package com.example.demo.Repo;

import com.example.demo.DB.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {
    
}
