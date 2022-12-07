package commands;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import web_scraping.Juegos_oferta;

import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class ComandoOfertas extends ListenerAdapter {

    List<Juegos_oferta> listaJuegos = new ArrayList<>();
    public ComandoOfertas(List<Juegos_oferta> llaves) {
        this.listaJuegos=llaves.subList(0, 5);
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("juegos-ofertas")) {
            event.reply("LAS MEJORES OFERTAS: "+ "\n"+
                    listaJuegos.toString()).queue();
        }
    }

}
