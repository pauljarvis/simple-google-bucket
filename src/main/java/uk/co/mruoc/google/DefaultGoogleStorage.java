package uk.co.mruoc.google;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLConnection;

public class DefaultGoogleStorage implements GoogleStorage {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGoogleStorage.class);

    private final Storage.Objects storageObjects;

    public DefaultGoogleStorage(GoogleStorageConfig googleStorageConfig) {
        this(GoogleStorageConfigConverter.toStorageObjects(googleStorageConfig));
    }

    public DefaultGoogleStorage(Storage.Objects storageObjects) {
        this.storageObjects = storageObjects;
    }

    @Override
    public void download(GoogleBucketRequest request) {
        try (OutputStream outputStream = new FileOutputStream(request.getFile())) {
            logDownload(request);
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
        String message = String.format("object %s in bucket %s", info.getObjectName(), info.getBucketName());
        try {
            Storage.Objects.Get get = toGet(info);
            get.execute();
            LOGGER.info(message + " exists");
            return true;
        } catch (GoogleJsonResponseException e) {
            if (hasNotFoundStatus(e)) {
                LOGGER.info(message + " does not exist");
                return false;
            }
            throw new GoogleStorageException(e);
        } catch (IOException e) {
            throw new GoogleStorageException(e);
        }
    }

    @Override
    public void delete(ObjectInfo info) {
        try {
            String message = String.format("deleting object %s exists in bucket %s", info.getObjectName(), info.getBucketName());
            LOGGER.info(message);
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
            logUpload(request);
            String contentType = URLConnection.guessContentTypeFromStream(stream);
            InputStreamContent content = new InputStreamContent(contentType, stream);
            Storage.Objects.Insert insert = storageObjects.insert(request.getBucketName(), null, content);
            insert.setName(request.getObjectName());
            insert.execute();
        }
    }

    private void logDownload(GoogleBucketRequest request) {
        String message = String.format("downloading object %s from bucket %s to local file %s",
                request.getObjectName(),
                request.getBucketName(),
                request.getFile().getAbsolutePath());
        LOGGER.info(message);
    }

    private void logUpload(GoogleBucketRequest request) {
        String message = String.format("uploading local file %s to bucket %s as object %s",
                request.getFile().getAbsolutePath(),
                request.getBucketName(),
                request.getObjectName());
        LOGGER.info(message);
    }

}
