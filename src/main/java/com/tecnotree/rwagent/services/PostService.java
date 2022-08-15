package com.tecnotree.rwagent.services;

import com.tecnotree.rwagent.dtos.PostUpdateDTO;
import com.tecnotree.rwagent.entities.Post;
import com.tecnotree.rwagent.exceptions.NotFoundException;
import com.tecnotree.rwagent.repositories.GenericRepository;
import com.tecnotree.rwagent.repositories.PostRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService extends GenericServiceImp<Post, Long> implements IPostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Post, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Class<?> getExtendedClass() {
        return this.getClass();
    }

    @Override
    public Post update(Long id, PostUpdateDTO changed) {

        logger.info("enter post update()");

        if (existById(id)) {
            Post main = findById(id);
            logger.info("update post:" + main);
            logger.info("update to:" + changed);

            if (changed.getBody() != null)
                main.setBody(changed.getBody());
            if (changed.getTitle() != null)
                main.setTitle(changed.getTitle());

            repository.save(main);
            logger.info("update successful");
            return main;
        } else {
            logger.error("post not found with id= " + id);
            throw new NotFoundException("post not found id= " + id);
        }

    }

    @Override
    public Page<Post> findAllPagination(int page, int size) {
        logger.info("enter findAllPagination() with page: " + page + "and size: " + size);
        if (page >= 0 && size >= 0) {
            Pageable pageable = PageRequest.of(page, size);
            return repository.findAll(pageable);

        } else {
            logger.info("the input parameters are not correct (should be > 0");
            throw new RuntimeException("page or size value should be greater than 0");
        }

    }

    @Override
    public List<Post> findAllContainPatternInTitle() {
        logger.info("enter findAllContainPatternInTitle()...");
        List<Post> postList = null;
        try{
            postList = repository.findAllContainPatternInTitle();
        }catch (DataAccessException ex){
            logger.error(ex.getMessage());
        }
        return postList;
    }


}
