
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

public class Bot_test1 {
    public static void main(String[] args) throws InterruptedException {
        Conseguir_los_juegos_en_oferta inicio = new Conseguir_los_juegos_en_oferta();
        inicio.obtenerOfertasdeGog();
        inicio.obtenerOfertasSteam();
        CRUDFirebase baseConLasOfertas = new CRUDFirebase();
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
