package guru.springframework.sdjpaintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Setzt auch ein @ComponentScan mit diesem package als root
public class SdjpaIntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdjpaIntroApplication.class, args);
    }

}
