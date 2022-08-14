package com.tecnotree.rwagent.services;


import com.tecnotree.rwagent.dtos.CommentDTO;
import com.tecnotree.rwagent.dtos.CommentUpdateDTO;
import com.tecnotree.rwagent.entities.Comment;
import com.tecnotree.rwagent.repositories.CommentRepository;
import com.tecnotree.rwagent.repositories.GenericRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService extends GenericServiceImp<Comment, Long> implements ICommentService {

    private final CommentRepository repository;
    private final IPostService postService;

    public CommentService(CommentRepository repository, IPostService postService) {
        this.repository = repository;
        this.postService = postService;
    }

    @Override
    protected GenericRepository<Comment, Long> getRepository() {
        return this.repository;
    }

    @Override
    protected Class<?> getExtendedClass() {
        return getClass();
    }

    @Override
    public void create(CommentDTO dto) {
        logger.info("creating comment object:"+dto.toString());
        try{
            Comment comment = new Comment();
            comment.setName(dto.getName());
            comment.setEmail(dto.getEmail());
            comment.setPost(postService.findById(dto.getPostId()));
            comment.setBody(dto.getBody());
            repository.save(comment);
            logger.info("created:)");
        }catch (DataAccessException ex){
            logger.error(ex.getMessage());
        }

    }

    @Override
    public Comment update(Long id, CommentUpdateDTO changed) {
        logger.info("enter comment update()");
        Comment main = findById(id);
        logger.info("update post:" + main.toString());
        logger.info("update to:" + changed.toString());
        if (changed.getEmail() != null)
            main.setEmail(changed.getEmail());
        if (changed.getBody() != null)
            main.setBody(changed.getBody());
        if (changed.getName() != null)
            main.setName(changed.getName());
        repository.save(main);
        logger.info("update successful");
        return main;
    }

    @Override
    public List<Comment> findAllByPostId(Long postId) {
        logger.info("enter findAllCommentByPostId() with input id: "+postId);
        List<Comment> list = null;
        try{
            list = repository.findAllByPostId(postId);
            logger.info("comments: "+list.toString());
        }catch (DataAccessException ex){
            logger.error(ex.getMessage());
        }
        return list;
    }

    @Override
    public Page<Comment> findAllPagination(int page, int size) {
        logger.info("enter findAllPagination() with page value: " + page + " size value:" + size);
        if (page >= 0 && size >= 0) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Comment> commentPage=  repository.findAll(pageable);
            logger.info("comments: "+commentPage.toString());
            return commentPage;
        } else {
            logger.info("the input parameters are not correct (should be > 0");
            throw new RuntimeException("page or size value should be greater than 0");
        }
    }


}
