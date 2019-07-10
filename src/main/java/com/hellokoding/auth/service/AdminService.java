package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Admin;

public interface AdminService {
    void save(Admin admin) throws Exception;
    void update(Admin admin);
    Admin findByUsername(String username);

    Admin findById(Long id);
}
