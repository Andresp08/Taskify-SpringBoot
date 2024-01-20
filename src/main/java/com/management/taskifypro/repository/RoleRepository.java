package com.management.taskifypro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.taskifypro.model.entity.Role;
import com.management.taskifypro.model.enums.ERole;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByName(ERole name);
}
