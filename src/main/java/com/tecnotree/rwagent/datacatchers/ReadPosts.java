package com.tecnotree.rwagent.datacatchers;

import com.tecnotree.rwagent.entities.Post;
import com.tecnotree.rwagent.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class ReadPosts implements Runnable{


    @Autowired
    private RestTemplate restTemplate;

    private IPostService service;

    public ReadPosts(IPostService service) {
        this.service = service;
    }

    public ReadPosts() {
    }

    @Override
    public void run() {


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
