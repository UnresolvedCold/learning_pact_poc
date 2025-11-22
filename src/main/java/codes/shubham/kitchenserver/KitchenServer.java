package codes.shubham.kitchenserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"codes.shubham.kitchenserver"})
@SpringBootConfiguration
public class KitchenServer {
    public static void main(String[] args) {
        SpringApplication.run(KitchenServer.class, args);
    }
}
