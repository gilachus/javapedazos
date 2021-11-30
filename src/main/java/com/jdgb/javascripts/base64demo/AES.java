/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdgb.javascripts.base64demo;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author user
 */
public class AES {
    public static String toEncrypt(String toEncrypt){
        try{
            SecretKeySpec sks = createKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            byte [] toEncryptbyte = toEncrypt.getBytes("UTF-8");
            byte [] strByteEncrypted = cipher.doFinal(toEncryptbyte);
            String strEncrypted = Base64.getEncoder().encodeToString(strByteEncrypted);
            return strEncrypted;
        }catch(Exception ex){
            System.out.println("error 'setdata'");
            return "";
        }
    }
    
    /**
     * Se usa principalmente para desencriptar el password
     */
    public static String toDecrypt(String toDecrypt){
        
        try{
            SecretKeySpec sks = createKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte [] toDecryptbyte = Base64.getDecoder().decode(toDecrypt);
            byte [] strByteDecrypted = cipher.doFinal(toDecryptbyte);
            String strDecrypted = new String(strByteDecrypted);
            return strDecrypted;
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("error 'getdata'");
            return "";
        }
    }
    
    public static SecretKeySpec createKey(){
        String llave = "ttGetWorrk";
        try{
            byte [] cadena = llave.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            cadena = md.digest(cadena);
            cadena = Arrays.copyOf(cadena, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, "AES");
            return secretKeySpec;
        }catch (Exception e){
            return null;
        }
    }
}
