package com.tecnotree.rwagent.services;

import com.tecnotree.rwagent.entities.ToDo;
import com.tecnotree.rwagent.repositories.ToDoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {

    @Mock
    ToDoRepository repository;

    IToDoService service;

    private static final Long ID = 1L;

    @BeforeEach
    void init() {
        service = new ToDoService(repository);
    }

    @Test
    public void findByUserIdAndCompleted_whenUidNotExist_shouldReturnNull() {
        when(repository.findByUserIdAndCompleted(anyLong(), anyBoolean())).thenReturn(Collections.EMPTY_LIST);
        Assertions.assertEquals(Collections.EMPTY_LIST, service.findByUserIdAndCompleted(ID, true));
    }

    @Test
    public void findByUserIdAndCompleted_shouldReturnListWithSameInputParameters() {
        when(repository.findByUserIdAndCompleted(ID, true)).thenReturn(getToDoList());
        service.findByUserIdAndCompleted(ID, true).forEach(cs -> {
            getToDoList().forEach(cr -> {
                Assertions.assertEquals(cs.getUserId(), cr.getUserId());
                Assertions.assertEquals(cs.getUserId(), ID);
            });
        });

    }

    @Test
    public void findAll_shouldReturnToDoList() {
        when(repository.findAll()).thenReturn(getToDoList());

        getToDoList().forEach(t -> {
            Assertions.assertTrue(service.findAll().contains(t));
        });

        Assertions.assertArrayEquals(getToDoList().toArray(),
                service.findAll().toArray());

        Assertions.assertEquals(getToDoList().size(), service.findAll().size());
    }

    @Test
    public void findAll_WhenNoToDoExist_shouldReturnNull() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);

        Assertions.assertEquals(Collections.EMPTY_LIST, service.findAll());
    }


    private List<ToDo> getToDoList() {
        List<ToDo> todos = new ArrayList<>();
        todos.add(ToDo.builder().id(2L).title("title").userId(ID).completed(true).build());
        todos.add(ToDo.builder().id(ID).title("title").userId(ID).completed(true).build());
        return todos;
    }


}
