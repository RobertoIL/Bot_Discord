import listeners.EventoReaccion;
import listeners.EventoSaludo;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


public class Bot_test1 {
    public static void main(String[] args) {
        final String token = "MTAzOTYzNDE5NzE2MzI4NjY0MA.G4xxMf.c17XY23_KSZzZkcjlsCZCrczRm94n32yjywWro";

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new EventoReaccion())
                .addEventListeners(new EventoSaludo())
                .build();







    }

}
