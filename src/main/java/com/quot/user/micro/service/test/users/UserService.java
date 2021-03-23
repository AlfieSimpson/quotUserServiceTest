package com.quot.user.micro.service.test.users;

import com.quot.user.micro.service.test.users.model.User;
import com.quot.user.micro.service.test.users.model.UserId;
import com.quot.user.micro.service.test.users.repository.UserRepository;
import com.quot.user.micro.service.test.users.validators.UserIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserIdValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserIdValidator userValidator){
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public boolean delete(UserId userId) throws Exception {
        if (!userValidator.isValid(userId)) throw new InvalidUserException();
        return userRepository.delete(userId);
    }

    public User save(User user) throws Exception {
        if (!userValidator.isValid(user.getId())) throw new InvalidUserException();
        return userRepository.save(user);
    }

    public User update(User user) throws Exception {
        if (!userValidator.isValid(user.getId())) throw new InvalidUserException();
        return userRepository.update(user);
    }

    public User get(UserId userId) throws Exception {
        if (!userValidator.isValid(userId)) throw new InvalidUserException();
        return userRepository.get(userId);
    }

}
