package cool.tch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SslSentinelBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SslSentinelBackendApplication.class, args);
    }

}
