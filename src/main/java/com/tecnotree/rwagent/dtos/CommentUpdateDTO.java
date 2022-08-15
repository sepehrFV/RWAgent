package com.tecnotree.rwagent.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CommentUpdateDTO {

    private String name;
    private String email;
    private String body;

}
