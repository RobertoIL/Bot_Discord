

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Bot_test1 {
    private final Dotenv config;


    public Bot_test1(Dotenv config) throws LoginException{
        this.config = config;

        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.build();




    }



}
