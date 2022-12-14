package com.tecnotree.rwagent.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PostUpdateDTO {

    private String title;
    private String body;

}
