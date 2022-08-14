package com.tecnotree.rwagent.repositories;

import com.tecnotree.rwagent.entities.ToDo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends GenericRepository<ToDo, Long> {

    @Query("FROM ToDo where userId =:id AND completed =:value ")
    List<ToDo> findByUserIdAndCompleted(@Param("id") Long uId, @Param("value") boolean completed);

}
