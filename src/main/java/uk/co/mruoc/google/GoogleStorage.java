package uk.co.mruoc.google;

import java.util.List;

import com.google.api.services.storage.model.StorageObject;

public interface GoogleStorage {

    void download(GoogleBucketRequest request);

    void upload(GoogleBucketRequest request);

    boolean exists(ObjectInfo info);

    void delete(ObjectInfo info);

    List<StorageObject> list(GoogleBucketRequest request);

}
