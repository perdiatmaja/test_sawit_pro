package id.atmaja.test;

import id.atmaja.test.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("id.*")
public class MainApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(MainApplication.class);

        application.setDefaultProperties(AppProperties.createAppProperties());

        application.run(args);
    }
}
