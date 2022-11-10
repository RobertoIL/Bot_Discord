

import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot_test1 {

    public static void main(String[] args) throws LoginException {
        final String token = "";

        JDABuilder jdaBuilder = JDABuilder.createDefault(token);

        jdaBuilder.build();

        //hola


    }



}
