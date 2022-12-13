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
import web_scraping.Juegos_oferta;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bot_test1 {
    public static void main(String[] args) throws InterruptedException {
        ConexionAFirebase conexionAFirebase = new ConexionAFirebase();
        conexionAFirebase.conectar();
        conexionAFirebase.eliminarTabla("juegos_rebajados", 1);
        conexionAFirebase.eliminarTabla("juegos_gratis", 1);
        conexionAFirebase.generarJuegos(10);//Aqui elige la cantidad aproximada de juegos que quieres que salgan, si los quieres todos dejalo en 0
        //el token se genera desde la cuenta de discord donde esta el bot

        final String token = "MTAzOTYzNDE5NzE2MzI4NjY0MA.GhiUfa.IrskiFjYaHii2MsSqsTy1zhsS9demN3uRKHtGc";

        JDA jda  =JDABuilder.createDefault(token)
                    .addEventListeners(new EventoReaccion())
                    .addEventListeners(new ComandoJuegosGratis(conexionAFirebase.devolverJuegosGratis()))
                    .addEventListeners(new ComandoOfertas(conexionAFirebase.devolverJuegosEnOferta()))
                    .build().awaitReady();

        jda.upsertCommand("juegos-gratis", "Mostrar juegos destacados gratuitos").queue();

        jda.upsertCommand("juegos-ofertas", "Mostrar ofertas destacadas de viedeojuegos").queue();

    }


}
