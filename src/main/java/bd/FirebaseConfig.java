package bd;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;


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

    public static boolean insertarDatos(String colleccion,
                                     String documento,
                                     Map<String, Object> data){

        try{
            DocumentReference docRef = db.collection(colleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Update time: " + result.get().getUpdateTime());
            return true;
        } catch(Exception e){
            System.out.println(e);
        }
        return false;

    }

}
