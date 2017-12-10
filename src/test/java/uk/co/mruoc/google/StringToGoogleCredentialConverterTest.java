package uk.co.mruoc.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringToGoogleCredentialConverterTest {

    private final StringToGoogleCredentialConverter converter = new StringToGoogleCredentialConverter();

    @Test
    public void shouldConvertJsonToCredential() {
        String value = "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"my-id\",\n" +
                "  \"private_key_id\": \"my-key-id\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCX4ohIT0TZRQfF\\nJ/YdDvLh5sfoAln20YEgnJfUy5jJ/psIHcI5Zuch/72whHcVA5L9nsscrDushKOw\\ns109ZOyJr9DQTT5KljYz581eDcFmUI+OlYAMrHaNVAVgf0fI5KVsKZqEnrkfWGjL\\nXp6pWPV+4vetKf6RIK2wIPJSzsmB7xzNyIsyPce7LEEeQXfZdsKn4tK/CACL+p86\\nDzgfefvssbsnBjkjQ1yu04uqGiAo/doC0KmGBl32Q3z/k/1ASiJ8vynUvGLgtJey\\n6Iv12WEmzs4LSLyx3Q9XE4Oul5Nc2gWt1Z4SKeSdm7uaXlP4a3r/XZea+l+3efsC\\nn+zk5z3HAgMBAAECgf84H15Ifm+vRrQwsgdFO5xG9U8NcgG/+zxsXK2NEljixL3m\\nCwCbNVigbl9yftVpYjf0f1D704QIlsm0xGF4ziFcRT+ge8XLvZEAnQIze8lWVGIl\\nIvRMZT0Hss1uZu8NRmNf44W1Ji5BhVdMF6RkaPqvQ5x+WeekoFxQvrdF1cG4RvJC\\nhXqq05qieiptXgxY69TJOiZulkFe1vHvbF8P8YNE9j/RUJ5eiGZrnjrhka+lp/v9\\nfKPL+IfUxkbQ+nHf445VMrCMQha8v+BYlxSJSc4fHl/F/L6MmXNO9FKlrBOnrH+H\\nhWH7zH1AwhoPYIeyssKJmXa+hMgaBcrfEhcvcQUCgYEAztV2FOcLbE5dX4IvV909\\ngZXBPEb7n1inpFebraMYpOjpF4pslZz7YJIDZXhMFkEzQFvBL7sTB1Ywh+j6NESi\\n8/bSh5ZsSeqAexNiZi92hYqPtUteojaB3+yZHIDqipTzC1hCvvjRgPjs9QqrxzmX\\nocuslW5GH4k46xhdGD5QIfUCgYEAu/09+CKMaWImWBs9fd2iozloURfJDb5gG8aV\\nw4y7ifyis2o7SQKui4riGy2lEcLpC17oJAkWqjfEDhnXYw77hY4ACEmJdhUeaW29\\n9FVWJGh0I2p2VJFdYIHNrzMaGLaJEX8VExXj1eAQ+x3VbEKfk55TQIUG+sfhmTDN\\nFQmKP0sCgYEAw4+G5zqRTWVG2cAQnWX8+qIqo6CF1uMPFs8SzfkwmvPxOCXUhx13\\nfJZyCaXVYxFgsRBQN54M3tn6Zva8Y2EmJb+QzUxHtRe5RSVN20ozKQ6a1BKgNoWN\\nBLkDB7VIjaFJr8dSzJgcFnLGMOZXBraIjR0zX9J1EOpA2+AcXYjFYWUCgYAxUHV5\\nKlb7Liww4LR42DNZ7i1XWI39dPtxTd7rtXdc81EbD7sNpA51vLEjwA2VYzQPL45m\\nJZw0evyLVmwSKFQC7QVFJ0VQHKolrgwulFRMqLoOsFbqSFfegS6bzyQBZ6D9XgEB\\nSLTD3kuWFHVwOOnzp4zfWAZEszqTHSIvaplBkQKBgF8aq4UK+bPXPtuZjFs1A0+9\\nKUQlIpLbiV0jjZCNPDe716DuL8kR0/XKavyY4+FZHc1koVN0GB5YrsBGBzFHXFCx\\nbEO6JdGV8ALP9mdhRF1pff4OHHUBg9afzPT0jDI2e63o9Y8Rpy8U1nmYUIJs0emr\\nl5beWbUHR/OdkfdvV8hx\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"my@client.email\",\n" +
                "  \"client_id\": \"my-client-id\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://accounts.google.com/o/oauth2/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/my@client.email\"\n" +
                "}";

        GoogleCredential credential = converter.toCredential(value);

        assertThat(credential.getServiceAccountId()).isEqualTo("my@client.email");
        assertThat(credential.getServiceAccountProjectId()).isEqualTo("my-id");
    }

}
