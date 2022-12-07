package web_scraping;

import Firebase.CRUDFirebase;
import Firebase.ConexionAFirebase;

import java.util.Map;
import java.util.stream.Collectors;

public class Main_scrape {
    public static void main(String[] args) {
        ConexionAFirebase baseDeDatos = new ConexionAFirebase();
        Conseguir_los_juegos_en_oferta scraper = new Conseguir_los_juegos_en_oferta();
        scraper.obtenerOfertasdeGog();
        scraper.obtenerOfertasSteam();
        System.out.println("Registros terminados, empezando subida...");
        baseDeDatos.conectar();
        if(scraper.getJuegos_ofertados().size() != 0){
            for (Map.Entry<String, Object> entry : scraper.getJuegos_ofertados().entrySet()) {
                baseDeDatos.insertarDatos("Juegos_rebajados", entry.getKey(), (Map<String, Object>) scraper.getJuegos_ofertados().get(entry.getKey()));
            }
        }
        if(scraper.getJuegos_gratis().size() != 0){
            for (Map.Entry<String, Object> entry:scraper.getJuegos_gratis().entrySet()){
                baseDeDatos.insertarDatos("Juegos_gratis", entry.getKey(), (Map<String, Object>) scraper.getJuegos_gratis().get(entry.getKey()));
            }
        }
        System.out.println("Subida de datos terminada, Base de datos lista para ser usada");
    }
    public static void actualizarBaseDeDatos(ConexionAFirebase baseDeDatos, Conseguir_los_juegos_en_oferta scraper){
        baseDeDatos.eliminarTabla("Juegos_rebajados", 1);
        baseDeDatos.eliminarTabla("Juegos_gratis", 1);
        scraper.limpiarOfertas();
        scraper.obtenerOfertasSteam();
        scraper.obtenerOfertasdeGog();
        if(scraper.getJuegos_ofertados().size() != 0){
            for (Map.Entry<String, Object> entry : scraper.getJuegos_ofertados().entrySet()) {
                baseDeDatos.insertarDatos("Juegos_rebajados", entry.getKey(), (Map<String, Object>) scraper.getJuegos_ofertados().get(entry.getKey()));
            }
        }
        if(scraper.getJuegos_gratis().size() != 0){
            for (Map.Entry<String, Object> entry:scraper.getJuegos_gratis().entrySet()){
                baseDeDatos.insertarDatos("Juegos_gratis", entry.getKey(), (Map<String, Object>) scraper.getJuegos_gratis().get(entry.getKey()));
            }
        }
    }
}
