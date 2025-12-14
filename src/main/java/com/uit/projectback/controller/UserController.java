package com.uit.projectback.controller;

import com.uit.projectback.dto.UserRegisterDto;
import com.uit.projectback.dto.UserLoginDto;
import com.uit.projectback.model.User;
import com.uit.projectback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Permet au front Android d'accéder
public class UserController {

    @Autowired
    private UserService userService;

    // Register
    @PostMapping("/register")
    public User register(@RequestBody UserRegisterDto dto) {
        return userService.register(dto);
    }

    // Login
    @PostMapping("/login")
    public User login(@RequestBody UserLoginDto dto) {
        return userService.login(dto);
    }
}
