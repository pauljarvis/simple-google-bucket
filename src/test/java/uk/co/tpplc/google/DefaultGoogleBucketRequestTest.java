package uk.co.tpplc.google;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultGoogleBucketRequestTest {

    private static final String BUCKET_NAME = "my-bucket-name";
    private static final String OBJECT_NAME = "my-object-name";
    private static final File FILE = new File("my-file.txt");

    private final GoogleBucketRequest request = new DefaultGoogleBucketRequest(FILE, BUCKET_NAME, OBJECT_NAME);

    @Test
    public void shouldReturnFile() {
        assertThat(request.getFile()).isEqualTo(FILE);
    }

    @Test
    public void shouldReturnBucketName() {
        assertThat(request.getBucketName()).isEqualTo(BUCKET_NAME);
    }

    @Test
    public void shouldReturnObjectName() {
        assertThat(request.getObjectName()).isEqualTo(OBJECT_NAME);
    }

}
