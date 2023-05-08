package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.StringUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TwitterController implements ca.jrvs.apps.twitter.controller.Controller {

    private final Service service;

    @Autowired
    public TwitterController(Service service) {
        this.service = service;
    }

    public Tweet postTweet(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        } else {
            String tweet_txt = args[1];
            String cord = args[2];
            String[] cordArray = cord.split(":");
            if (cordArray.length == 2 && !StringUtil.isEmpty(tweet_txt)) {
                double lat, lon;

                try {
                    lat = Double.parseDouble(cordArray[0]);
                    lon = Double.parseDouble(cordArray[1]);
                } catch (Exception var8) {
                    throw new IllegalArgumentException("Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"", var8);
                }

                Tweet postTweet = TweetUtil.buildTweet(tweet_txt, lon, lat);
                return this.service.postTweet(postTweet);
            } else {
                throw new IllegalArgumentException("Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
            }
        }
    }

    public Tweet showTweet(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp show tweet_id [fields]");
        } else {
            String[] fieldsArray = null;
            String tweet_id = null;
            switch (args.length) {
                case 3:
                    String fields = args[2];
                    if (StringUtil.isEmpty(fields)) {
                        throw new IllegalArgumentException("Error: empty fields. USAGE: TwitterCLIApp show tweet_id [fields]");
                    } else {
                        fieldsArray = fields.split(",");
                    }
                case 2:
                    tweet_id = args[1];
                    if (StringUtil.isEmpty(tweet_id)) {
                        throw new IllegalArgumentException("Error: Empty ID\nUSAGE: TwitterCLIApp show tweet_id [fields]");
                    }
                default:
                    return this.service.showTweet(tweet_id, fieldsArray);
            }
        }
    }

    public List<Tweet> deleteTweet(String[] args) {
        if (args.length == 2 && !StringUtil.isEmpty(args[1])) {
            String tweetIds = args[1];
            String[] ids = tweetIds.split(",");
            return this.service.deleteTweets(ids);
        } else {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp deleteTweets tweet_ids");
        }
    }
}
