package com.jack.demo.Services;

import com.jack.demo.entities.NoteEntity;
import com.jack.demo.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NoteService {
    private TaskService taskService;
    private HashMap<Integer,TaskNoteHolder> taskNoteHolders=new HashMap<>();
    public NoteService(TaskService taskService){
        this.taskService=taskService;
    }
    class TaskNoteHolder{
       protected int noteId=1;
       protected ArrayList<NoteEntity> notes=new ArrayList<>();

    }
    public List<NoteEntity> getNotesForTask(int taskId){
        TaskEntity task=taskService.getTaskById(taskId);
        if(task== null){
            return null;
        }
        if(taskNoteHolders.get(taskId)==null){
            taskNoteHolders.put(taskId,new TaskNoteHolder());
        }
        return taskNoteHolders.get(taskId).notes;
    }
    public NoteEntity addNoteforTask(int taskId, String title, String body){
        TaskEntity task=taskService.getTaskById(taskId);
        if(task==null){
            return null;
        }
        if(taskNoteHolders.get(taskId)==null){
            taskNoteHolders.put(taskId,new TaskNoteHolder());
        }
        TaskNoteHolder taskNoteHolder=taskNoteHolders.get(taskId);
        NoteEntity noteEntity=new NoteEntity();
        noteEntity.setBody(body);
        noteEntity.setId(taskNoteHolder.noteId);
        noteEntity.setTitle(title);
        taskNoteHolder.notes.add(noteEntity);
        taskNoteHolder.noteId++;
        return  noteEntity;

    }


}
