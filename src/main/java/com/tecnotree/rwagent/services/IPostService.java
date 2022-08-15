package com.tecnotree.rwagent.services;

import com.tecnotree.rwagent.dtos.PostUpdateDTO;
import com.tecnotree.rwagent.entities.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPostService extends IGenericService<Post, Long> {

    Page<Post> findAllPagination(int page, int size);

    List<Post> findAllContainPatternInTitle();

    Post update(Long id, PostUpdateDTO changed);

}
