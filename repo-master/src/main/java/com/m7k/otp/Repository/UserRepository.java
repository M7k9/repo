package com.m7k.otp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m7k.otp.Entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByEmail(String email);
}
