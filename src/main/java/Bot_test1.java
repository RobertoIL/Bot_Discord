

import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot_test1 {

    public static void main(String[] args) throws LoginException {
        final String token = "MTAzOTYzNDE5NzE2MzI4NjY0MA.GCXP9O.dSBG7JSym--Si_rFGlZ3nFGKrJPWjz0dsznF90";

        JDABuilder jdaBuilder = JDABuilder.createDefault(token);

        jdaBuilder.build();


    }

}
