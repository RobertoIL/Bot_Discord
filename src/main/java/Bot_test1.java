
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
        /*
        Firebase baseDeDatos = new Firebase();
        Conseguir_los_juegos_en_oferta scraper = new Conseguir_los_juegos_en_oferta();
        scraper.obtenerOfertasdeGog();
        System.out.println("Registros terminados, empezando subida...");
        baseDeDatos.conectar();
        if(scraper.juegos_ofertados.size() != 0){
            for (Map.Entry<String, Object> entry : scraper.juegos_ofertados.entrySet()) {
                baseDeDatos.insertarDatos("Juegos_rebajados", entry.getKey(), (Map<String, Object>) scraper.juegos_ofertados.get(entry.getKey()));
            }
        }
        if(scraper.juegos_gratis.size() != 0){
            for (Map.Entry<String, Object> entry:scraper.juegos_gratis.entrySet()){
                baseDeDatos.insertarDatos("Juegos_gratis", entry.getKey(), (Map<String, Object>) scraper.juegos_gratis.get(entry.getKey()));
            }
        }
        baseDeDatos.MostrarDatos("Juegos_rebajados");
        baseDeDatos.eliminarTabla("Juegos_rebajados");
        System.out.println("a");
         */
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
