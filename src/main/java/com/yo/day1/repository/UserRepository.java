package com.yo.day1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yo.day1.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}