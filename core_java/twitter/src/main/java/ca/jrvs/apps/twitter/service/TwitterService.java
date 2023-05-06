package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.StringUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitterService implements ca.jrvs.apps.twitter.service.Service {
    private CrdDao<Tweet, String> dao;

    @Autowired
    public TwitterService(CrdDao dao) {
        this.dao = dao;
    }

    public Tweet postTweet(Tweet tweet) {
        TweetUtil.validatePostTweet(tweet);
        return (Tweet)this.dao.create(tweet);
    }

    public Tweet showTweet(String id, String[] fields) {
        if (!TweetUtil.validId.test(id)) {
            throw new IllegalArgumentException("ID must be number");
        } else {
            return (Tweet)this.dao.findById(id);
        }
    }

    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> tweets = new ArrayList();
        String[] var3 = ids;
        int var4 = ids.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String id = var3[var5];
            TweetUtil.validId.test(id);
            Tweet tweet = (Tweet)this.dao.deleteById(id);
            tweets.add(tweet);
        }

        return tweets;
    }

    protected Tweet selectFields(Tweet tweet, String[] fields) throws IOException {
        if (fields != null && fields.length != 0) {
            Tweet rTweet = (Tweet)JsonUtil.toObjectFromJson(JsonUtil.toPrettyJson(tweet), Tweet.class);
            Function<String[], String[]> trimStrArray = (items) -> {
                return (String[])Arrays.stream(items).map(String::trim).toArray((x$0) -> {
                    return new String[x$0];
                });
            };
            Set<String> fieldSet = new HashSet(Arrays.asList((Object[])trimStrArray.apply(fields)));
            Predicate<Method> isSetter = (method) -> {
                return method.getName().startsWith("set");
            };
            Arrays.stream(Tweet.class.getMethods()).filter(isSetter).forEach((setter) -> {
                JsonProperty jsonProperty = (JsonProperty)setter.getDeclaredAnnotation(JsonProperty.class);
                if (jsonProperty != null && !StringUtil.isEmpty(jsonProperty.value())) {
                    String value = jsonProperty.value();
                    if (fieldSet.contains(value)) {
                        fieldSet.remove(value);
                    } else {
                        try {
                            setter.invoke(rTweet, null);
                        } catch (InvocationTargetException | IllegalAccessException var6) {
                            throw new RuntimeException("unable to set setter:" + setter.getName(), var6);
                        }
                    }

                } else {
                    throw new RuntimeException("@JsonProperty is not defined for method" + setter.getName());
                }
            });
            if (!fieldSet.isEmpty()) {
                String invalidFields = String.join(",", fieldSet);
                throw new RuntimeException("Found invalid select field(s):" + invalidFields);
            } else {
                return rTweet;
            }
        } else {
            return tweet;
        }
    }
}
