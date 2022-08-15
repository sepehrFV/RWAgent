package com.tecnotree.rwagent.mappers;

import com.tecnotree.rwagent.dtos.PostDTO;
import com.tecnotree.rwagent.entities.Post;
import com.tecnotree.rwagent.entities.Post.PostBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-15T04:16:29+0430",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post toEntity(PostDTO postDTO) {
        if ( postDTO == null ) {
            return null;
        }

        PostBuilder post = Post.builder();

        post.userId( postDTO.getUserId() );
        post.title( postDTO.getTitle() );
        post.body( postDTO.getBody() );

        return post.build();
    }

    @Override
    public List<Post> toEntities(List<PostDTO> postDTOs) {
        if ( postDTOs == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( postDTOs.size() );
        for ( PostDTO postDTO : postDTOs ) {
            list.add( toEntity( postDTO ) );
        }

        return list;
    }

    @Override
    public PostDTO toDTO(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        postDTO.setUserId( post.getUserId() );
        postDTO.setTitle( post.getTitle() );
        postDTO.setBody( post.getBody() );

        return postDTO;
    }

    @Override
    public List<PostDTO> toDTOs(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostDTO> list = new ArrayList<PostDTO>( posts.size() );
        for ( Post post : posts ) {
            list.add( toDTO( post ) );
        }

        return list;
    }
}
