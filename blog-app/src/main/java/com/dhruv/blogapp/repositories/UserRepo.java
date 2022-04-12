package com.dhruv.blogapp.repositories;

import com.dhruv.blogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String email);

    public Optional<User> findByUsernameOrEmail(String username, String email);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
