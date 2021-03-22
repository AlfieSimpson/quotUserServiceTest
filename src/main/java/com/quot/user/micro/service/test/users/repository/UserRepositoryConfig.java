package com.quot.user.micro.service.test.users.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRepositoryConfig {

    private FileBasedUserRepository fileBasedUserRepository;

    @Autowired
    public UserRepositoryConfig(FileBasedUserRepository fileBasedUserRepository){
        this.fileBasedUserRepository = fileBasedUserRepository;
    }

    @Bean
    public UserRepository userRepository(){
        return fileBasedUserRepository;
    }
}
