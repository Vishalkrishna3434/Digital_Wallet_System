package com.example.wallet.controller;

import com.example.wallet.dto.UserRegisterRequest;
import com.example.wallet.dto.UserResponse;
import com.example.wallet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        UserResponse user = userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));
        return ResponseEntity.ok(user);
    }

    @PostMapping
     public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest){
        UserResponse user = userService.registerUser(userRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
