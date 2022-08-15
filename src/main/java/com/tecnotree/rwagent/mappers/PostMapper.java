package com.tecnotree.rwagent.mappers;

import com.tecnotree.rwagent.dtos.PostDTO;
import com.tecnotree.rwagent.entities.Post;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toEntity(PostDTO postDTO);
    List<Post> toEntities(List<PostDTO> postDTOs);
    PostDTO toDTO(Post post);
    List<PostDTO> toDTOs(List<Post> posts);

}
