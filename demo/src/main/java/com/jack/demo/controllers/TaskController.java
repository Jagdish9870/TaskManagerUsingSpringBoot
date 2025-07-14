package com.jack.demo.controllers;

import com.jack.demo.DTO.CreateTaskDTO;
import com.jack.demo.DTO.ErrorResponseDTO;
import com.jack.demo.DTO.TaskResponseDTO;
import com.jack.demo.DTO.UpdateTaskDTO;
import com.jack.demo.Services.NoteService;
import com.jack.demo.Services.TaskService;
import com.jack.demo.entities.TaskEntity;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final NoteService noteService;
    private ModelMapper modelMapper=new ModelMapper();

    public TaskController(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService = noteService;
    }

    @GetMapping("")
   public ResponseEntity<List<TaskEntity>> getTask(){
    var tasks=taskService.getTask();
    return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
        var task=taskService.getTaskById(id);
        var notes=noteService.getNotesForTask(id);
        if(task== null){
            return ResponseEntity.notFound().build();
        }
        var taskResponse=modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);
    }
    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO taskDTO) throws ParseException {
        var task=taskService.addTask(taskDTO.getTitle(),taskDTO.getDesc(),taskDTO.getDeadLine());
        return ResponseEntity.ok(task);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id")Integer id, @RequestBody UpdateTaskDTO updateTaskDTO) throws ParseException {
        var task=taskService.updateTask(id,updateTaskDTO.getDesc(),updateTaskDTO.getDeadLine(),updateTaskDTO.getCompleted());
        if(task== null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("invalid date format"));
        }
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("internal server error"));
    }


}
