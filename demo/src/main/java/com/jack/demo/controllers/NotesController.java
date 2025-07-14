package com.jack.demo.controllers;

import com.jack.demo.DTO.CreateNoteDTO;
import com.jack.demo.DTO.CreateNoteResponseDTO;
import com.jack.demo.Services.NoteService;
import com.jack.demo.entities.NoteEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    private NoteService noteService;
    public NotesController(NoteService noteService){
        this.noteService=noteService;
    }
    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes=noteService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }
    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId, @RequestBody CreateNoteDTO body){
        var note=noteService.addNoteforTask(taskId,body.getTitle(),body.getBody());
        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId,note));
    }


}
