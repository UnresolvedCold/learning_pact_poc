package codes.shubham.kitchenserver;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ItemDB {
  Map<String, String> itemLocationMap = Map.of(
      "rice", "left_side_drawer",
      "lentils", "main_rack",
      "spices", "spice_rack"
  );

  Map<String, String> itemContainerMap = Map.of(
      "rice", "red_bowl",
      "lentils", "square_box",
      "spices", "small_jar"
  );

  public String getItemLocation(String item) {
    return itemLocationMap.get(item);
  }

  public String getItemContainer(String item) {
    return itemContainerMap.get(item);
  }
}
