package com.eve.repository;

import com.eve.entity.Role;
import com.eve.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    User findRoleById(Long id);
}
