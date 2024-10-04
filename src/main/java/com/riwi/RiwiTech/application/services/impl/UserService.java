package com.riwi.RiwiTech.application.services.impl;

import com.riwi.RiwiTech.application.dtos.exception.UnauthorizedAccessException;
import com.riwi.RiwiTech.application.dtos.requests.UserWithoutId;
import com.riwi.RiwiTech.application.dtos.requests.UserWithoutIdAndRole;
import com.riwi.RiwiTech.domain.entities.User;
import com.riwi.RiwiTech.domain.enums.Role;
import com.riwi.RiwiTech.domain.ports.service.IUserService;
import com.riwi.RiwiTech.infrastructure.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User create(UserWithoutId userDTO) {
        if (!isAdmin()) {
            throw new UnauthorizedAccessException("No estás autorizado para crear un usuario.");
        }

        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(encryptPassword(userDTO.getPassword()))
                .role(userDTO.getRole())
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public User register(UserWithoutIdAndRole userDTO) {

        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(encryptPassword(userDTO.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        if (!isAdmin()) {
            throw new UnauthorizedAccessException("No estás autorizado para crear un usuario.");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<User> readAll() {
        if (!isAdmin()) {
            throw new UnauthorizedAccessException("No estás autorizado para crear un usuario.");
        }
        return userRepository.findAll();
    }

    @Override
    public Optional<User> readById(Long id) {
        if (!isAdmin()) {
            throw new UnauthorizedAccessException("No estás autorizado para crear un usuario.");
        }
        return userRepository.findById(id);
    }

    @Override
    public User update(Long id, UserWithoutId userDTO) {

        if (!isAdmin()) {
            throw new UnauthorizedAccessException("No estás autorizado para crear un usuario.");
        }

        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(encryptPassword(userDTO.getPassword()))
                .role(userDTO.getRole())
                .build();

        userRepository.save(user);
        return user;
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password); // Encripta la contraseña
    }

    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Role.ADMIN.name()));
    }
}
