package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"text", "indices"})
@SuppressWarnings("unused")
public class Hashtag {
    @JsonProperty("text")
    private String text;
    @JsonProperty("indices")
    private List<Long> indices = null;

    public Hashtag() {
    }

    @JsonProperty("text")
    public String getText() {
        return this.text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("indices")
    public List<Long> getIndices() {
        return this.indices;
    }

    @JsonProperty("indices")
    public void setIndices(List<Long> indices) {
        this.indices = indices;
    }

    public String toString() {
        return "Hashtag{text='" + this.text + '\'' + ", indices=" + this.indices + '}';
    }

    public static void main(String[] args) {
        Hashtag hashtag = new Hashtag();
        System.out.println(hashtag);
    }

}
