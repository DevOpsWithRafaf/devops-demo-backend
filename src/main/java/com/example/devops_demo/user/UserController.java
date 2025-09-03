package com.example.devops_demo.user;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.devops_demo.security.JwtResponse;
import com.example.devops_demo.security.JwtUtil;
import com.example.devops_demo.user.dto.LoginDto;
import com.example.devops_demo.user.dto.RegisterUserDto;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody RegisterUserDto dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginDto dto) {

        User user = userService.getByUsername(dto.getUsername());

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getUsername(), user.getId());

        return new JwtResponse(token);
    }

    @GetMapping("/ok")
    public String  login() {
        return "OK";
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getByUsername(username);
    }
}
