package listeners;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String canalMencion = event.getChannel().getAsMention();
        String jumplink = event.getJumpUrl();

        String mensaje = user.getAsTag() + " reaccionó a un  mensaje con " + emoji + " en " +canalMencion;
        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(mensaje).queue();
    }




}
