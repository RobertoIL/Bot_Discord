import listeners.EventListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;



public class Bot_test1 {
    public static void main(String[] args) {
        ShardManager shardManager;
        final String token = "MTAzOTYzNDE5NzE2MzI4NjY0MA.GIKPVo.Y54ItOcnXgT_Jb77vUJqK1BNVywGgv-qMns2SY";

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        shardManager = builder.build();
        shardManager.addEventListener(new EventListener());






    }

}
