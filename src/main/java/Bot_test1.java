
import Firebase.CRUDFirebase;
import Firebase.ConexionAFirebase;
import commands.ComandoJuegosGratis;
import commands.ComandoOfertas;
import listeners.EventoReaccion;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import web_scraping.Conseguir_los_juegos_en_oferta;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class Bot_test1 {
    public static void main(String[] args) throws InterruptedException {
        ConexionAFirebase baseDeDatos = new ConexionAFirebase();
        Conseguir_los_juegos_en_oferta scraper = new Conseguir_los_juegos_en_oferta();
        scraper.obtenerOfertasdeGog();
        scraper.obtenerOfertasSteam();
        System.out.println("Registros terminados, empezando subida...");
        baseDeDatos.conectar();
        if(scraper.getJuegos_ofertados().size() != 0){
            for (Map.Entry<String, Object> entry : scraper.getJuegos_ofertados().entrySet()) {
                baseDeDatos.insertarDatos("juegos_ofertas", entry.getKey(), (Map<String, Object>) scraper.getJuegos_ofertados().get(entry.getKey()));
            }
        }
        if(scraper.getJuegos_gratis().size() != 0){
            for (Map.Entry<String, Object> entry:scraper.getJuegos_gratis().entrySet()){
                baseDeDatos.insertarDatos("juegos_gratis", entry.getKey(), (Map<String, Object>) scraper.getJuegos_gratis().get(entry.getKey()));
            }
        }
        System.out.println("Subida de datos terminada, Base de datos lista para ser usada");

        //el token se genera desde la cuenta de discord donde esta el bot
        final String token = "";

        JDA jda  =JDABuilder.createDefault(token)
                    .addEventListeners(new EventoReaccion())
                    .addEventListeners(new ComandoJuegosGratis())
                    .addEventListeners(new ComandoOfertas())
                    .build().awaitReady();

        jda.upsertCommand("juegos-gratis", "Mostrar juegos destacados gratuitos").queue();

        jda.upsertCommand("ofertas", "Mostrar ofertas destacadas de viedeojuegos").queue();

    }

    public static void actualizarBaseDeDatos(ConexionAFirebase baseDeDatos, Conseguir_los_juegos_en_oferta scraper){
        baseDeDatos.eliminarTabla("juegos_ofertas", 1);
        baseDeDatos.eliminarTabla("juegos_gratis", 1);
        scraper.limpiarOfertas();
        scraper.obtenerOfertasSteam();
        scraper.obtenerOfertasdeGog();
        if(scraper.getJuegos_ofertados().size() != 0){
            for (Map.Entry<String, Object> entry : scraper.getJuegos_ofertados().entrySet()) {
                baseDeDatos.insertarDatos("juegos_ofertas", entry.getKey(), (Map<String, Object>) scraper.getJuegos_ofertados().get(entry.getKey()));
            }
        }
        if(scraper.getJuegos_gratis().size() != 0){
            for (Map.Entry<String, Object> entry:scraper.getJuegos_gratis().entrySet()){
                baseDeDatos.insertarDatos("juegos_gratis", entry.getKey(), (Map<String, Object>) scraper.getJuegos_gratis().get(entry.getKey()));
            }
        }
    }

}
