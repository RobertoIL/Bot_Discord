import listeners.EventoReaccion;
import listeners.EventoSaludo;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


public class Bot_test1 {
    public static void main(String[] args) {
        final String token = "";

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new EventoReaccion())
                .addEventListeners(new EventoSaludo())
                .build();







    }

}
