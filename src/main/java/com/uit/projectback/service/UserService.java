package com.uit.projectback.service;

import com.uit.projectback.dto.UserRegisterDto;
import com.uit.projectback.dto.UserLoginDto;
import com.uit.projectback.model.User;
import com.uit.projectback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register
    public User register(UserRegisterDto dto) {
        // Vérifie si email existe
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé !");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // hash
        user.setLanguage(dto.getLanguage());
        user.setCountry(dto.getCountry());
        user.setCurrency(dto.getCurrency());
        return userRepository.save(user);
    }

    // Login
    public User login(UserLoginDto dto) {
        Optional<User> userOpt = userRepository.findByEmail(dto.getEmail());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Email ou mot de passe invalide !");
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email ou mot de passe invalide !");
        }
        return user;
    }
}
