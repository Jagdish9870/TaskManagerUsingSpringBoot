package com.jack.demo.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskEntity {
    private int id;
    private String title;
    private String desc;
    private Date deadLine;
    private boolean completed;


}
