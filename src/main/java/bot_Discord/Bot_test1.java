package bot_Discord;

import Firebase.CRUDFirebase;
import Firebase.ConexionAFirebase;
import commands.ComandoJuegosGratis;
import commands.ComandoOfertas;
import listeners.EventoReaccion;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import web_scraping.Conseguir_los_juegos_en_oferta;
import web_scraping.Juegos_gratis;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Bot_test1 {
    public static void main(String[] args) throws InterruptedException {

        Map<String, Object> juegos_gratis = new HashMap<>();
        Juegos_gratis juegosGratis = new Juegos_gratis();

        ConexionAFirebase conexionAFirebase = new ConexionAFirebase();
        //conexionAFirebase.getDocument("juegos-gratis", juegos_gratis);

        //el token se genera desde la cuenta de discord donde esta el bot
        final String token = "MTAzOTYzNDE5NzE2MzI4NjY0MA.GTnAZT.JtMJ4_IJmJyOFlqNFlyUMxMF5Yv-Xk_302vfV4";

        JDA jda  =JDABuilder.createDefault(token)
                    .addEventListeners(new EventoReaccion())
                    .addEventListeners(new ComandoJuegosGratis())
                    .addEventListeners(new ComandoOfertas())
                    .build().awaitReady();

        jda.upsertCommand("juegos-gratis", "Mostrar juegos destacados gratuitos").queue();

        jda.upsertCommand("ofertas", "Mostrar ofertas destacadas de viedeojuegos").queue();

    }


}
