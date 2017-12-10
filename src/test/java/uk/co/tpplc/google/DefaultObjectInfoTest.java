package uk.co.tpplc.google;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultObjectInfoTest {

    private static final String BUCKET_NAME = "my-bucket-name";
    private static final String OBJECT_NAME = "my-object-name";

    private final ObjectInfo info = new DefaultObjectInfo(BUCKET_NAME, OBJECT_NAME);

    @Test
    public void shouldReturnBucketName() {
        assertThat(info.getBucketName()).isEqualTo(BUCKET_NAME);
    }

    @Test
    public void shouldReturnObjectName() {
        assertThat(info.getObjectName()).isEqualTo(OBJECT_NAME);
    }

}
