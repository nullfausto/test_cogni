package com.example.profile_logging_demo.controller;

import com.example.profile_logging_demo.model.User;
import com.example.profile_logging_demo.service.UserService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        logger.debug("Received request to get all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        logger.debug("Received request to get user with id: {}",id);
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        logger.info("Received request to create new user");
        return userService.createUser(user);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        logger.warn("Received request to delete user with id: {}", id);
        userService.deleteUser(id);
    }

    @GetMapping("/test")
    public String test() {
        logger.error("ERROR TEST");
        logger.warn("WARN TEST");
        logger.info("INFO TEST");
        logger.debug("DEBUG TEST");
        logger.trace("TRACE TEST");
        return "Check logs!";
    }
    
    

}

