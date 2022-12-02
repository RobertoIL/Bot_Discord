package Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.IOException;


public class Conexion {

    private Firestore bd = null;
    public Firestore iniciarFirebase(){

        Firestore bd = null;
        try{
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.
                            fromStream(getClass().
                                    getResourceAsStream("Firebase/celestine-ae15b-firebase-adminsdk-ej4dv-0941c34ad6.json")))
                    .build();
            FirebaseApp.initializeApp(options);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return FirestoreClient.getFirestore();
    }
}
