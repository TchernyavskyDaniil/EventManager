package com.eve.repository;

import com.eve.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(Long id);

    @Override
    void delete(User user);
}
