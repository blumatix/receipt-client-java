# ReceiptApi

All URIs are relative to *https://capture.bludelta.ai/receipt*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**v1ReceiptPost**](ReceiptApi.md#v1ReceiptPost) | **POST** /v1/receipt |  |


<a id="v1ReceiptPost"></a>
# **v1ReceiptPost**
> v1ReceiptPost(xApiKey, xApiIdentifier, _file)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReceiptApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://capture.bludelta.ai/receipt");

    ReceiptApi apiInstance = new ReceiptApi(defaultClient);
    String xApiKey = "xApiKey_example"; // String | The customer's application key. Required for authentication
    String xApiIdentifier = "xApiIdentifier_example"; // String | The customer's api identifier key. Not required for authentication
    File _file = new File("/path/to/file"); // File | 
    try {
      apiInstance.v1ReceiptPost(xApiKey, xApiIdentifier, _file);
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

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **xApiKey** | **String**| The customer&#39;s application key. Required for authentication | |
| **xApiIdentifier** | **String**| The customer&#39;s api identifier key. Not required for authentication | [optional] |
| **_file** | **File**|  | [optional] |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |

