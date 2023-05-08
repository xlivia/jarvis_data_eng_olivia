package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"name", "indices", "screen_name", "id", "id_str"})
@SuppressWarnings("unused")
public class UserMention {
    @JsonProperty("name")
    private String name;
    @JsonProperty("indices")
    private List<Long> indices = null;
    @JsonProperty("screen_name")
    private String screenName;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("id_str")
    private String idStr;

    public UserMention() {
    }

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("indices")
    public List<Long> getIndices() {
        return this.indices;
    }

    @JsonProperty("indices")
    public void setIndices(List<Long> indices) {
        this.indices = indices;
    }

    @JsonProperty("screen_name")
    public String getScreenName() {
        return this.screenName;
    }

    @JsonProperty("screen_name")
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @JsonProperty("id")
    public Long getId() {
        return this.id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("id_str")
    public String getIdStr() {
        return this.idStr;
    }

    @JsonProperty("id_str")
    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }
}
