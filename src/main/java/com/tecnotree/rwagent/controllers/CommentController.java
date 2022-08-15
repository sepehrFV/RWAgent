package com.tecnotree.rwagent.controllers;

import com.tecnotree.rwagent.dtos.CommentDTO;
import com.tecnotree.rwagent.dtos.CommentUpdateDTO;
import com.tecnotree.rwagent.entities.Comment;
import com.tecnotree.rwagent.mappers.CommentMapper;
import com.tecnotree.rwagent.services.ICommentService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/comment")
public class CommentController {


    private final ICommentService service;
    private final CommentMapper mapper;

    public CommentController(ICommentService service, CommentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @ApiOperation(value = "find comments by pagination")
    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<List<Comment>> findWithPagination(@PathVariable int page, @PathVariable int size) {
        Page<Comment> commentPage = service.findAllPagination(page, size);
        List<Comment> comments = commentPage.getContent();
        return ResponseEntity.ok(comments);

    }

    @ApiOperation(value = "find all comments from specific post")
    @GetMapping(value = "/{postId}")
    public ResponseEntity<List<Comment>> findCommentsWithPostId(@PathVariable Long postId) {

        List<Comment> comments = service.findAllByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @ApiOperation(value = "create comment about a post")
    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CommentDTO commentDTO) {
        service.create(mapper.toEntity(commentDTO));
        return ResponseEntity.ok("comment successfully created");
    }

    @ApiOperation(value = "update all comment fields except userId")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<CommentDTO> partialUpdate(
            @RequestBody CommentUpdateDTO updateDTO,
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.toDTO(service.update(id, updateDTO)));
    }

    @ApiOperation(value = "delete comment by specific id")
    @DeleteMapping(value = "/byId/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
