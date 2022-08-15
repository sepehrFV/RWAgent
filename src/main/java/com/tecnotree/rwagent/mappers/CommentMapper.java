package com.tecnotree.rwagent.mappers;

import com.tecnotree.rwagent.dtos.CommentDTO;
import com.tecnotree.rwagent.entities.Comment;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentDTO commentDTO);
    List<Comment> toEntities(List<CommentDTO> commentDTOs);
    CommentDTO toDTO(Comment comment);
    List<CommentDTO> toDTOs(List<Comment> comments);


}
