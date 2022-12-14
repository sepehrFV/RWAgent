package com.tecnotree.rwagent.controllers;


import com.tecnotree.rwagent.dtos.CommentDTO;
import com.tecnotree.rwagent.dtos.PostDTO;
import com.tecnotree.rwagent.dtos.PostUpdateDTO;
import com.tecnotree.rwagent.entities.Comment;
import com.tecnotree.rwagent.entities.Post;
import com.tecnotree.rwagent.mappers.CommentMapper;
import com.tecnotree.rwagent.mappers.PostMapper;
import com.tecnotree.rwagent.services.ICommentService;
import com.tecnotree.rwagent.services.IPostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/post")
public class PostController {

    private final IPostService service;
    private final ICommentService commentService;
    private final PostMapper mapper;
    private final CommentMapper commentMapper;

    public PostController(IPostService service, ICommentService commentService, PostMapper mapper, CommentMapper commentMapper) {
        this.service = service;
        this.commentService = commentService;
        this.mapper = mapper;
        this.commentMapper = commentMapper;
    }

    @ApiOperation(value = "find posts by pagination")
    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<List<Post>> findWithPagination(@PathVariable int page, @PathVariable int size) {
        Page<Post> postPage = service.findAllPagination(page, size);
        List<Post> posts = postPage.getContent();
        return ResponseEntity.ok(posts);
    }

    @ApiOperation(value = "find post by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        service.findById(id);
        return ResponseEntity.ok(service.findById(id));
    }

    @ApiOperation(value = "find all comments from specific post")
    @GetMapping(value = "/commentsBy/{postId}")
    public ResponseEntity<List<CommentDTO>> findCommentsWithPostId(@PathVariable Long postId) {

        List<Comment> commentDTOS = commentService.findAllByPostId(postId);
        return ResponseEntity.ok(commentMapper.toDTOs(commentDTOS));
    }

    @ApiOperation(value = "find all posts that have the ???eos??? keyword in their title")
    @GetMapping(value = "/includePattern")
    public ResponseEntity<List<Post>> findContainPatternInTitle() {
        return ResponseEntity.ok(service.findAllContainPatternInTitle());
    }

    @ApiOperation(value = "create a post")
    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid PostDTO post) {
        service.create(mapper.toEntity(post));
        return ResponseEntity.ok("post successfully created");
    }


    @ApiOperation(value = "update all post fields except userId")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<PostDTO> update(
            @RequestBody PostUpdateDTO updateDTO,
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.toDTO(service.update(id, updateDTO)));
    }

    @ApiOperation(value = "delete post by specific id")
    @DeleteMapping(value = "/byId/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("post successfully deleted");
    }

}
