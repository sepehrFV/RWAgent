package com.tecnotree.rwagent.services;

import com.tecnotree.rwagent.exceptions.NotFoundException;
import com.tecnotree.rwagent.repositories.GenericRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.dao.DataAccessException;
import java.util.List;

public abstract class GenericServiceImp<E, ID> implements IGenericService<E, ID> {

    protected org.apache.logging.log4j.Logger logger = LogManager.getLogger(GenericServiceImp.class);

    protected abstract GenericRepository<E, ID> getRepository();
    protected abstract Class<?> getExtendedClass();


    @Override
    public void create(E entity) {
        logger.info("enter "+getExtendedClass().getName()+" create()");
        try{
            getRepository().save(entity);
            logger.info(getExtendedClass().getName()+" saved");
        }catch(DataAccessException ex){
            logger.error("message: "+ex.getMessage());
        }
    }

    @Override
    public void delete(ID id) {
        logger.info("enter "+getExtendedClass().getName()+" delete()");
        if (existById(id))
            getRepository().deleteById(id);
        else{
            logger.error("operation canceled because id: " + id + " does not exist");
            throw new RuntimeException("operation canceled because id: " + id + " does not exist");
        }
    }

    @Override
    public List<E> findAll() {
        logger.info("enter "+getExtendedClass().getName()+" findAll()");
        List<E> list = null;
        try{
            list = getRepository().findAll();
            logger.info("method return is: "+list.toString());
        }catch(DataAccessException ex){
            logger.error("message: "+ex.getMessage());
        }
        return list;
    }

    @Override
    public E findById(ID id) {
        logger.info("enter "+getExtendedClass().getName()+" findById()");
        if (existById(id))
            return getRepository().findById(id).get();
        else throw new NotFoundException("ID not found");
    }

    @Override
    public boolean existById(ID id) {
        return getRepository().existsById(id);
    }


}
