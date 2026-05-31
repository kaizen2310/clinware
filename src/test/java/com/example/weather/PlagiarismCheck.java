package com.example.weather;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PlagiarismCheck {

    public static String getLogicHash(String filePath) throws IOException, NoSuchAlgorithmException {
        String content = Files.readString(Paths.get(filePath));
        // Remove whitespace and comments to get core logic
        String logicOnly = content.replaceAll("//.*|/\\*.*?\\*/", "").replaceAll("\\s+", "");
        
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(logicOnly.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }
}
