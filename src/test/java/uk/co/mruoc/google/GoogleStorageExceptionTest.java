package uk.co.mruoc.google;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleStorageExceptionTest {

    @Test
    public void shouldReturnCause() {
        Exception cause = new Exception();

        GoogleStorageException exception = new GoogleStorageException(cause);

        assertThat(exception.getCause()).isEqualTo(cause);
    }

}
