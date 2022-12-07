package Firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ConexionAFirebase {
    static Firestore bd;
    public void conectar(){
        try {
            InputStream serviceAccount = new FileInputStream("src/main/java/Firebase/config.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
            bd = FirestoreClient.getFirestore();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void insertarDatos(String nombre_tabla, String identificadorFila, Map<String, Object> campos){
        try {
            DocumentReference docRef = bd.collection(nombre_tabla).document(identificadorFila);
            ApiFuture<WriteResult> result = docRef.set(campos);
            System.out.println("Update time : " + result.get().getUpdateTime());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void MostrarDatos(String nombre_tabla){
        try {
            ApiFuture<QuerySnapshot> query = bd.collection(nombre_tabla).get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                System.out.println("Juego: " + document.getId());
                System.out.println(document.get("Nombre_Juego"));
                System.out.println(document.get("Precio_Juego"));
                System.out.println(document.get("Enlace_Al_Juego"));
                System.out.println(document.get("Imagen"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void eliminarTabla(String collection, int batchSize){
        try {
            ApiFuture<QuerySnapshot> future = bd.collection(collection).limit(batchSize).get();
            int deleted = 0;
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                document.getReference().delete();
                ++deleted;
            }
            if (deleted >= batchSize) {
                eliminarTabla(collection, batchSize);
            }
        } catch (Exception e) {
            System.err.println("Error deleting collection : " + e.getMessage());
        }
    }
    public void getDocument(String coleccion, Map<String, Object> juegos) {
        try {
            ApiFuture<QuerySnapshot> future = bd.collection(coleccion).get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                juegos.put(document.getId(), document.getData());
            }

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void terminarConexion(){
        bd.shutdown();
    }
}
