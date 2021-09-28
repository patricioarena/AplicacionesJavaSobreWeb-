package com.example.demo.DataAccess.DbFirebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class CloudFirestore {

    @PostConstruct
    public void Initialize(){
        try {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\patri\\source\\repos\\IdeaProjects\\demo\\serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://mobile-292605.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public Firestore context() {
        return FirestoreClient.getFirestore();
    }

}
