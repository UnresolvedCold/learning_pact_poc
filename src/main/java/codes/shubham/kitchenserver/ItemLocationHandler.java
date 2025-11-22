package codes.shubham.kitchenserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/kitchen")
public class ItemLocationHandler {

  @Autowired
  private ItemDB itemDB;

  @PostMapping("/get_item_location")
  public ResponseEntity<Map<String, String>> getItemLocation(@RequestBody Map<String, String> request) {
    String key = request.get("key");

    String location = itemDB.getItemLocation(key);
    String container = itemDB.getItemContainer(key);

    if (location != null && container != null) {
      return ResponseEntity.ok(Map.of("location", location, "container", container));
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
