package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
@SuppressWarnings("unused")
public class TwitterCLIAppTest {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties props = new Properties();
    public static final String USAGE = "USAGE: TwitterCLIApp post|show|delete [options]";
    private final Controller controller;

    @Autowired
    public TwitterCLIAppTest(Controller controller) {
        this.controller = controller;
    }

    public void run(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp post|show|delete [options]");
        } else {
            switch (args[0].toLowerCase()) {
                case "post":
                    this.printTweet(this.controller.postTweet(args));
                    break;
                case "show":
                    this.printTweet(this.controller.showTweet(args));
                    break;
                case "delete":
                    this.controller.deleteTweet(args).forEach(this::printTweet);
                    break;
                default:
                    throw new IllegalArgumentException("USAGE: TwitterCLIApp post|show|delete [options]");
            }

        }
    }

    private void printTweet(Tweet tweet) {
        try {
            System.out.println(JsonUtil.toPrettyJson(tweet));
        } catch (JsonProcessingException var3) {
            throw new RuntimeException("Unable to convert tweet object to string", var3);
        }
    }

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE);
            props.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Error reading config file", e);
        }

        HttpHelper httpHelper = new TwitterHttpHelper(
                props.getProperty("consumer.key"),
                props.getProperty("consumer.secret"),
                props.getProperty("access.token"),
                props.getProperty("access.token.secret"));

        CrdDao<Tweet, String> dao = new TwitterDao(httpHelper);
        Service service = new TwitterService(dao);
        Controller controller = new TwitterController(service);
        TwitterCLIApp app = new TwitterCLIApp(controller);
        app.run(args);
        controller.postTweet(args);

    }

}
