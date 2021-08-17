package com.test.cbback.service;

import com.test.cbback.model.User;
import com.test.cbback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User createUser(String email) {
        User newUser = new User();
        newUser.setEmail(email);
        return this.userRepository.save(newUser);
    }
}
