package com.tecnotree.rwagent.services;

import com.tecnotree.rwagent.dtos.PostUpdateDTO;
import com.tecnotree.rwagent.entities.Post;
import com.tecnotree.rwagent.exceptions.NotFoundException;
import com.tecnotree.rwagent.mappers.PostMapper;
import com.tecnotree.rwagent.repositories.PostRepository;
import com.tecnotree.rwagent.services.IPostService;
import com.tecnotree.rwagent.services.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    PostRepository repository;


    IPostService service;

    private static final Long ID = 1L;

    @BeforeEach
    void init(){
        service = new PostService(repository);
    }

    @Test
    public void findAll_shouldFindAllPosts(){

        Post post =getPost();
        List<Post> posts = Collections.singletonList(post);

        when(repository.findAll()).thenReturn(posts);

        Assertions.assertEquals(post,service.findAll().get(0));
    }

    @Test
    public void create_shouldCallSaveMethodOneTime(){
        Post post = getPost();
        Mockito.when(repository.save(post)).thenCallRealMethod();

        Mockito.verify(repository,Mockito.times(1)).save(post);
    }

    @Test
    public void findAll_shouldReturnPostList() {
        when(repository.findAll()).thenReturn(getPostList());

        getPostList().forEach(p -> {
            Assertions.assertTrue(service.findAll().contains(p));
        });

        Assertions.assertArrayEquals(getPostList().toArray(),
                service.findAll().toArray());

        Assertions.assertEquals(getPostList().size(), service.findAll().size());
    }

    @Test
    public void findAll_WhenNoPostExist_shouldReturnNull() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);

        Assertions.assertEquals(Collections.EMPTY_LIST, service.findAll());
    }

    @Test
    public void findById_shouldGetPost() {
        Post post = getPost();

        when(repository.findById(ID)).thenReturn(Optional.of(post));

        Assertions.assertNotNull(service.findById(ID));
        Assertions.assertEquals(post, service.findById(ID));
        Assertions.assertEquals(post.getId(), service.findById(ID).getId());
        Assertions.assertEquals(post.getTitle(), service.findById(ID).getTitle());
        Assertions.assertEquals(post.getBody(), service.findById(ID).getBody());
        Assertions.assertEquals(post.getUserId(), service.findById(ID).getUserId());
    }

    @Test
    public void findById_whenNotFound_shouldThrowException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,()->{
            service.findById(ID);
        });
    }

    @Test
    public void update_shouldChangeOnlyNotNullFields(){

        ArgumentCaptor<Post> entityArgumentCaptor = ArgumentCaptor.forClass(Post.class);
        PostUpdateDTO inputDto = new PostUpdateDTO();
        inputDto.setTitle("changed title");
        inputDto.setBody(null);


        Post responseDto = service.update(ID,inputDto);
        verify(repository,Mockito.times(1)).save(entityArgumentCaptor.capture());


        Post savedEntity = entityArgumentCaptor.getValue();
        Assertions.assertEquals(inputDto.getTitle(), savedEntity.getTitle());
        Assertions.assertNotEquals(inputDto.getBody(),savedEntity.getBody());
        Assertions.assertEquals(responseDto.getBody(),savedEntity.getBody());


    }




    private Post getPost(){
        Post post = Post.builder().id(ID).body("body").title("title").userId(ID).build();
        return post;
    }

    private List<Post> getPostList(){
        List<Post> posts = new ArrayList<>();
        posts.add(getPost());
        posts.add(Post.builder().id(2L).body("body").title("title").userId(ID).build());
        return posts;
    }


}
