package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.util.Map;

public class CRUDFirebase {
    private static Firestore db = null;

    public CRUDFirebase(){
        Conexion conexion = new Conexion();
        db = conexion.conectar();
    }

    public static boolean insertarDatos(String colleccion, String documento, Map<String, Object> data){
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
