package com.quot.user.micro.service.test.users;

import com.quot.user.micro.service.test.users.model.User;
import com.quot.user.micro.service.test.users.model.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{userId}/{clientId}")
    public User get(@PathVariable("userId") String userId, @PathVariable("clientId") String clientId) throws Exception {
        UserId requestedUserId = new UserId(userId, clientId);

        return userService.get(requestedUserId);
    }

    @PostMapping()
    public User upsert(@RequestBody User user) throws Exception {

        return userService.save(user);
    }

    @DeleteMapping("/{userId}/{clientId}")
    public void upsert(@PathVariable("userId") String userId, @PathVariable("clientId") String clientId) throws Exception {
        UserId requestedUserId = new UserId(userId, clientId);
        userService.delete(requestedUserId);
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    public void fileNotFound(){}

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUserException.class)
    public void invalidModel(){}
}
