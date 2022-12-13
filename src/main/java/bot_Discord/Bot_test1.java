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

        Map<String, Object> juegos_gratis = new HashMap<>();
        Juegos_gratis juegosGratis = new Juegos_gratis();
        List<Juegos_gratis> ejemplo = new ArrayList<>();
        ejemplo.add(new Juegos_gratis("Fort Triumph", "https://store.epicgames.com/es-ES/p/fort-triumph", ""));
        ejemplo.add(new Juegos_gratis("RPG in a Box", "https://store.epicgames.com/es-ES/p/rpg-in-a-box", ""));


        List<Juegos_oferta> ejemplo2 = new ArrayList<>();
        ejemplo2.add(new Juegos_oferta("Freedom Fighters", "1.65", "https://www.gog.com/en/game/freedom_fighters"));
        ejemplo2.add(new Juegos_oferta("Knights of Honor II: Sovereign", "19,79", "https://www.gog.com/en/game/knights_of_honor_ii_sovereign"));
        ejemplo2.add(new Juegos_oferta("Horizon Zero Dawn™ Complete Edition", "12,54", "https://www.gog.com/en/game/horizon_zero_dawn_complete_edition"));
        ejemplo2.add(new Juegos_oferta("Steel Division 2", "7,58", "https://www.gog.com/en/game/steel_division_2"));
        ejemplo2.add(new Juegos_oferta("Blade Runner", "2,63", "https://www.gog.com/en/game/blade_runner"));


        ConexionAFirebase conexionAFirebase = new ConexionAFirebase();
        conexionAFirebase.conectar();
        conexionAFirebase.generarJuegos(20);//Aqui elige la cantidad aproximada de juegos que quieres que salgan, si los quieres todos dejalo en 0


        //conexionAFirebase.getDocument("juegos-gratis", juegos_gratis);

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
