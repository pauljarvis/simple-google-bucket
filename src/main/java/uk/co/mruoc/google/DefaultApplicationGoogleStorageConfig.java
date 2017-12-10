package uk.co.mruoc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import java.io.IOException;
import java.io.UncheckedIOException;

import static com.google.api.services.storage.StorageScopes.DEVSTORAGE_FULL_CONTROL;
import static java.util.Collections.singleton;


public class DefaultApplicationGoogleStorageConfig implements GoogleStorageConfig {

    private final String applicationName;

    public DefaultApplicationGoogleStorageConfig(DefaultApplicationGoogleStorageConfigBuilder builder) {
        this.applicationName = builder.applicationName;
    }

    @Override
    public GoogleCredential getCredential() {
        try {
            GoogleCredential credential = GoogleCredential.getApplicationDefault();
            return credential.createScoped(singleton(DEVSTORAGE_FULL_CONTROL));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public String getApplicationName() {
        return applicationName;
    }

    public static class DefaultApplicationGoogleStorageConfigBuilder {

        private String applicationName;

        public DefaultApplicationGoogleStorageConfigBuilder setApplicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public GoogleStorageConfig build() {
            return new DefaultApplicationGoogleStorageConfig(this);
        }

    }

}
