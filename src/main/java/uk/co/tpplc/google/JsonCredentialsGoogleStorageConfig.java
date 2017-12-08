package uk.co.tpplc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class JsonCredentialsGoogleStorageConfig implements GoogleStorageConfig {

    private final GoogleCredentialConverter googleCredentialConverter = new StringToGoogleCredentialConverter();

    private final String jsonCredentials;
    private final String applicationName;

    public JsonCredentialsGoogleStorageConfig(JsonCredentialsGoogleStorageConfigBuilder builder) {
        this.jsonCredentials = builder.jsonCredentials;
        this.applicationName = builder.applicationName;
    }

    @Override
    public GoogleCredential getCredential() {
        return googleCredentialConverter.toCredential(jsonCredentials);
    }

    @Override
    public String getApplicationName() {
        return applicationName;
    }

    public static class JsonCredentialsGoogleStorageConfigBuilder {

        private String jsonCredentials;
        private String applicationName;

        public JsonCredentialsGoogleStorageConfigBuilder setJsonCredentials(String jsonCredentials) {
            this.jsonCredentials = jsonCredentials;
            return this;
        }

        public JsonCredentialsGoogleStorageConfigBuilder setApplicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public GoogleStorageConfig build() {
            return new JsonCredentialsGoogleStorageConfig(this);
        }

    }

}
