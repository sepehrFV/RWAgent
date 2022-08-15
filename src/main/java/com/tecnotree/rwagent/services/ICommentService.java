package com.tecnotree.rwagent.services;

import com.tecnotree.rwagent.dtos.CommentDTO;
import com.tecnotree.rwagent.dtos.CommentUpdateDTO;
import com.tecnotree.rwagent.entities.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICommentService extends IGenericService<Comment, Long> {

    List<Comment> findAllByPostId(Long postId);

    Comment update(Long id, CommentUpdateDTO changed);

    Page<Comment> findAllPagination(int page, int size);


}
