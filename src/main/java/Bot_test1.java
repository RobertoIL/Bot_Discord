
import Firebase.CRUDFirebase;
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


}
