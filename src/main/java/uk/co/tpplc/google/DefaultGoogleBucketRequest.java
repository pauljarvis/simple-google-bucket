package uk.co.tpplc.google;

import java.io.File;

public class DefaultGoogleBucketRequest extends DefaultObjectInfo implements GoogleBucketRequest {

    private final File file;

    public DefaultGoogleBucketRequest(File file, String bucketName, String objectName) {
        super(bucketName, objectName);
        this.file = file;
    }

    @Override
    public File getFile() {
        return file;
    }

}
