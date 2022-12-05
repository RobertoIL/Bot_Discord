package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import web_scraping.Juegos_gratis;
import web_scraping.Juegos_oferta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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

    public boolean addFirebase(Map<String, Object> docData, String nombre_documento, String collection){
        boolean key = false;
            /*
            docData = new HashMap<>();
            docData.put("name", "Los Angeles");
            docData.put("state", "CA");
            docData.put("country", "USA");
            docData.put("regions", Arrays.asList("west_coast", "socal"));
                 */
            try{
                ApiFuture<WriteResult> future = bd.collection(collection).document(nombre_documento).set(docData);
                System.out.println("Update time : " + future.get().getUpdateTime());

                key = true;
            }catch(InterruptedException | ExecutionException e){
                e.printStackTrace();
            }

        return key;
    }


}
