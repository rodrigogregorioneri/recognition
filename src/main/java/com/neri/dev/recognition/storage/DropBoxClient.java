package com.neri.dev.recognition.storage;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.stereotype.Component;

@Component
public class DropBoxClient {


    private static final String ACCESS_TOKEN = "<ACCESS TOKEN>";
    private static final String CLIENT_IDENTIFIER = "dropbox/java-tutorial";
    private static final String USER_LOCALE = "en_US";

    public DropBoxClient(){
        DbxRequestConfig config = new DbxRequestConfig(CLIENT_IDENTIFIER, USER_LOCALE);
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    }

}
