package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"hashtags", "user_mentions"})
@SuppressWarnings("unused")
public class Entities {
    @JsonProperty("hashtags")
    private List<Hashtag> hashtags = null;
    @JsonProperty("user_mentions")
    private List<UserMention> userMentions = null;

    public Entities() {
    }

    @JsonProperty("hashtags")
    public List<Hashtag> getHashtags() {
        return this.hashtags;
    }

    @JsonProperty("hashtags")
    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    @JsonProperty("user_mentions")
    public List<UserMention> getUserMentions() {
        return this.userMentions;
    }

    @JsonProperty("user_mentions")
    public void setUserMentions(List<UserMention> userMentions) {
        this.userMentions = userMentions;
    }
}
