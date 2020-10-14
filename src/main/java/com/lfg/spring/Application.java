package com.lfg.spring;

import com.lfg.spring.model.Posts;
import com.lfg.spring.model.Users;
import com.lfg.spring.repository.PostsRepository;
import com.lfg.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void run(String... args) throws Exception {
        Posts post = new Posts();
        post.setTitle("Title");
        post.setBody("body");
        postsRepository.save(post);

        Users user = new Users();
        user.setUsername("user");
        user.setRole("USER");
        user.setEnabled(true);
        user.setEmail("a@a.a");
        user.setPassword("pass");
        usersRepository.save(user);
    }
}
