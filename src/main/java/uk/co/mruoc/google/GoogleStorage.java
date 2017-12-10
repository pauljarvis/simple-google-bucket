package uk.co.mruoc.google;

public interface GoogleStorage {

    void download(GoogleBucketRequest request);

    void upload(GoogleBucketRequest request);

    boolean exists(ObjectInfo info);

    void delete(ObjectInfo info);

}