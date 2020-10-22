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
        return (args) -> { };
    }

}
