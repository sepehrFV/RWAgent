package com.tecnotree.rwagent.datacatchers;


import com.tecnotree.rwagent.entities.Comment;
import com.tecnotree.rwagent.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReadComments implements Runnable {


    private final RestTemplate restTemplate;
    private final ICommentService service;


    public ReadComments(RestTemplate restTemplate, ICommentService service) {
        this.restTemplate = restTemplate;
        this.service = service;
    }

    @Override
    public void run() {
        catchComment();
    }

    public synchronized void catchComment() {
        ResponseEntity<Comment[]> response = restTemplate.getForEntity(
                "https://jsonplaceholder.typicode.com/comments",
                Comment[].class);
        Comment[] comments = response.getBody();

        if (comments != null && comments.length != 0)
            for (Comment c : comments) {
                service.create(c);
            }
    }

}
