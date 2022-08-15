package com.tecnotree.rwagent.datacatchers;

import com.tecnotree.rwagent.entities.Post;
import com.tecnotree.rwagent.services.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReadPosts implements Runnable{



    private final RestTemplate restTemplate;
    private final IPostService service;

    public ReadPosts(RestTemplate restTemplate, IPostService service) {
        this.restTemplate = restTemplate;
        this.service = service;
    }

    @Override
    public void run() {
        catchPost();
    }

    public synchronized void catchPost(){
        ResponseEntity<Post[]> response = restTemplate.getForEntity(
                "https://jsonplaceholder.typicode.com/posts",
                Post[].class);
        Post[] posts = response.getBody();

        if(posts!=null && posts.length!=0)
            for (Post p:posts) {
                service.create(p);
            }
    }
}
