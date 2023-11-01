package com.example.fastfood.repository;

import com.example.fastfood.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAdminByUsername(String username);
}
