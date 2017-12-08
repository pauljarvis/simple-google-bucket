package uk.co.tpplc.google;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.storage.Storage;

import java.io.*;
import java.net.URLConnection;

import static uk.co.tpplc.google.GoogleStorageConfigConverter.toStorageObjects;

public class DefaultGoogleStorage implements GoogleStorage {

    private final Storage.Objects storageObjects;

    public DefaultGoogleStorage(GoogleStorageConfig googleStorageConfig) {
        this(toStorageObjects(googleStorageConfig));
    }

    public DefaultGoogleStorage(Storage.Objects storageObjects) {
        this.storageObjects = storageObjects;
    }

    @Override
    public void download(GoogleBucketRequest request) {
        try (OutputStream outputStream = new FileOutputStream(request.getFile())) {
            Storage.Objects.Get get = toGet(request);
            get.executeAndDownloadTo(outputStream);
        } catch (IOException e) {
            throw new GoogleStorageException(e);
        }
    }

    @Override
    public void upload(GoogleBucketRequest request) {
        try {
            internalUpload(request);
        } catch (IOException e) {
            throw new GoogleStorageException(e);
        }
    }

    @Override
    public boolean exists(ObjectInfo info) {
        try {
            Storage.Objects.Get get = toGet(info);
            get.execute();
            return true;
        } catch (GoogleJsonResponseException e) {
            if (hasNotFoundStatus(e))
                return false;
            throw new GoogleStorageException(e);
        } catch (IOException e) {
            throw new GoogleStorageException(e);
        }
    }

    @Override
    public void delete(ObjectInfo info) {
        try {
            Storage.Objects.Delete delete = storageObjects.delete(info.getBucketName(), info.getObjectName());
            delete.execute();
        } catch (IOException e) {
            throw new GoogleStorageException(e);
        }
    }

    private Storage.Objects.Get toGet(ObjectInfo info) throws IOException {
        return storageObjects.get(info.getBucketName(), info.getObjectName());
    }

    private boolean hasNotFoundStatus(GoogleJsonResponseException e) {
        return e.getStatusCode() == 404;
    }

    private void internalUpload(GoogleBucketRequest request) throws IOException {
        try (InputStream stream = new FileInputStream(request.getFile())) {
            String contentType = URLConnection.guessContentTypeFromStream(stream);
            InputStreamContent content = new InputStreamContent(contentType, stream);
            Storage.Objects.Insert insert = storageObjects.insert(request.getBucketName(), null, content);
            insert.setName(request.getObjectName());
            insert.execute();
        }
    }

}
