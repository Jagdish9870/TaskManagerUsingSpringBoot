package com.jack.demo.Services;

import com.jack.demo.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks= new ArrayList<>();
    private int taskId=1;
    private final SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    public TaskEntity addTask(String title, String desc, String deadLine) throws ParseException {
        TaskEntity task=new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDesc(desc);
        task.setDeadLine(simpleDateFormat.parse(deadLine));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;

    }
    public ArrayList<TaskEntity> getTask(){
        return tasks;
    }
    public TaskEntity getTaskById(int id){
        for(TaskEntity task: tasks){
            if(task.getId()==id){
                return task;
            }
        }
        return null;
    }
    public TaskEntity updateTask(int id, String desc, String deadLine,Boolean completed) throws ParseException {
        TaskEntity task=getTaskById(id);
        if(task== null){
            return null;
        }
        if(desc!=null){
            task.setDesc(desc);

        }
        if(deadLine!=null){


        task.setDeadLine(simpleDateFormat.parse(deadLine));
        }
        if(completed !=null){


        task.setCompleted(completed);
        }

        return task;


    }
}
