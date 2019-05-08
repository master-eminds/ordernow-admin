package com.hellokoding.auth.service;

import com.hellokoding.auth.model.MisUsers;

import java.util.List;

public interface MisUsersService {

    void save(MisUsers misUsers);

    MisUsers findById(Long id);
    List<MisUsers> findAll();
    MisUsers findByEmailPass(String email,String pass);
}
