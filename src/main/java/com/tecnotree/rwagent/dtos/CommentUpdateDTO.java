package com.tecnotree.rwagent.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentUpdateDTO {

    private String name;
    private String email;
    private String body;

}
