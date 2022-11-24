import listeners.EventListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;



public class Bot_test1 {
    public static void main(String[] args) {
        ShardManager shardManager;
        final String token = "";

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        shardManager = builder.build();
        shardManager.addEventListener(new EventListener());






    }

}
