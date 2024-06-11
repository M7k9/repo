package com.m7k.otp.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.m7k.otp.Entity.MyUser;
import com.m7k.otp.Repository.UserRepository;

@Service
public class UserService {
    public final UserRepository userRepository; 

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MyUser createUser(MyUser user){
            return userRepository.save(user);
        
    }
    public Optional<MyUser> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
