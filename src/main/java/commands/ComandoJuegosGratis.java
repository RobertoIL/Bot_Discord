package commands;

import Firebase.ConexionAFirebase;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import web_scraping.Juegos_gratis;

import java.util.*;


public class ComandoJuegosGratis extends ListenerAdapter {
    List<Juegos_gratis> listaJuegos = new ArrayList<>();
    public ComandoJuegosGratis(List<Juegos_gratis> juegos) {
        if(juegos.size() <= 15){
            this.listaJuegos = juegos;
        }else{
            for (int i = 0; i < 15; i++) {
                List<Integer> asd = new ArrayList<>();
                int val1 = 0;
                do{
                    val1 = (int)(Math.random() * juegos.size());
                }while(asd.contains(val1));
                asd.add(val1);
                this.listaJuegos.add(juegos.get(val1));
            }
        }
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
