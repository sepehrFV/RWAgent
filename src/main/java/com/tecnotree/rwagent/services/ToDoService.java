package com.tecnotree.rwagent.services;

import com.tecnotree.rwagent.entities.Post;
import com.tecnotree.rwagent.entities.ToDo;
import com.tecnotree.rwagent.repositories.GenericRepository;
import com.tecnotree.rwagent.repositories.ToDoRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService extends GenericServiceImp<ToDo, Long> implements IToDoService {

    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<ToDo, Long> getRepository() {
        return this.repository;
    }

    @Override
    protected Class<?> getExtendedClass() {
        return getClass();
    }

    @Override
    public List<ToDo> findByUserIdAndCompleted(Long uId, boolean completed) {
        logger.info("enter findToDoByUserIdAndCompleted() with uId: " + uId + " & complete:" + completed);
        List<ToDo> list = null;
        try{
            list = repository.findByUserIdAndCompleted(uId, completed);
            logger.info("ToDos :"+list.toString());
        }catch (DataAccessException ex){
            logger.error(ex.getMessage());
        }
        return list;
    }

}
