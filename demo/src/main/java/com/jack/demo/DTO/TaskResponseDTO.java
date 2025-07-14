package com.jack.demo.DTO;

import com.jack.demo.entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {
    private int id;
    private String title;
    private String desc;
    private Date deadLine;
    private boolean completed;
    private List<NoteEntity> notes;
}
