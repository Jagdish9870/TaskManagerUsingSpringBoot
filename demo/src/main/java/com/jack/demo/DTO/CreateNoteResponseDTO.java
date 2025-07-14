package com.jack.demo.DTO;

import com.jack.demo.entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoteResponseDTO {
    private Integer taskId;
    private NoteEntity note;

}
