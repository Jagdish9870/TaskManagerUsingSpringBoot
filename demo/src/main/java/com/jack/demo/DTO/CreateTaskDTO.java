package com.jack.demo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CreateTaskDTO {
    String title;
    String desc;
    String deadLine;
}
