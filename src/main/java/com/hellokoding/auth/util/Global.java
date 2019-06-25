package com.hellokoding.auth.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class Global {
    public static String AES="AES";
    public static String cheieCriptare="criptare";


    public static String criptare(String data, String password) throws Exception{
        SecretKeySpec key= generateKey(password);
        Cipher cipher=Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] encrypted= cipher.doFinal(data.getBytes());
        String encryptedValue= Base64.getEncoder().encodeToString(encrypted);
        return encryptedValue;
    }
    public static String decriptare(String data, String password) throws Exception {
        SecretKeySpec key= generateKey(password);
        Cipher cipher=Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] decodedValue= Base64.getDecoder().decode(data);
        byte[] decVal=cipher.doFinal(decodedValue);
        String valoare= new String(decVal);
        return valoare;
    }
    public static SecretKeySpec generateKey(String password) throws Exception{
        final MessageDigest digest= MessageDigest.getInstance("SHA-256");
        byte[] bytes= password.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key=digest.digest();
        SecretKeySpec secretKeySpec= new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }
}
