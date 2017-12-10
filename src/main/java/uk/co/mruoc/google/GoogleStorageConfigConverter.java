package uk.co.mruoc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.storage.Storage;

public class GoogleStorageConfigConverter {

    public static Storage.Objects toStorageObjects(GoogleStorageConfig googleStorageConfig) {
        Storage storage = toStorage(googleStorageConfig);
        return storage.objects();
    }

    private static Storage toStorage(GoogleStorageConfig googleStorageConfig) {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        GoogleCredential credential = googleStorageConfig.getCredential();
        return new Storage.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(googleStorageConfig.getApplicationName())
                .build();
    }

}
