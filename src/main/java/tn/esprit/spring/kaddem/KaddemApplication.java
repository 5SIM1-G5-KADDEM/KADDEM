package tn.esprit.spring.kaddem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@SpringBootApplication
@RestController
public class KaddemApplication {



    public static void main(String[] args) {
        SpringApplication.run(KaddemApplication.class, args);
    }

}
