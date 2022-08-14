package com.tecnotree.rwagent.repositories;

import com.tecnotree.rwagent.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends GenericRepository<Post,Long>{

    Page<Post> findAll(Pageable pageable);

    @Query("FROM Post WHERE title LIKE '%eos%'")
    List<Post> findAllContainPatternInTitle();

}
