package uk.co.mruoc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FilePathToGoogleCredentialConverterTest {

    private final FilePathToGoogleCredentialConverter converter = new FilePathToGoogleCredentialConverter();

    @Test
    public void shouldConvertFilePathToCredentials() {
        String value = "test/creds.json";

        GoogleCredential credential = converter.toCredential(value);

        assertThat(credential.getServiceAccountId()).isEqualTo("my@client.email");
        assertThat(credential.getServiceAccountProjectId()).isEqualTo("my-id");
    }

}
