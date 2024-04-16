import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.model.*;
import org.openapitools.client.api.ReceiptApi;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.FileData;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://capture.bludelta.ai/receipt");

    ReceiptApi apiInstance = new ReceiptApi(defaultClient);
    String xApiKey = "YOUR_API_KEY"; // String | The customer's application key. Required for authentication
    String xApiIdentifier = null; // String | The customer's api identifier key. Not required for authentication
    File _file = new File("YOUR_FILE"); // File | 


    try {
        // Convert File to byte array
        byte[] dataBytes = Files.readAllBytes(_file.toPath());
        String contentType = "application/pdf"; // Determine as needed
        String fileName = "document.pdf";

        FileData fileData = new FileData(dataBytes, contentType, fileName);        

        ApiResponse<String> response = apiInstance.v1ReceiptPostWithHttpInfo(xApiKey, xApiIdentifier, fileData);
        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response headers: " + response.getHeaders());
        System.out.println("Response body: " + response.getData());
    } catch (ApiException e) {
        System.err.println("Exception when calling ReceiptApi#v1ReceiptPost");
        System.err.println("Status code: " + e.getCode());
        System.err.println("Reason: " + e.getResponseBody());
        System.err.println("Response headers: " + e.getResponseHeaders());
        e.printStackTrace();
    } catch (IOException e) {
        System.err.println("Error reading file into byte array");
        e.printStackTrace();
    }
  }
}