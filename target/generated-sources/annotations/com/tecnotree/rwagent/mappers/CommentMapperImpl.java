package com.tecnotree.rwagent.mappers;

import com.tecnotree.rwagent.dtos.CommentDTO;
import com.tecnotree.rwagent.entities.Comment;
import com.tecnotree.rwagent.entities.Comment.CommentBuilder;
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
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        CommentBuilder comment = Comment.builder();

        comment.postId( commentDTO.getPostId() );
        comment.name( commentDTO.getName() );
        comment.email( commentDTO.getEmail() );
        comment.body( commentDTO.getBody() );

        return comment.build();
    }

    @Override
    public List<Comment> toEntities(List<CommentDTO> commentDTOs) {
        if ( commentDTOs == null ) {
            return null;
        }

        List<Comment> list = new ArrayList<Comment>( commentDTOs.size() );
        for ( CommentDTO commentDTO : commentDTOs ) {
            list.add( toEntity( commentDTO ) );
        }

        return list;
    }

    @Override
    public CommentDTO toDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setPostId( comment.getPostId() );
        commentDTO.setName( comment.getName() );
        commentDTO.setEmail( comment.getEmail() );
        commentDTO.setBody( comment.getBody() );

        return commentDTO;
    }

    @Override
    public List<CommentDTO> toDTOs(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentDTO> list = new ArrayList<CommentDTO>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( toDTO( comment ) );
        }

        return list;
    }
}
