package uk.co.mruoc.google;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedObjectInfoTest {

    private final ObjectInfo info = new StubbedObjectInfo();

    @Test
    public void shouldReturnBucketName() {
        assertThat(info.getBucketName()).isEqualTo("stubbed-bucket-name");
    }

    @Test
    public void shouldReturnObjectName() {
        assertThat(info.getObjectName()).isEqualTo("stubbed-object-name");
    }

}
