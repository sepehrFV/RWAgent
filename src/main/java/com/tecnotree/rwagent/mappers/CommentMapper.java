/*
package com.tecnotree.rwagent.mappers;

import com.tecnotree.rwagent.dtos.CommentDTO;
import com.tecnotree.rwagent.entities.Comment;
import com.tecnotree.rwagent.services.IPostService;
import org.mapstruct.*;
import java.util.List;


@Mapper(componentModel = "spring",uses = {IPostService.class})
public interface CommentMapper {


    @Mapping(source = "postId", target = "post")
    Comment toEntity(CommentDTO commentDTO);

    @Mapping(source = "postId", target = "post")
    List<Comment> toEntities(List<CommentDTO> commentDTOs);

    @Mapping(source = "post", target = "postId")
    CommentDTO toDTO(Comment comment);

    @Mapping(source = "post", target = "postId")
    List<CommentDTO> toDTOs(List<Comment> comments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Comment entity, CommentDTO dto);



}
*/
