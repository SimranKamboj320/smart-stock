package com.jsp.controller;

import com.jsp.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        authenticationManager.authenticate(authToken);
        return ResponseEntity.ok("Login successful.");
    }
}