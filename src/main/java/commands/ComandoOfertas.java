package commands;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import web_scraping.Juegos_oferta;

import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

public class ComandoOfertas extends ListenerAdapter {

    List<Juegos_oferta> listaJuegos = new ArrayList<>();
    public ComandoOfertas(List<Juegos_oferta> juegos) {
        if(juegos.size() <= 15){
            this.listaJuegos = juegos;
        }else{
            for (int i = 0; i < 15; i++) {
                List<Integer> registroDeIndex = new ArrayList<>();
                int val1 = 0;
                do{
                    val1 = (int)(Math.random() * juegos.size());
                }while(registroDeIndex.contains(val1));
                registroDeIndex.add(val1);
                this.listaJuegos.add(juegos.get(val1));
            }
        }
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("juegos-ofertas")) {
            event.reply("Ofertas generadas: "+ "\n"+
                    listaJuegos.toString()).queue();
        }
    }

}
