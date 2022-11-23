

import io.github.cdimascio.dotenv.Dotenv;
import listeners.EventListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Bot_test1 {
    public static void main(String[] args) {
        ShardManager shardManager;
        String token = "MTAzOTYzNDE5NzE2MzI4NjY0MA.GL2Par.O3khijX9QOGLg8q7gfia8SC_fHht5vrVTS5k9Q";

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        shardManager = builder.build();
        shardManager.addEventListener(new EventListener());



    }



}
