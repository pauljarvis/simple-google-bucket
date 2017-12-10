package uk.co.mruoc.google;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedGoogleBucketRequestTest {

    private final GoogleBucketRequest info = new StubbedGoogleBucketRequest();

    @Test
    public void shouldReturnFile() {
        assertThat(info.getFile().getPath()).isEqualTo("stubbed-file.txt");
    }

}
