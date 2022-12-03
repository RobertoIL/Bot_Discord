package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import web_scraping.Juegos_gratis;
import web_scraping.Juegos_oferta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CRUDFirebase {

    private static Firestore bd = null;
    public CRUDFirebase(){
        Conexion conexion = new Conexion();
        bd = conexion.iniciarFirebase();
    }

    public static boolean agregarJuegosGratis(Juegos_gratis juegos_gratis){
        boolean key = false;
        try {
            ApiFuture<WriteResult> future = bd.collection("juegos").document("juegos_gratis").set(juegos_gratis);
            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return key;
    }
    public static boolean agregarJuegosOferta(Juegos_oferta juegos_oferta){
        boolean key = false;
        try {
            ApiFuture<WriteResult> future = bd.collection("juegos").document("juegos_ofertas").set(juegos_oferta);
            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return key;
    }
    public static boolean obtenerJuegosGratis() {
        boolean key = false;
        Gson gson = new GsonBuilder().create();
        FileWriter fileWriter;
        try {
            ApiFuture<QuerySnapshot> future = bd.collection("juegos_gratis").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            fileWriter = new FileWriter("datos/juegos_gratis.json");
            for (QueryDocumentSnapshot document : documents) {
                gson.toJson(new Juegos_gratis((String) document.getData().get("nombre"),
                        (String) document.getData().get("sitio_web"),
                        (String) document.getData().get("imagen")), fileWriter);
            }
            key = true;
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("error al crear el archivo");
        }
        return key;
    }


}
