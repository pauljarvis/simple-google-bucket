package uk.co.tpplc.google;

public interface GoogleStorage {

    void download(GoogleBucketRequest request);

    void upload(GoogleBucketRequest request);

    boolean exists(ObjectInfo info);

    void delete(ObjectInfo info);

}