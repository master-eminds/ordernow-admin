package com.hellokoding.auth.util;

import com.hellokoding.auth.model.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.*;

public class Global {
    public static String AES="AES";
    public static String cheieCriptare="criptare";
    public static Admin admin=new Admin();
    public static Rol rol;
    public static List<Comanda> listaComenzi= new ArrayList<>();


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

    public static List<Comanda> listaComenziUltimaSaptamana=new ArrayList<>();
    public static List<Comanda> listaComenziUltimeleLuni=new ArrayList<>();
//    public static Map<String, Integer> mapUltimaSaptamanAsync;
public static List<Ospatar> listaOspatari=new ArrayList<>();
    public static List<Masa> listaMese=new ArrayList<>();
    public static Double valoareTotala=0.0;
    public static Map<String,Integer> comenziPeZile= new HashMap<>();
    public static Map<String,Integer> comenziPeLuna= new HashMap<>();
    public static Map<String,Double> valoriPeLuna= new HashMap<>();
    public static List<CountProdus> produseComandate;
    public static String dateChartComenziTotal ="";
    public static String dateChartComenziUS="";
    public static String dateChartComenziUL="";
    public static String dateChartReviewProduse="";
    public static Map<Long,Double> noteProduse= new HashMap<>();
    public static Map<Long,Double> noteOspatari= new HashMap<>();

    public static Map<Long,List<Review>> reviewProduse= new HashMap<>();
    public static Map<Long,List<Review>> reviewOspatari= new HashMap<>();

    private static Map<Long,Double> medieProdus=new HashMap<>();
    public static String dateChartOspatari="";

    public static List<Comanda> toateComenzile=new ArrayList<>();

    public static String dateChartCeleMaiComandateProd="";


    //Meniuri
    public static List<Meniu> listaMeniuri=new ArrayList<>();
    public static List<Produs> listaProduse=new ArrayList<>();
    public static List<Categorie> listaCategorii=new ArrayList<>();
    public static Map<Long, List<Categorie>> mapCategoriiByMeniu= new HashMap<Long, List<Categorie>>();
    public static Map<Long, List<Produs>> mapProduseByCategorie= new HashMap<Long, List<Produs>>();











}
