package com.uece.horas_complementares.util;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@AllArgsConstructor
public class ImageToBase64Converter {
    public static String convertImageToBase64(String imagePath) {
        try {
            File file = new File("F://sisgehc/back novo/horas-complemantares-API/src/main/resources"+imagePath);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            // e.printStackTrace();
            return null; // Ou lançar uma exceção personalizada
        }
    }
}
