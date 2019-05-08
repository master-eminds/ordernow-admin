package com.hellokoding.auth.service;

import com.hellokoding.auth.model.MisUsers;
import com.hellokoding.auth.repository.MisUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MisUsersServiceImpl implements MisUsersService {

    @Autowired
    MisUsersRepository misUsersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void save(MisUsers misUsers) {
        misUsers.setPass(bCryptPasswordEncoder.encode(misUsers.getPass()));
        misUsersRepository.save(misUsers);
    }

    @Override
    public MisUsers findById(Long id) {
        return misUsersRepository.findOne(id);
    }

    @Override
    public List<MisUsers> findAll() {
        return misUsersRepository.findAll();
    }


    @Override
    public MisUsers findByEmailPass(String email, String pass) {
        MisUsers misUsersOn = null;
        List<MisUsers> misUsersesList = misUsersRepository.findAll();
        for(MisUsers m : misUsersesList){
            if(email.equals(m.getEmail())){
                if(pass.equals(m.getPass()))
                    misUsersOn = m;
            }
        }
        return misUsersOn;
    }
}
