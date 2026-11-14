package com.example.wallet.service;
import com.example.wallet.dto.UserRegisterRequest;
import com.example.wallet.dto.UserResponse;
import com.example.wallet.entity.User;
import com.example.wallet.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<UserResponse> getUserById(Long id){
        return userRepository.findById(id).map(user->new UserResponse(
                user.getId(),user.getUsername(),user.getName(),user.getEmail(),user.getCreatedAt()
        ));
    }
    public UserResponse registerUser(UserRegisterRequest userRegisterRequest){
        if(userRepository.existsByEmail(userRegisterRequest.getEmail())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email Already Registered"
            );
        }
        String hashedPassword= passwordEncoder.encode(userRegisterRequest.getPassword());
        User user=new User(userRegisterRequest.getUsername(),userRegisterRequest.getEmail(),userRegisterRequest.getName(),
                hashedPassword);
        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse(savedUser.getId(),savedUser.getUsername(),
                                    savedUser.getName(),savedUser.getEmail(),savedUser.getCreatedAt());
        return userResponse;
    }
}
