package com.uit.projectback.controller;

import com.uit.projectback.dto.LoginResponseDto;
import com.uit.projectback.dto.UserRegisterDto;
import com.uit.projectback.dto.UserLoginDto;
import com.uit.projectback.dto.UserUpdateDto;
import com.uit.projectback.model.User;
import com.uit.projectback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto dto) {
        try {
            User user = userService.register(dto);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto dto) {
        try {
            LoginResponseDto response = userService.login(dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        try {
            Integer userId = (Integer) authentication.getPrincipal();
            User user = userService.getUserProfile(userId);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            Authentication authentication,
            @RequestBody UserUpdateDto dto) {
        try {
            Integer userId = (Integer) authentication.getPrincipal();
            User user = userService.updateProfile(userId, dto);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Avec JWT, la déconnexion se fait côté client (suppression du token)
        return ResponseEntity.ok("Déconnexion réussie");
    }
}