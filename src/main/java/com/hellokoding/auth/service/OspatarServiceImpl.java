package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.repository.OspatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

@Service
public class OspatarServiceImpl implements OspatarService {

    @Autowired
    OspatarRepository ospatarRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private String key="criptare";
    @Override
    public void save(Ospatar ospatar) {

        try {
            ospatar.setParola(criptare(ospatar.getParola(),key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ospatar.setStatus("offline");
        ospatarRepository.save(ospatar);
    }


    @Override
    public Ospatar findById(Long id) {

        return ospatarRepository.findOne(id);
    }

    @Override
    public Ospatar findByEmail(String email) {
        return ospatarRepository.findByEmail(email);
    }

    @Override
    public List<Ospatar> findAll() {
        return ospatarRepository.findAll();
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
