package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.App;
import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class TwitterCLIBean {

    public TwitterCLIBean() {}

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(new Class[]{TwitterCLIBean.class});
        App app = (App)context.getBean(App.class);
        app.run(args);
    }

    @Bean
    public App twitterCLIApp(Controller controller) {
        return new App(controller);
    }

    @Bean
    public Controller controller(Service service) {
        return new TwitterController(service);
    }

    @Bean
    public Service service(CrdDao dao) {
        return new TwitterService(dao);
    }

    @Bean
    public CrdDao crdDao(HttpHelper httpHelper) {
        return new TwitterDao(httpHelper);
    }

    @Bean
    HttpHelper helper() {
        String consumerKey = System.getenv("consumer.key");
        String consumerSecret = System.getenv("consumer.secret");
        String accessToken = System.getenv("access.token");
        String tokenSecret = System.getenv("access.token.secret");
        return new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    }
}
