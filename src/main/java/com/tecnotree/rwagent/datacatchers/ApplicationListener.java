package com.tecnotree.rwagent.datacatchers;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListener implements org.springframework.context.ApplicationListener<ApplicationReadyEvent> {


    private final ReadPosts readPosts;

    private final ReadComments readComments;

    private final ReadToDos readToDos;

    public ApplicationListener(ReadPosts readPosts, ReadComments readComments, ReadToDos readToDos) {
        this.readPosts = readPosts;
        this.readComments = readComments;
        this.readToDos = readToDos;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Thread one = new Thread(readPosts);
        Thread two = new Thread(readComments);
        Thread three = new Thread(readToDos);

        one.start();
        try {
            one.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        two.start();
        three.start();
    }
}
