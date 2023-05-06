package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterDao implements CrdDao<Tweet, String> {
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";
    private static final int HTTP_OK = 200;
    private HttpHelper httpHelper;

    @Autowired
    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    public Tweet create(Tweet tweet) {
        URI uri;
        try {
            uri = this.getPostUri(tweet);
        } catch (UnsupportedEncodingException | URISyntaxException var4) {
            throw new IllegalArgumentException("Invalid tweet input", var4);
        }

        HttpResponse response = this.httpHelper.httpPost(uri);
        return this.parseResponseBody(response, 200);
    }

    protected Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
        Tweet tweet = null;
        int status = response.getStatusLine().getStatusCode();
        if (status != expectedStatusCode) {
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException var7) {
                System.out.println("Response has no entity");
            }

            throw new RuntimeException("Unexpected HTTP status:" + status);
        } else if (response.getEntity() == null) {
            throw new RuntimeException("Empty response body");
        } else {
            String jsonStr;
            try {
                jsonStr = EntityUtils.toString(response.getEntity());
            } catch (IOException var9) {
                throw new RuntimeException("Failed to convert entity to String", var9);
            }

            try {
                tweet = (Tweet)JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
                return tweet;
            } catch (IOException var8) {
                throw new RuntimeException("Unable to convert JSON str to Object", var8);
            }
        }
    }

    public Tweet findById(String id) {
        URI uri;
        try {
            uri = this.getShowUri(id);
        } catch (URISyntaxException var4) {
            throw new IllegalArgumentException("Unable to construct URI", var4);
        }

        HttpResponse response = this.httpHelper.httpGet(uri);
        return this.parseResponseBody(response, 200);
    }

    public Tweet deleteById(String id) {
        URI uri;
        try {
            uri = this.getDeleteUri(id);
        } catch (URISyntaxException var4) {
            throw new IllegalArgumentException("Unable to construct URI", var4);
        }

        HttpResponse response = this.httpHelper.httpPost(uri);
        return this.parseResponseBody(response, 200);
    }

    private URI getDeleteUri(String id) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.twitter.com").append("/1.1/statuses/destroy").append("/").append(id).append(".json");
        return new URI(sb.toString());
    }

    private URI getShowUri(String id) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.twitter.com").append("/1.1/statuses/show.json").append("?");
        this.appendQueryParam(sb, "id", id, true);
        return new URI(sb.toString());
    }

    private URI getPostUri(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException {
        String text = tweet.getText();
        Double longitude = (Double)tweet.getCoordinates().getCoordinates().get(0);
        Double latitude = (Double)tweet.getCoordinates().getCoordinates().get(1);
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.twitter.com").append("/1.1/statuses/update.json").append("?");
        this.appendQueryParam(sb, "status", URLEncoder.encode(text, StandardCharsets.UTF_8.name()), true);
        this.appendQueryParam(sb, "long", longitude.toString(), false);
        this.appendQueryParam(sb, "lat", latitude.toString(), false);
        return new URI(sb.toString());
    }

    private void appendQueryParam(StringBuilder sb, String key, String value, boolean firstParam) {
        if (!firstParam) {
            sb.append("&");
        }

        sb.append(key).append("=").append(value);
    }
}
