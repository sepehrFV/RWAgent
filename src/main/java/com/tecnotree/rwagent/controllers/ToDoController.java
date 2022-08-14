package com.tecnotree.rwagent.controllers;


import com.tecnotree.rwagent.entities.ToDo;
import com.tecnotree.rwagent.services.IToDoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class ToDoController {

    private final IToDoService service;

    public ToDoController(IToDoService service) {
        this.service = service;
    }

    @ApiOperation(value = "find all to-do’s")
    @GetMapping
    public ResponseEntity<List<ToDo>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @ApiOperation(value = "find to-do’s of specific user by user id and completed field")
    @GetMapping(value = "/{userId}/{completed}")
    public ResponseEntity<List<ToDo>> findByUserAndCompleted(@PathVariable Long userId, @PathVariable boolean completed) {
        return ResponseEntity.ok(service.findByUserIdAndCompleted(userId, completed));
    }

}
