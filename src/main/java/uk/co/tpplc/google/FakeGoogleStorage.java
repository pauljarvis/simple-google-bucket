package uk.co.tpplc.google;

public class FakeGoogleStorage implements GoogleStorage {

    private GoogleBucketRequest lastDownloadRequest;
    private GoogleBucketRequest lastUploadRequest;
    private ObjectInfo lastDeletedInfo;
    private ObjectInfo lastExistsInfo;
    private boolean exists;

    @Override
    public void download(GoogleBucketRequest request) {
        this.lastDownloadRequest = request;
    }

    @Override
    public void upload(GoogleBucketRequest request) {
        this.lastUploadRequest = request;
    }

    @Override
    public boolean exists(ObjectInfo info) {
        this.lastExistsInfo = info;
        return exists;
    }

    @Override
    public void delete(ObjectInfo info) {
        this.lastDeletedInfo = info;
    }

    public GoogleBucketRequest getLastDownloadRequest() {
        return lastDownloadRequest;
    }

    public GoogleBucketRequest getLastUploadRequest() {
        return lastUploadRequest;
    }

    public ObjectInfo getLastDeletedInfo() {
        return lastDeletedInfo;
    }

    public ObjectInfo getLastExistsInfo() {
        return lastExistsInfo;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

}
