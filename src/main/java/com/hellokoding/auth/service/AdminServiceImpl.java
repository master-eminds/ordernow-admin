package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Admin;
import com.hellokoding.auth.repository.RoleRepository;
import com.hellokoding.auth.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Base64;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private String key = "criptare";
    @Override
    public void save(Admin admin) {
        String password= admin.getPassword();
        admin.setPassword(bCryptPasswordEncoder.encode(password));
        try {
            admin.setParolaAndroid(criptare(password,key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        admin.setRoles(new HashSet<>(roleRepository.findAll()));
        adminRepository.save(admin);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    String AES="AES";

    private String criptare(String data, String password) throws Exception{
        SecretKeySpec key= generateKey(password);
        Cipher cipher=Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] encrypted= cipher.doFinal(data.getBytes());
        String encryptedValue= Base64.getEncoder().encodeToString(encrypted);
        return encryptedValue;
    }
    private String decriptare(String data, String password) throws Exception {
        SecretKeySpec key= generateKey(password);
        Cipher cipher=Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] decodedValue= Base64.getDecoder().decode(data);
        byte[] decVal=cipher.doFinal(decodedValue);
        String valoare= new String(decVal);
        return valoare;
    }
    private SecretKeySpec generateKey(String password) throws Exception{
        final MessageDigest digest= MessageDigest.getInstance("SHA-256");
        byte[] bytes= password.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key=digest.digest();
        SecretKeySpec secretKeySpec= new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }
}
