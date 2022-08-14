package com.tecnotree.rwagent.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CommentDTO {

    @NotNull
    @Min(1)
    private Long postId;
    private String name;
    private String email;
    private String body;

}
