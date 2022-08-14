package com.tecnotree.rwagent.services;

import com.tecnotree.rwagent.entities.ToDo;

import java.util.List;

public interface IToDoService extends IGenericService<ToDo,Long> {

    List<ToDo> findByUserIdAndCompleted(Long uId, boolean completed);

}
