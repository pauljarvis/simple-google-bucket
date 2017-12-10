package uk.co.tpplc.google;

import java.io.File;

public class StubbedGoogleBucketRequest extends StubbedObjectInfo implements GoogleBucketRequest {

    @Override
    public File getFile() {
        return new File("stubbed-file.txt");
    }

}
