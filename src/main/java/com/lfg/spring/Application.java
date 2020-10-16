package com.lfg.spring;

import java.util.Date;
import java.util.LinkedList;

import com.lfg.spring.model.Group;
import com.lfg.spring.model.Post;
import com.lfg.spring.model.User;
import com.lfg.spring.repository.GroupRepository;
import com.lfg.spring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


	@Bean
	public CommandLineRunner loaddata(UserRepository userRepository, GroupRepository groupRepository){
        return (args) -> {

            User user1 = User.builder().username("user").password(new BCryptPasswordEncoder().encode("pass")).email("user@gmail.com").isEnabled(true).createdAt(new Date()).build();
            userRepository.save(user1);

            Group group1 = new Group();
            group1.setName("group1");
            group1.setTopic("topic1");
            group1.setDescription("description");
            group1.setType("type");
            group1.setPosts(new LinkedList<>());
            group1.setMembers(new LinkedList<>());
            group1.setDisabled(false);
            group1.setCreatedAt(new Date());
            groupRepository.save(group1);

            Post post = new Post();
            post.setTitle("Title");
            post.setBody("Body");
            post.setCreatedAt(new Date());
            post.setUser(user1);
            post.setGroup(group1);
        };
    }

}
