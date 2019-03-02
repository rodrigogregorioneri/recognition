package com.neri.dev.recognition.config;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class DropBoxClient {


    private static final String ACCESS_TOKEN = "<ACCESS TOKEN>";
    private static final String CLIENT_IDENTIFIER = "dropbox/java-tutorial";
    private static final String USER_LOCALE = "en_US";

    @Value("${dropbox.accessToken}")
    private String accessToken;

    @Bean
    public DbxClientV2 dropboxClient() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("example-app").build();
        return new DbxClientV2(config, accessToken);
    }

}
