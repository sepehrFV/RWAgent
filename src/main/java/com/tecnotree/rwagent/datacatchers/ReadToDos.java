package com.tecnotree.rwagent.datacatchers;

import com.tecnotree.rwagent.entities.ToDo;
import com.tecnotree.rwagent.services.IToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReadToDos implements Runnable{

    @Autowired
    private RestTemplate restTemplate;

    private IToDoService service;

    public ReadToDos(IToDoService service) {
        this.service = service;
    }

    public ReadToDos() {
    }

    @Override
    public void run() {
        ResponseEntity<ToDo[]> response = restTemplate.getForEntity(
                "https://jsonplaceholder.typicode.com/todos",
                ToDo[].class);
        ToDo[] toDos = response.getBody();

        if(toDos!=null && toDos.length!=0)
            for (ToDo t:toDos) {
                service.create(t);
            }

    }
}
