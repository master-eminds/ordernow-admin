package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Admin;

public interface AdminService {
    void save(Admin admin);
    Admin findByUsername(String username);
}
