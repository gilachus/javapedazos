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
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author user
 */
public class MD5 {
    
    public static String toEncrypt(String toEncrypt){
        String strEncrypt = "";
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] keypass = md5.digest("ggGWww".getBytes("utf-8"));
            byte[] BytesK = Arrays.copyOf(keypass, 24);
            SecretKey k = new SecretKeySpec(BytesK, "DESede");
            Cipher c = Cipher.getInstance("DESede");
            c.init(Cipher.ENCRYPT_MODE, k);
            byte[] plain = toEncrypt.getBytes("UTF-8");
            byte[] buf = c.doFinal(plain);
            byte[] b64 = Base64.getEncoder().encode(buf); 
            strEncrypt = new String(b64);
        }catch(Exception ex){
            System.out.println("error 'setdata'");
        }
        
        return strEncrypt;
    }
    
    public static String toDecrypt(String value){
        String pass = "";
        try{
            byte[] b64 = Base64.getDecoder().decode(value.getBytes("utf-8"));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] keypass = md5.digest("ggGWww".getBytes("utf-8"));
            byte[] BytesK = Arrays.copyOf(keypass, 24);
            SecretKey k = new SecretKeySpec(BytesK, "DESede");
            Cipher c = Cipher.getInstance("DESede");
            c.init(Cipher.DECRYPT_MODE, k);
            byte[] buf = c.doFinal(b64);
            pass = new String(buf, "utf-8");
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("error 'getdata'");
        }
        return pass;
    }
}
