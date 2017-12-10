package uk.co.mruoc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.google.api.client.googleapis.auth.oauth2.GoogleCredential.fromStream;
import static com.google.api.services.storage.StorageScopes.DEVSTORAGE_FULL_CONTROL;
import static java.util.Collections.singleton;

public class FilePathToGoogleCredentialConverter implements GoogleCredentialConverter {

    @Override
    public GoogleCredential toCredential(String path) {
        try {
            InputStream stream = new FileInputStream(new File(path));
            GoogleCredential credential = fromStream(stream);
            return credential.createScoped(singleton(DEVSTORAGE_FULL_CONTROL));
        } catch (IOException e) {
            throw new GoogleStorageException(e);
        }
    }

}
