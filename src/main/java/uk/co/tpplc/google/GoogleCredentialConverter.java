package uk.co.tpplc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public interface GoogleCredentialConverter {

    GoogleCredential toCredential(String value);

}
