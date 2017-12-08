package uk.co.tpplc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class CredentialFileGoogleStorageConfig implements GoogleStorageConfig {

    private final GoogleCredential credential;
    private final String applicationName;

    public CredentialFileGoogleStorageConfig(CredentialFileGoogleStorageConfigBuilder builder) {
        this.credential = builder.credential;
        this.applicationName = builder.applicationName;
    }

    @Override
    public GoogleCredential getCredential() {
        return credential;
    }

    @Override
    public String getApplicationName() {
        return applicationName;
    }

    public static class CredentialFileGoogleStorageConfigBuilder {

        private final GoogleCredentialConverter credentialConverter;

        private GoogleCredential credential;
        private String applicationName;

        public CredentialFileGoogleStorageConfigBuilder() {
            this(new FilePathToGoogleCredentialConverter());
        }

        public CredentialFileGoogleStorageConfigBuilder(GoogleCredentialConverter credentialConverter) {
            this.credentialConverter = credentialConverter;
        }

        public CredentialFileGoogleStorageConfigBuilder setCredentialFilePath(String credentialFilePath) {
            this.credential = credentialConverter.toCredential(credentialFilePath);
            return this;
        }

        public CredentialFileGoogleStorageConfigBuilder setApplicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public GoogleStorageConfig build() {
            return new CredentialFileGoogleStorageConfig(this);
        }

    }

}
