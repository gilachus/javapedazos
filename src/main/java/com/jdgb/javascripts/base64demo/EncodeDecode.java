/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdgb.javascripts.base64demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class EncodeDecode {
    public static void main(String[] args) {
        try {
            encodeImage("C:\\Users\\user\\Videos\\yuls\\cake.png");
            decodeImage("testimageb64.txt");
        } catch (IOException ex) {
            Logger.getLogger(EncodeDecode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String encodeImage(String imgPath) throws FileNotFoundException, IOException{
         FileInputStream imageStream = new FileInputStream(imgPath);
        byte[] data = imageStream.readAllBytes();
        String imageString = Base64.getEncoder().encodeToString(data);
        
        FileWriter fileWriter = new FileWriter("testimageb64.txt");
        fileWriter.write(imageString);
        fileWriter.close();
        imageStream.close();
        return imageString;
    }
    
    private static void decodeImage(String txtPath) throws IOException{
        FileInputStream inputStream = new FileInputStream(txtPath);
        byte[] data = Base64.getDecoder().decode(new String(inputStream.readAllBytes()));
        
        FileOutputStream fileOuputStream = new FileOutputStream("decodeImage.jpg");
        fileOuputStream.write(data);
        fileOuputStream.close();
        inputStream.close();
    }
}
