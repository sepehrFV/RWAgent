package com.tecnotree.rwagent;


import com.tecnotree.rwagent.datacatchers.ReadComments;
import com.tecnotree.rwagent.datacatchers.ReadPosts;
import com.tecnotree.rwagent.datacatchers.ReadToDos;
import com.tecnotree.rwagent.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);




    }
}
