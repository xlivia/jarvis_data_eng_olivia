package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIApp;
import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("unused")
public class TwitterCLIBean {

    public TwitterCLIBean() {}

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
        TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
        app.run(args);
    }

    @Bean
    public TwitterCLIApp twitterCLIApp(Controller controller) {
        return new TwitterCLIApp(controller);
    }

    @Bean
    public Controller controller(Service service) {
        return new TwitterController(service);
    }

    @Bean
    public Service service(CrdDao<Tweet, String> dao) {
        return new TwitterService(dao);
    }

    @Bean
    public CrdDao<Tweet, String> crdDao(HttpHelper httpHelper) {
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
