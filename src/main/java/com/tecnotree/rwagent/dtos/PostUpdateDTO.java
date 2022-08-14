package com.tecnotree.rwagent.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUpdateDTO {

    private String title;
    private String body;

}
