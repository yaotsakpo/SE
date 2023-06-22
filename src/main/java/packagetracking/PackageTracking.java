package packagetracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class PackageTracking {

    public static void main(String[] args) {
        SpringApplication.run(PackageTracking.class, args);
    }

}
