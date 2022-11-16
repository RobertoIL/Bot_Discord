

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Bot_test1 {
    public static void main(String[] args) {
        //final Dotenv config;

        //config = Dotenv.configure().load();
        //String token = config.get("TOKEN");
        String token = "";
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.build();
    }


}
