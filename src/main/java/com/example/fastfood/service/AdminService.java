package com.example.fastfood.service;

import com.example.fastfood.model.entity.Admin;

public interface AdminService {
    Admin login(String username);

    boolean checkAdminByUsername(String username);
}
