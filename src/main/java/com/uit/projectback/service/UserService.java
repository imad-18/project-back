package com.uit.projectback.service;

import com.uit.projectback.dto.LoginResponseDto;
import com.uit.projectback.dto.UserRegisterDto;
import com.uit.projectback.dto.UserLoginDto;
import com.uit.projectback.dto.UserUpdateDto;
import com.uit.projectback.model.User;
import com.uit.projectback.repository.UserRepository;
import com.uit.projectback.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User register(UserRegisterDto dto) {
        // Vérifier si l'email existe déjà
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // 🔒 Hash du mot de passe
        user.setLanguage(dto.getLanguage());
        user.setCountry(dto.getCountry());
        user.setCurrency(dto.getCurrency());

        return userRepository.save(user);
    }

    public LoginResponseDto login(UserLoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }

        // Générer le token JWT
        String token = jwtUtil.generateToken(user.getEmail(), user.getUser_id());

        // Ne pas renvoyer le mot de passe
        user.setPassword(null);

        return new LoginResponseDto(token, user);
    }

    public User updateProfile(Integer userId, UserUpdateDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifier le mot de passe actuel si fourni
        if (dto.getCurrentPassword() != null && !dto.getCurrentPassword().isEmpty()) {
            if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
                throw new RuntimeException("Mot de passe actuel incorrect");
            }
        }

        // Mise à jour des champs
        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getEmail() != null) {
            // Vérifier que le nouvel email n'est pas déjà utilisé
            Optional<User> existing = userRepository.findByEmail(dto.getEmail());
            if (existing.isPresent() && !existing.get().getUser_id().equals(userId)) {
                throw new RuntimeException("Email déjà utilisé");
            }
            user.setEmail(dto.getEmail());
        }
        if (dto.getLanguage() != null) user.setLanguage(dto.getLanguage());
        if (dto.getCountry() != null) user.setCountry(dto.getCountry());
        if (dto.getCurrency() != null) user.setCurrency(dto.getCurrency());

        // Changer le mot de passe si fourni
        if (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        }

        User updated = userRepository.save(user);
        updated.setPassword(null); // Ne pas renvoyer le mot de passe
        return updated;
    }

    public User getUserProfile(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        user.setPassword(null); // Ne jamais renvoyer le mot de passe
        return user;
    }
}