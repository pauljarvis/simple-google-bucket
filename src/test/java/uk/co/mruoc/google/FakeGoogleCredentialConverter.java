package uk.co.mruoc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class FakeGoogleCredentialConverter implements GoogleCredentialConverter {

    private String value;
    private GoogleCredential credential;

    @Override
    public GoogleCredential toCredential(String value) {
        this.value = value;
        return credential;
    }

    public void setCredential(GoogleCredential credential) {
        this.credential = credential;
    }

    public String getLastValue() {
        return value;
    }

}
