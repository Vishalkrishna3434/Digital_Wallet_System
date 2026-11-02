package com.example.wallet.service;
import com.example.wallet.entity.User;
import com.example.wallet.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    private Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
