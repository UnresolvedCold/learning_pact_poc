package code.shubham.kitchenui;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactBuilder;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
public class PactConsumerTest {

  @Pact(consumer = "KitchenUI", provider = "KitchenServer")
  public V4Pact kitchenItemLocationFeature(PactBuilder builder) {
    return builder
        .usingLegacyDsl()
        .uponReceiving("A request to get item details")
        .path("/kitchen/get_item_location")
        .method("POST")
        .body("{\"key\": \"rice\"}")
        .willRespondWith()
        .status(200)
        .body("{\"location\": \"left_side_drawer\", \"container\": \"red_bowl\"}")
        .toPact(V4Pact.class);
  }

  @Test
  @PactTestFor(pactMethod = "kitchenItemLocationFeature")
  void testKitchenServer(MockServer mockServer) throws Exception {
      String url = mockServer.getUrl() + "/kitchen/get_item_location";

      HttpRequest request = HttpRequest.newBuilder()
          .uri(new java.net.URI(url))
          .header("Content-Type", "application/json")
          .POST(java.net.http.HttpRequest.BodyPublishers.ofString("{\"key\": \"rice\"}"))
          .build();

      HttpClient client = HttpClient.newHttpClient();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      assertEquals(200, response.statusCode());
      assertEquals("{\"location\": \"left_side_drawer\", \"container\": \"red_bowl\"}", response.body());
  }
}
