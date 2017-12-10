# Simple Google Bucket

[![Build Status](https://travis-ci.org/michaelruocco/simple-google-bucket.svg?branch=master)](https://travis-ci.org/michaelruocco/simple-google-bucket)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/simple-google-bucket/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/simple-google-bucket?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a9a463bc38234e28acaa7a2b41ef3ee6)](https://www.codacy.com/app/michaelruocco/simple-google-bucket?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/simple-google-bucket&amp;utm_campaign=Badge_Grade)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.michaelruocco/simple-google-bucket/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.michaelruocco/simple-google-bucket)

This library provides an abstraction layer around code to interact with a
google bucket. It provides a simple way to perform the following actions
against a Google Bucket:

* Store a file
* Read a file
* Delete an object
* Check an object exists

## Usage

To use the library from a program you will need to add a dependency to your project. In
gradle you would do this by adding the following to your build.gradle file:

```
dependencies {
    compile group: 'com.github.michaelruocco', name: 'simple-google-bucket', version: '1.0.0'
}
```

## Creating an Instance of Google Storage

Before interactive with a bucket you will need to create an instance of the
GoogleStorage class, to do this you will need to create an instance of a class that
implements the GoogleStorageConfig interface.

There are three ways that you can set up your bucket configuration, you can
provide a json string containing your credentials, you can provide a path variable
that points at a file containing your credentials or you can set an environment variable
that contains a path that points at your credentials file.

### JSON Credential Configuration

To use a json string containing your credentials you can create an instance of
JsonCredentialsGoogleStorageConfig:

```
GoogleStorageConfig config = new JsonCredentialsGoogleStorageConfigBuilder()
            .setJsonCredentials("\"type\":\"service_account\",\"project_id\":...")
            .setApplicationName("my-application")
            .build();
GoogleStorage storage = new DefaultGoogleStorage(config);
```

### File Path Configuration

To use a path variable that points you can create an instance of
JsonCredentialsGoogleStorageConfig:

```
GoogleStorageConfig config = new CredentialFileGoogleStorageConfigBuilder()
            .setCredentialFilePath("creds/my-credentials.json")
            .setApplicationName("my-application")
            .build();
GoogleStorage storage = new DefaultGoogleStorage(config);
```

### Default Environment Variable Configuration

Google standard practice is to have set an environment variable
GOOGLE_APPLICATION_CREDENTIALS to contain a path variable that points
at your credentials file to do this you can use
DefaultApplicationGoogleStorageConfig:

```
GoogleStorageConfig config = new DefaultApplicationGoogleStorageConfigBuilder()
            .setApplicationName("my-application")
            .build();
GoogleStorage storage = new DefaultGoogleStorage(config);
```

## Interacting with Google Storage

Once you have an instance of GoogleStorage you can use it to write a file to a bucket
by creating a GoogleBucketRequest:

```
File file = new File("test/upload.txt");
GoogleBucketRequest request = new DefaultGoogleBucketRequest(file, "my-bucket", "my-object");
storage.upload(request);
```

Downloading works in exactly the same way:

```
File file = new File("test/download.txt");
GoogleBucketRequest request = new DefaultGoogleBucketRequest(file, "my-bucket", "my-object");
storage.download(request);
```

Checking a file exists does not require a file object because
we are not uploading or downloading anything so an ObjectInfo can be
used in place of a request:

```
ObjectInfo info = new DefaultObjectInfo("my-bucket", "my-object");
boolean exists = storage.exists(info);
```

Deleting a file works in exactly the same way:

```
ObjectInfo info = new DefaultObjectInfo("my-bucket", "my-object");
storage.delete(info);
```

There is the potential that each of these messages could throw
a GoogleStorageException if any unexpected errors occur.

## Running the tests

This project is covered by both unit tests and integration tests. The
integration tests need to make use of a real google bucket so you will
need to provide a google application name, google bucket name and a google
json credentials string. You can do this by populating them into the
gradle.properties file in the repo (note you will need to escape any new lines, i.e
\n needs to become \\n.)

### Running the unit tests

To run just the unit tests you can run the command:

```
gradlew clean build -x integrationTest
```

### Running the integration tests

To run just the integration tests you can run the command:

```
gradlew clean build -x test
```

If you want to provide the google bucket name, application and json credentials
from gradle rather than setting environment variables you can add them into the
gradle.properties file within the repo instead. You will need to add the following
entries to the file:

```
google.json.credentials=<your google credentials json>
google.application.name=<your google application name>
google.bucket.name=<your google bucket name>
```

### Running all the tests

Finally to run all the integration tests you can run the command:

```
gradlew clean build
```

As above you will need to supply properties for or set environment variables for the google
application name, bucket name and credentials for the integration tests to work.

### Running the integration tests from IDE

If you are trying to run the integration tests directly in your IDE rather
than using the gradle tasks provided then you will need to set the following
environment variables within your IDE:

* GOOGLE_JSON_APPLICATION_CREDENTIALS - the json string containing the credentials for the google application and google bucket
* GOOGLE_APPLICATION_NAME - the name of the google application to use
* GOOGLE_BUCKET_NAME - the name of the google bucket to use for the tests

## Checking dependencies

You can check the current dependencies used by the project to see whether
or not they are currently up to date by running the following command:

```
gradlew dependencyUpdates
```