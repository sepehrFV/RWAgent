package com.tecnotree.rwagent.repositories;

import com.tecnotree.rwagent.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends GenericRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId);

    Page<Comment> findAll(Pageable pageable);

}
