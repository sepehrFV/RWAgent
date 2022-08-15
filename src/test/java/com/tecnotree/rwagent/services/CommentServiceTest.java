package com.tecnotree.rwagent.services;


import com.tecnotree.rwagent.entities.Comment;
import com.tecnotree.rwagent.exceptions.NotFoundException;
import com.tecnotree.rwagent.mappers.CommentMapper;
import com.tecnotree.rwagent.repositories.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    CommentRepository repository;

    @Mock
    IPostService postService;

    @Mock
    CommentMapper mapper;

    ICommentService service;

    private static final Long ID = 1L;

    @BeforeEach
    void init(){
        service = new CommentService(repository,postService,mapper);
        MockitoAnnotations.initMocks(postService);
    }



    @Test
    public void create_shouldCallSaveMethodOneTime(){
        Comment comment = getComment();
        Mockito.when(repository.save(comment)).thenCallRealMethod();

        Mockito.verify(repository,Mockito.times(1)).save(comment);
    }

    @Test
    public void findAll_shouldFindAllComments(){
        //given
        Comment comment =getComment();
        List<Comment> comments = Collections.singletonList(comment);
        //when
        when(repository.findAll()).thenReturn(comments);
        //then
        Assertions.assertEquals(comment,service.findAll().get(0));
    }

    @Test
    public void findAll_shouldReturnCommentList() {
        when(repository.findAll()).thenReturn(getCommentList());

        getCommentList().forEach(p -> {
            Assertions.assertTrue(service.findAll().contains(p));
        });

        Assertions.assertArrayEquals(getCommentList().toArray(),
                service.findAll().toArray());

        Assertions.assertEquals(getCommentList().size(), service.findAll().size());
    }

    @Test
    public void findAll_WhenNoCommentExist_shouldReturnNull() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);

        Assertions.assertEquals(Collections.EMPTY_LIST, service.findAll());
    }

    @Test
    public void findById_shouldGetComment() {
        Comment comment = getComment();

        when(repository.findById(ID)).thenReturn(Optional.of(comment));

        Assertions.assertNotNull(service.findById(ID));
        Assertions.assertEquals(comment, service.findById(ID));
        Assertions.assertEquals(comment.getId(), service.findById(ID).getId());
        Assertions.assertEquals(comment.getName(), service.findById(ID).getName());
        Assertions.assertEquals(comment.getBody(), service.findById(ID).getBody());
        Assertions.assertEquals(comment.getEmail(), service.findById(ID).getEmail());
        Assertions.assertEquals(comment.getPostId(), service.findById(ID).getPostId());
    }

    @Test
    public void findById_whenNotFound_shouldThrowException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,()->{
            service.findById(ID);
        });
    }

    @Test
    public void findAllByPostId_shouldOnlyReturnDataWithInputPostId(){
        when(repository.findAllByPostId(ID)).thenReturn(getCommentList());
        service.findAllByPostId(ID).forEach(cs->{
            getCommentList().forEach(cr->{
                Assertions.assertEquals(cs,cr);
                Assertions.assertEquals(cs.getPostId(),ID);
            });
        });
    }

    @Test
    public void findAllByPostId_whenNoCommentExist_shouldReturnNull(){
        when(repository.findAllByPostId(ID)).thenReturn(Collections.EMPTY_LIST);
        Assertions.assertEquals(Collections.EMPTY_LIST,service.findAllByPostId(ID));
    }


    private Comment getComment(){
        Comment comment = Comment.builder().id(ID).body("body").email("email").name("name").postId(ID).build();
        return comment;
    }

    private List<Comment> getCommentList(){
        List<Comment> comments = new ArrayList<>();
        comments.add(getComment());
        comments.add(Comment.builder().id(2L).body("body").email("email").name("name").postId(ID).build());
        return comments;
    }
}
