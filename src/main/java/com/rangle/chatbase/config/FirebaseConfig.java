package com.rangle.chatbase.config;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.http.AuthHttpConstants;
import com.google.auth.Credentials.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.github.cdimascio.dotenv.Dotenv;



@Configuration
public class FirebaseConfig {

    private static String readFirebaseDatabaseUrlFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath))).trim();
    }

    @Bean
    public FirebaseApp firebaseApp() throws IOException {

        FileInputStream serviceAccount = new FileInputStream(".env/firebase/service-account.json");
        String firebaseDatabaseUrl = readFirebaseDatabaseUrlFromFile(".env/firebase/firebase-database-url.txt");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(firebaseDatabaseUrl)
                .build();

        return FirebaseApp.initializeApp(options);
    }


}


