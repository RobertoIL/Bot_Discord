package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CRUDFirebase {

    private static Firestore bd = null;
    public CRUDFirebase(){
        Conexion conexion = new Conexion();
        bd = conexion.iniciarFirebase();
    }

    public boolean crearDocumento(Map<String, Object> docData, String nombre_documento, String collection){
        boolean key = false;
            try{
                ApiFuture<WriteResult> future = bd.collection(collection).document(nombre_documento).set(docData);
                System.out.println("Update time : " + future.get().getUpdateTime());

                key = true;
            }catch(InterruptedException | ExecutionException e){
                e.printStackTrace();
            }

        return key;
    }

    public boolean actualizarDocumento(String coleccion, String documento, HashMap<String, Object>doc){
        boolean key = false;
        try{
            DocumentReference docRef = bd.collection(coleccion).document(documento);

            ApiFuture<WriteResult> future = docRef.update(doc);

            WriteResult result = future.get();
            System.out.println("Write result: " + result);
            key = true;
        }
        catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return key;
    }
    public boolean getDocument(String coleccion, Map<String, Object> juegos) {
        boolean key = false;
        try {
            ApiFuture<QuerySnapshot> future = bd.collection(coleccion).get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                juegos.put(document.getId(), document.getData());
            }
            key = true;

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return key;
    }



}
