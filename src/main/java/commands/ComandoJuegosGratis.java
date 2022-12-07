package commands;

import Firebase.ConexionAFirebase;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import web_scraping.Juegos_gratis;

import java.util.*;


public class ComandoJuegosGratis extends ListenerAdapter {
    List<Juegos_gratis> listaJuegos = new ArrayList<>();
    public ComandoJuegosGratis(List<Juegos_gratis> llaves) {
        this.listaJuegos=llaves.subList(0, 2);
    }

    //CRUDFirebase crudFirebase = new CRUDFirebase();
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("juegos-gratis")) {
            event.reply("JUEGOS GRATUITOS: "+
                    listaJuegos.toString()).queue();
        }
    }

}
