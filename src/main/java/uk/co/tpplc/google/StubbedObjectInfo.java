package uk.co.tpplc.google;

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
