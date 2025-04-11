package com.advaya.userservice.controllers;

import com.advaya.userservice.dtos.LoginRequestDTO;
import com.advaya.userservice.dtos.LogoutRequestDTO;
import com.advaya.userservice.dtos.SignUpRequestDTO;
import com.advaya.userservice.dtos.UserDTO;
import com.advaya.userservice.models.Token;
import com.advaya.userservice.models.User;
import com.advaya.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {

    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDTO signup(@RequestBody SignUpRequestDTO requestDTO )
    {

        User user  = userService.signUp(requestDTO.getEmail(), requestDTO.getPassword(), requestDTO.getName());

        return UserDTO.from(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        Token token = userService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        return token;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO logoutRequestDTO)
    {
        User user = userService.logout(logoutRequestDTO.getToken());

        if(user == null)
        {
            return null;
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/validate/{token}")
    public UserDTO validateToken(@PathVariable String token)
    {

        User user = userService.validateToken(token);

        if(user == null)
        {
            return null;
        }

        return UserDTO.from(user);
    }


    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id)
    {
        return null;
    }
}
