package uk.co.mruoc.google;

public class DefaultObjectInfo implements ObjectInfo {

    private final String bucketName;
    private final String objectName;

    public DefaultObjectInfo(String bucketName, String objectName) {
        this.bucketName = bucketName;
        this.objectName = objectName;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }

    @Override
    public String getObjectName() {
        return objectName;
    }

}
