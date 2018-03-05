package uk.co.mruoc.google;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeGoogleStorageTest {

    private final FakeGoogleStorage storage = new FakeGoogleStorage();

    @Test
    public void shouldExistsShouldBeFalseByDefault() {
        boolean exists = storage.exists(new StubbedObjectInfo());

        assertThat(exists).isFalse();
    }

    @Test
    public void shouldSetExists() {
        storage.setExists(true);

        boolean exists = storage.exists(new StubbedObjectInfo());

        assertThat(exists).isTrue();
    }

    @Test
    public void shouldReturnLastObjectInfoForExists() {
        ObjectInfo info = new StubbedObjectInfo();

        storage.exists(info);

        assertThat(storage.getLastExistsInfo()).isEqualTo(info);
    }

    @Test
    public void shouldReturnLastDeletedObjectInfo() {
        ObjectInfo info = new StubbedObjectInfo();

        storage.delete(info);

        assertThat(storage.getLastDeletedInfo()).isEqualTo(info);
    }

    @Test
    public void shouldReturnLastUploadBucketRequest() {
        GoogleBucketRequest request = new StubbedGoogleBucketRequest();

        storage.upload(request);

        assertThat(storage.getLastUploadRequest()).isEqualTo(request);
    }

    @Test
    public void shouldReturnLastDownloadBucketRequest() {
        GoogleBucketRequest request = new StubbedGoogleBucketRequest();

        storage.download(request);

        assertThat(storage.getLastDownloadRequest()).isEqualTo(request);
    }

    @Test
    public void shouldReturnLastListRequest() {
        GoogleBucketRequest request = new StubbedGoogleBucketRequest();

        storage.list(request);

        assertThat(storage.getLastListRequest()).isEqualTo(request);
    }

}
