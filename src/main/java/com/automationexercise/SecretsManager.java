package com.automationexercise;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.val;

public class SecretsManager {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String get(String key) {
        //CI/CD
        String env = System.getenv(key);
        if (env != null && !env.isBlank()) {
            return env;
        }
        //local
        String localEnv = dotenv.get(key);
        if (localEnv != null && !localEnv.isBlank()){
            return localEnv;
        }
throw new RuntimeException(("Secret not found: " + key));
    }
}
