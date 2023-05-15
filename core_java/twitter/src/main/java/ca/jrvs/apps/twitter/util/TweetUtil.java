package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.Arrays;
import java.util.function.Predicate;

public class TweetUtil {
    private static final Double MAX_LAT = 90.0;
    private static final Double MIN_LAT = -90.0;
    private static final Double MAX_LON = 180.0;
    private static final Double MIN_LON = -180.0;
    private static final Integer MAX_TWEET_CHAR = 140;
    public static Predicate<String> validId = (id) -> !StringUtil.isEmpty(id) && id.chars().noneMatch((c) -> c < 48 || c > 57);

    public TweetUtil() {
    }

    public static void validatePostTweet(Tweet tweet) {
        String text = tweet.getText();
        Double longitude = tweet.getCoordinates().getCoordinates().get(0);
        Double latitude = tweet.getCoordinates().getCoordinates().get(1);
        validatePostTweet(text, longitude, latitude);
    }

    public static void validatePostTweet(String text, Double longitude, Double latitude) {
        if (!StringUtil.isEmpty(text) && text.toCharArray().length <= MAX_TWEET_CHAR) {
            if (latitude < MIN_LAT || latitude > MAX_LAT || longitude < MIN_LON || longitude > MAX_LON) {
                throw new IllegalArgumentException("Invalid latitude or longitude value");
            }
        } else {
            throw new IllegalArgumentException("Invalid Tweet");
        }
    }

    public static Tweet buildTweet(String text, Double longitude, Double latitude) {
        Tweet tweet = new Tweet();
        tweet.setText(text);
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(Arrays.asList(longitude, latitude));
        tweet.setCoordinates(coordinates);
        return tweet;
    }
}
