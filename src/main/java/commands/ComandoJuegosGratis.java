package commands;

import Firebase.CRUDFirebase;
import Firebase.Conexion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import web_scraping.Juegos_gratis;

import java.util.ArrayList;
import java.util.List;

public class ComandoJuegosGratis extends ListenerAdapter {
    //CRUDFirebase crudFirebase = new CRUDFirebase();
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (event.getName().equals("juegos-gratis")) {
            event.reply("aqui deberian verse los juegos gratis").queue();
        }
    }
}
