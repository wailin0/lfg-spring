package com.lfg.spring;

import java.util.Date;
import java.util.LinkedList;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import com.lfg.spring.model.Comment;
import com.lfg.spring.model.Group;
import com.lfg.spring.model.Member;
import com.lfg.spring.model.Post;
import com.lfg.spring.model.User;
import com.lfg.spring.repository.CommentRepository;
import com.lfg.spring.repository.GroupRepository;
import com.lfg.spring.repository.MemberRepository;
import com.lfg.spring.repository.PostRepository;
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
    protected Module module() {
        return new Hibernate5Module();
    }
    
	@Bean
	public CommandLineRunner loaddata(CommentRepository commentRepository, MemberRepository memberRepository, PostRepository postRepository, UserRepository userRepository, GroupRepository groupRepository){
        return (args) -> {
            User user1 = User.builder().username("user").password(new BCryptPasswordEncoder().encode("pass")).email("user@gmail.com").isEnabled(true).createdAt(new Date()).build();

            Member member1 = new Member();
            member1.setDisabled(false);
            member1.setJoinedAt(new Date());
            member1.setUser(user1);

            memberRepository.save(member1);
            
            userRepository.save(user1);

            Post post1 = new Post();
            post1.setTitle("Title");
            post1.setBody("Body");
            post1.setCreatedAt(new Date());
            post1.setUser(user1);
            post1.setGroup(null);

            Comment comment1 = new Comment();
            comment1.setBody("Body");
            comment1.setCreatedAt(new Date());
            comment1.setUser(user1);
            comment1.setPost(post1);

            postRepository.save(post1);
            commentRepository.save(comment1);
        };
    }

}
