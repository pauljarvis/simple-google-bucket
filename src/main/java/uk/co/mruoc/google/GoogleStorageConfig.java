package uk.co.mruoc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public interface GoogleStorageConfig {

    GoogleCredential getCredential();

    String getApplicationName();

}
