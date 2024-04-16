# HowTo generate and use an openapi java client for our Receipt API

This document describes how to generate and use an openapi client from a openapi spec.

## Prerequisites
__Docker__
Docker is required to run the openapi generator. Install it from [docker.com](https://www.docker.com/products/docker-desktop)

__java 8 or higher__
Install it with [chocolatey](https://chocolatey.org/): `choco install jdk8`

```powershell
PS C:\WINDOWS\system32> choco install jdk8
Chocolatey v0.10.15
Installing the following packages:
jdk8
By installing you accept licenses for the packages.

jdk8 v8.0.211 [Approved]
jdk8 package files install completed. Performing other installation steps.
The package jdk8 wants to run 'chocolateyInstall.ps1'.
...
  Software installed to 'C:\Program Files\Java\jdk1.8.0_211\'
```

__maven__
Install it with [chocolatey](https://chocolatey.org/): `choco install maven`

```powershell
PS C:\WINDOWS\system32> choco install maven
Chocolatey v0.10.15
Installing the following packages:
maven
By installing you accept licenses for the packages.
Progress: Downloading maven 3.9.6... 100%

maven v3.9.6 [Approved]
maven package files install completed. Performing other installation steps.
The package maven wants to run 'chocolateyinstall.ps1'.
Note: If you don't run this script, the installation will fail.
...
Software installed to 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.6'

Chocolatey installed 1/1 packages.
 See the log for details (C:\ProgramData\chocolatey\logs\chocolatey.log).
```


## Download the openapi spec
Please download the openapi spec from the following link:
```powershell
Invoke-WebRequest -Uri "https://capture.bludelta.ai/receipt/specs/receipt/prod/v1/swagger.yaml" -OutFile "receipt-openapi.yaml"
```

## Generate the client with Docker
Download the openapi generator docker image:

```powershell
docker pull openapitools/openapi-generator-cli
```

Generate the client on windows with the following command:

```powershell
docker run --rm -it -v /d/openapi-specs:/local openapitools/openapi-generator-cli generate -i /local/receipt-openapi.yaml -g java -o /local/out/java
```

Compile the client with maven:

```powershell
cd openapi-specs/out/java
mvn clean install
```
This will generate a jar file 'openapi-java-client-1.0.jar' in the target folder.

## Use the client in an example application

```java
import java.io.File;

// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.model.*;
import org.openapitools.client.api.ReceiptApi;
import org.openapitools.client.ApiResponse;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://capture.bludelta.ai/receipt");

    ReceiptApi apiInstance = new ReceiptApi(defaultClient);
    String xApiKey = "YOUR_API_KEY"; // String | The customer's application key. Required for authentication
    String xApiIdentifier = null; // String | The customer's api identifier key. Not required for authentication
    File _file = new File("PATH_TO_RECEIPT"); // File | 
    try {
      ApiResponse<String> response = apiInstance.v1ReceiptPostWithHttpInfo(xApiKey, xApiIdentifier, _file);
      System.out.println("Response status code: " + response.getStatusCode());
      System.out.println("Response headers: " + response.getHeaders());
      System.out.println("Response body: " + response.getData());
    } catch (ApiException e) {
      System.err.println("Exception when calling ReceiptApi#v1ReceiptPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

Compile it

```powershell
javac -cp "D:\openapi-specs\out\java\target\openapi-java-client-1.0.jar;D:\openapi-specs\out\java\target\lib\*" "D:\openapi-specs\Example.java"
```

Run it

```powershell
java -cp "D:\openapi-specs\out\java\target\classes;D:\openapi-specs\out\java\target\openapi-java-client-1.0.jar;D:\openapi-specs\out\java\target\lib\*;D:\openapi-specs\" Example
```

Output:

```json
Response status code: 200
Response headers: {api-supported-versions=[1.0], connection=[keep-alive], content-length=[83092], content-type=[application/json], date=[Tue, 16 Apr 2024 05:50:36 GMT], server=[Kestrel]}
Response body: {
    "BluDoc.Version": "1.3.0",
    "Created.DateTime": "2024-04-03T11:29:19.5293973+00:00",
    "CreatorSoftware.Name": "BluDelta Receipt",
    "CreatorSoftware.Version": "1.0.0",
    "DocumentProvider.Name": "partner.css-Receipt",
    "Document.Languages": [
      "de"
    ],
    "Document.Type": "Invoice",
    "DocumentEssentials": [
      {
        "Confidence": 0.1015,
        "ConfidenceThreshold": -1,
        "Label": "Invoice.Id",
        "Location": {
          "Height": 31,
          "Left": 676,
          "Page": 1,
          "Top": 1222,
          "Width": 139
        },
        "Text": "448933",
        "Value": "448933"
      },
      {
        "Confidence": 0.9072,
        "ConfidenceThreshold": -1,
        "Label": "Invoice.Date",
        "Location": {
          "Height": 31,
          "Left": 2013,
          "Page": 1,
          "Top": 871,
          "Width": 205
        },
        "Text": "08.08.2023",
        "Value": "2023-08-08"
      },
      ...
```      



