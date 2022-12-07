package commands;

import Firebase.ConexionAFirebase;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutoCompleteBot extends ListenerAdapter {
    private String[] words = new String[]{"apple", "apricot", "banana", "cherry", "coconut", "cranberry"};
    //private List<String> words = new ArrayList<>();
    ConexionAFirebase asd = new ConexionAFirebase();


    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        asd.conectar();
        String[] totalWords=asd.devolverKeys(event.getFocusedOption().getName());
        if (event.getName().equals("fruit") && event.getFocusedOption().getName().equals("name")) {
            List<Command.Choice> options = Stream.of(totalWords)
                    .filter(word -> word.startsWith(event.getFocusedOption().getValue())) // only display words that start with the user's current input
                    .map(word -> new Command.Choice(word, word)) // map the words to choices
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }
    }
}
