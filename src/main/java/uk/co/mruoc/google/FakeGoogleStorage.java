package uk.co.mruoc.google;

import java.util.List;

import com.google.api.services.storage.model.StorageObject;

public class FakeGoogleStorage implements GoogleStorage {

    private GoogleBucketRequest lastDownloadRequest;
    private GoogleBucketRequest lastUploadRequest;
    private GoogleBucketRequest lastListRequest;
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

    @Override
    public List<StorageObject> list(GoogleBucketRequest request) {
        this.lastListRequest = request;
        return null;
    }

    public GoogleBucketRequest getLastDownloadRequest() {
        return lastDownloadRequest;
    }

    public GoogleBucketRequest getLastUploadRequest() {
        return lastUploadRequest;
    }

    public GoogleBucketRequest getLastListRequest() {
        return lastListRequest;
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
