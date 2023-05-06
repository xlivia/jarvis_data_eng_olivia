package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"ca.jrvs.apps.twitter"}
)
public class TwitterCLISpringBoot implements CommandLineRunner {
    private App app;

    @Autowired
    public TwitterCLISpringBoot(App app) {
        this.app = app;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(new Class[]{TwitterCLISpringBoot.class});
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    public void run(String... args) throws Exception {
        this.app.run(args);
    }
}
