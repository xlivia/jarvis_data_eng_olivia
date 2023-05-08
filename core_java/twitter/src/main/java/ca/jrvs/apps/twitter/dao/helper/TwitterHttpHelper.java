package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class TwitterHttpHelper implements HttpHelper {

    private final OAuthConsumer consumer;
    private final HttpClient httpClient;

    public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        this.consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        this.consumer.setTokenWithSecret(accessToken, tokenSecret);
        this.httpClient = HttpClientBuilder.create().build();
    }

    public TwitterHttpHelper() {
        String consumerKey = System.getenv("consumer.key");
        String consumerSecret = System.getenv("consumer.secret");
        String accessToken = System.getenv("access.token");
        String tokenSecret = System.getenv("access.token.secret");
        this.consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        this.consumer.setTokenWithSecret(accessToken, tokenSecret);
        //this.httpClient = new DefaultHttpClient();
        this.httpClient = HttpClients.createDefault();
    }

    public HttpResponse httpPost(URI uri) {
        try {
            return this.executeHttpRequest(HttpMethod.POST, uri, null);
        } catch (IOException | OAuthException var3) {
            throw new RuntimeException("Failed to execute", var3);
        }
    }

    private HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity stringEntity) throws OAuthException, IOException {
        if (method == HttpMethod.GET) {
            HttpGet request = new HttpGet(uri);
            this.consumer.sign(request);
            return this.httpClient.execute(request);
        } else if (method == HttpMethod.POST) {
            HttpPost request = new HttpPost(uri);
            if (stringEntity != null) {
                request.setEntity(stringEntity);
            }

            this.consumer.sign(request);
            return this.httpClient.execute(request);
        } else {
            throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
        }
    }

    public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
        try {
            return this.executeHttpRequest(HttpMethod.POST, uri, stringEntity);
        } catch (IOException | OAuthException var4) {
            throw new RuntimeException("Failed to execute", var4);
        }
    }

    public HttpResponse httpGet(URI uri) {
        try {
            return this.executeHttpRequest(HttpMethod.GET, uri, null);
        } catch (IOException | OAuthException var3) {
            throw new RuntimeException("Failed to execute", var3);
        }
    }

    public static void main(String[] args) throws Exception {
        String consumerKey = System.getenv("consumer.key");
        String consumerSecret = System.getenv("consumer.secret");
        String accessToken = System.getenv("access.token");
        String tokenSecret = System.getenv("token.secret");
        System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com//1.1/statuses/update.json?status=first_tweet2"));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

}
