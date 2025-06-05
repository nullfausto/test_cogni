package com.example.profile_logging_demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.profile_logging_demo.model.User;;


@Service
public class UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<User> getAllUsers(){
        logger.debug("Fetching all users");
        return new ArrayList<>(users);
    }

    public User getUserById(Long id) {
        logger.debug("Fetching user with id: {}", id);
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new RuntimeException("User not found");
                });
    }

    public User createUser(User user){
        logger.info("Creating new user: {}",user);
        User newUser = new User(counter.incrementAndGet(), user.getName(), user.getEmail());
        users.add(newUser);
        return newUser;
    }

    public void deleteUser(Long id){
        logger.warn("Deleting user with id: {}",id);
        users.removeIf(user -> user.getId().equals(id));
    }

}
