package com.lfg.spring;

import java.util.Date;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import com.lfg.spring.model.User;
import com.lfg.spring.model.enums.UserRole;
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
    
    // Because Jackson-Serializer triggers lazy loading in any cases
    @Bean
    protected Module module() {
        
        return new Hibernate5Module();
    }

	@Bean
	public CommandLineRunner loaddata(CommentRepository commentRepository, MemberRepository memberRepository, PostRepository postRepository, UserRepository userRepository, GroupRepository groupRepository){
        return (args) -> {

            User user1 = User.builder().username("user").password(new BCryptPasswordEncoder().encode("pass")).email("user@gmail.com").enabled(true).createdAt(new Date()).roles(UserRole.ROLE_ADMIN.name()).build();

            userRepository.save(user1);

         };
    }

}
