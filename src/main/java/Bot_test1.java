import commands.BotCommands;
import listeners.EventoReaccion;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;

import javax.security.auth.login.LoginException;


public class Bot_test1 {
    public static void main(String[] args) throws LoginException, InterruptedException {
        final String token = "";

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new EventoReaccion())
                .addEventListeners(new BotCommands())
                .build().awaitReady();

        Guild guild = jda.getGuildById("1039635186071117865");

        if (guild != null){
            guild.upsertCommand("juegos-gratis", "Ver los juegos que destacados gratuitos").queue();
            guild.upsertCommand("ofertas", "Ver las ultimas ofertas destacadas").queue();
        }

        }


    }


