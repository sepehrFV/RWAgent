package com.tecnotree.rwagent.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PostDTO {

    @NotNull
    @Min(1L)
    private Long userId;
    private String title;
    private String body;

}
