package Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.IOException;

public class Conexion {

    public Firestore conectar(){

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(getClass().
                            getResourceAsStream("celestine-25909-firebase-adminsdk-o6xkh-55c1ac1dbd.json")))
                    .setDatabaseUrl("https://celestine-25909-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return FirestoreClient.getFirestore();

    }

}
