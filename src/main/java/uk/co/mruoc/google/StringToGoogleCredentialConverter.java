package uk.co.mruoc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.google.api.client.googleapis.auth.oauth2.GoogleCredential.fromStream;
import static com.google.api.services.storage.StorageScopes.DEVSTORAGE_FULL_CONTROL;
import static java.util.Collections.singleton;

public class StringToGoogleCredentialConverter implements GoogleCredentialConverter {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF8");

    private final Charset charset;

    public StringToGoogleCredentialConverter() {
        this(DEFAULT_CHARSET);
    }

    public StringToGoogleCredentialConverter(Charset charset) {
        this.charset = charset;
    }

    @Override
    public GoogleCredential toCredential(String value) {
        try {
            byte[] bytes = value.getBytes(charset);
            InputStream stream = new ByteArrayInputStream(bytes);
            GoogleCredential credential = fromStream(stream);
            return credential.createScoped(singleton(DEVSTORAGE_FULL_CONTROL));
        } catch (IOException e) {
            throw new GoogleStorageException(e);
        }
    }

}
