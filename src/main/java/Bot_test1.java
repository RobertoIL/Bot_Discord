import commands.ComandoOfertas;
import listeners.EventoReaccion;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import javax.security.auth.login.LoginException;


public class Bot_test1 {
    public static void main(String[] args) throws LoginException, InterruptedException {
        final String token = "";

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new EventoReaccion())
                .addEventListeners(new ComandoOfertas())
                .build().awaitReady();

        Guild guild = jda.getGuildById("1039635186071117865");

        if (guild != null){
            guild.upsertCommand("juegos-gratis", "Ver los juegos que destacados gratuitos").queue();

            guild.upsertCommand("ofertas", "Ver las ultimas ofertas destacadas")
                    .addOption(OptionType.STRING, "seleccione-plataforma"
                            ,"Buscaremos las mejores ofertas en la plataforma seleccionada"
                    ,true).queue();
        }

    }


}


