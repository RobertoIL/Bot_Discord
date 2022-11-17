package db;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;

public class FirebaseConfig {

    public static void conectar() {
        FileInputStream serviceAccount;
        try{
            serviceAccount =
                new FileInputStream("path/to/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://celestine-25909-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        }
        catch(Exception e){
            System.out.println(""+e);
        }

    }


}
