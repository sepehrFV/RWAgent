package com.tecnotree.rwagent.services;

import java.util.List;

public interface IGenericService<E, ID> {

    void create(E entity);

    void delete(ID id);

    List<E> findAll();

    E findById(ID id);

    boolean existById(ID id);



}
