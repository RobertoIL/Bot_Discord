package Firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;
import web_scraping.Conseguir_los_juegos_en_oferta;
import web_scraping.Juegos_gratis;
import web_scraping.Juegos_oferta;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConexionAFirebase {
    static Firestore bd;

    Conseguir_los_juegos_en_oferta scraper = new Conseguir_los_juegos_en_oferta();
    public void conectar(){
        try {
            InputStream serviceAccount = new FileInputStream("" +
                    "src/main/java/Firebase/config.json");
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
    public String[] devolverKeys(String nombre_tabla){
        try {
            ApiFuture<QuerySnapshot> query = bd.collection(nombre_tabla).get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            ArrayList<String> devolver = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                devolver.add(document.getId());
            }
            String [] arregloDeKeys = new String[devolver.size()];
            arregloDeKeys = devolver.toArray(arregloDeKeys);
            return arregloDeKeys;
        }catch (Exception e){
            e.printStackTrace();
            return new String[0];
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
    public List<Juegos_oferta> devolverJuegosEnOferta(){
        List<Juegos_oferta> objetos = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = bd.collection("juegos_oferta").get();
// future.get() blocks on response
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                objetos.add(document.toObject(Juegos_oferta.class));
            }
            return objetos;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Juegos_gratis> devolverJuegosGratis(){
        List<Juegos_gratis> objetos = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = bd.collection("juegos_gratis").get();
// future.get() blocks on response
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                objetos.add(document.toObject(Juegos_gratis.class));
            }
            return objetos;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void terminarConexion(){
        bd.shutdown();
    }
    public void generarJuegos(int cantidad){
        scraper.limpiarOfertas();
        if(cantidad != 0){
            scraper.obtenerOfertasSteam(cantidad);
            scraper.obtenerOfertasdeGog(cantidad);
        }else{
            scraper.obtenerOfertasdeGog();
            scraper.obtenerOfertasSteam();
        }
        eliminarTabla("juegos_ofertas", 1);
        eliminarTabla("juegos_gratis", 1);
        List<String> llaves = scraper.getJuegos_ofertados().keySet().stream().toList();
        if(scraper.getJuegos_ofertados().size() != 0){
            for (Map.Entry<String, Object> entry : scraper.getJuegos_ofertados().entrySet()) {
                insertarDatos("Juegos_rebajados", entry.getKey(), (Map<String, Object>) scraper.getJuegos_ofertados().get(entry.getKey()));
            }
        }
        if(scraper.getJuegos_gratis().size() != 0){
            for (Map.Entry<String, Object> entry:scraper.getJuegos_gratis().entrySet()){
                insertarDatos("Juegos_gratis", entry.getKey(), (Map<String, Object>) scraper.getJuegos_gratis().get(entry.getKey()));
            }
        }
    }
}
