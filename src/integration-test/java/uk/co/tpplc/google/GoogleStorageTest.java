package uk.co.tpplc.google;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import uk.co.tpplc.google.JsonCredentialsGoogleStorageConfig.JsonCredentialsGoogleStorageConfigBuilder;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleStorageTest {

    private static final String GOOGLE_CREDENTIALS = System.getenv("GOOGLE_JSON_APPLICATION_CREDENTIALS");
    private static final String APPLICATION_NAME = System.getenv("GOOGLE_APPLICATION_NAME");
    private static final String BUCKET_NAME = System.getenv("GOOGLE_BUCKET_NAME");
    private static final String OBJECT_NAME = "unit.test.object.txt";

    private final GoogleStorageConfig config = new JsonCredentialsGoogleStorageConfigBuilder()
            .setJsonCredentials(GOOGLE_CREDENTIALS)
            .setApplicationName(APPLICATION_NAME)
            .build();

    private final GoogleStorage storage = new DefaultGoogleStorage(config);

    @Test
    public void shouldUploadFile() {
        GoogleBucketRequest uploadRequest = buildUploadRequest();
        try {
            deleteObjectIfExists(uploadRequest);

            storage.upload(uploadRequest);

            assertThat(storage.exists(uploadRequest)).isTrue();
        } finally {
            deleteObjectIfExists(uploadRequest);
        }
    }

    @Test
    public void shouldDownloadFile() {
        GoogleBucketRequest request = buildDownloadRequest();
        try {
            deleteLocalFileIfExists(request.getFile());
            createFileToDownload();

            storage.download(request);

            assertThat(request.getFile().exists());
        } finally {
            deleteLocalFileIfExists(request.getFile());
            deleteObjectIfExists(request);
        }
    }

    private GoogleBucketRequest buildUploadRequest() {
        File file = new File("test/upload.txt");
        return new DefaultGoogleBucketRequest(file, BUCKET_NAME, OBJECT_NAME);
    }

    private GoogleBucketRequest buildDownloadRequest() {
        File file = new File("test/download.txt");
        return new DefaultGoogleBucketRequest(file, BUCKET_NAME, OBJECT_NAME);
    }

    private void deleteObjectIfExists(ObjectInfo info) {
        if (storage.exists(info))
            storage.delete(info);
    }

    private void deleteLocalFileIfExists(File file) {
        FileUtils.deleteQuietly(file);
    }

    private void createFileToDownload() {
        GoogleBucketRequest request = buildUploadRequest();
        storage.upload(request);
    }

}
