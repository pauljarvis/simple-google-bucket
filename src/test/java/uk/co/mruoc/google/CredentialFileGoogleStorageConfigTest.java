package uk.co.mruoc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CredentialFileGoogleStorageConfigTest {

    private final GoogleCredential credential = mock(GoogleCredential.class);
    private final FakeGoogleCredentialConverter credentialConverter = new FakeGoogleCredentialConverter();

    private final CredentialFileGoogleStorageConfig.CredentialFileGoogleStorageConfigBuilder builder = new CredentialFileGoogleStorageConfig.CredentialFileGoogleStorageConfigBuilder(credentialConverter);

    @Test
    public void shouldPassFilePathToCredentialConverter() {
        String filePath = "/my/file-path.txt";
        builder.setCredentialFilePath(filePath);

        builder.build();

        assertThat(credentialConverter.getLastValue()).isEqualTo(filePath);
    }

    @Test
    public void shouldSetCredentialOnConfig() {
        credentialConverter.setCredential(credential);
        String filePath = "/my/file-path.txt";
        builder.setCredentialFilePath(filePath);

        GoogleStorageConfig config = builder.build();

        assertThat(config.getCredential()).isEqualTo(credential);
    }

    @Test
    public void shouldSetApplicationNameOnConfig() {
        String applicationName = "application-name";
        builder.setApplicationName(applicationName);

        GoogleStorageConfig config = builder.build();

        assertThat(config.getApplicationName()).isEqualTo(applicationName);
    }


}
