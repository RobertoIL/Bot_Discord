

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Bot_test1 {
    public static void main(String[] args) {
        String token = "";
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.build();
    }



}
