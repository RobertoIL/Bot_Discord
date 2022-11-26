package commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BotCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("juegos-gratis")){
            event.reply("Aqui estaran los juegos gratuitos de epic").queue();

        } else if (event.getName().equals("ofertas")) {
            event.reply("Aqui se mostraran las mejores ofertas destacadas").queue();

        }
    }
}
