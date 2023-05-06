package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.App;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ca.jrvs.apps.twitter"})
public class TwitterCLIComponentScan {
    public TwitterCLIComponentScan() {}
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(new Class[]{TwitterCLIComponentScan.class});
        App app = (App)context.getBean(App.class);
        app.run(args);
    }
}
