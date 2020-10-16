package com.lfg.spring;

import com.lfg.spring.model.User;
import com.lfg.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


	@Bean
	public CommandLineRunner loaddata(UserRepository userRepository){
        return (args) -> {
            User user1 = new User();
        };
    }

}
