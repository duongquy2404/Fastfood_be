package com.example.fastfood.service.impl;

import com.example.fastfood.model.entity.Admin;
import com.example.fastfood.repository.AdminRepository;
import com.example.fastfood.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Admin login(String username) {
        return adminRepository.findAdminByUsername(username);
    }
}
