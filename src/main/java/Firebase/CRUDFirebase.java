package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import web_scraping.Juegos_gratis;
import web_scraping.Juegos_oferta;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class CRUDFirebase {

    private static Firestore bd = null;
    public CRUDFirebase(){
        Conexion conexion = new Conexion();
        bd = conexion.iniciarFirebase();

    }

    public static boolean agregarJuegosGratis(String nombre, String sitio_web, String imagen){
        boolean key = false;
        try {
            Juegos_gratis juegos_gratis =
                    new Juegos_gratis(nombre, sitio_web, imagen);
            ApiFuture<WriteResult> future = bd.collection("juegos").document("juegos_gratis").set(juegos_gratis);
            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return key;
    }
    public static boolean agregarJuegosOferta(String nombre, String precio, String sitio_web, String imagen){
        boolean key = false;
        try {
            Juegos_oferta juegos_oferta =
                    new Juegos_oferta(nombre, precio, sitio_web, imagen);
            ApiFuture<WriteResult> future = bd.collection("juegos").document("juegos_ofertas").set(juegos_oferta);
            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return key;
    }


}
