package uk.co.mruoc.google;

public class StubbedObjectInfo implements ObjectInfo {

    @Override
    public String getBucketName() {
        return "stubbed-bucket-name";
    }

    @Override
    public String getObjectName() {
        return "stubbed-object-name";
    }

}
