package bd;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;


public class FirebaseConfig{
    public static Firestore db;

    public static void conectar() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/celestine-25909-firebase-adminsdk-o6xkh-55c1ac1dbd.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://celestine-25909-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
        System.out.println("conexion exitosa");

    }

}
