package ca.jrvs.apps.twitter.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JsonUtil {
    public JsonUtil() {
    }

    public static String toPrettyJson(Object object) throws JsonProcessingException {
        return toJson(object, true, false);
    }

    public static String toJson(Object object, boolean prettyJson, boolean includeNullValues) throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        if (!includeNullValues) {
            m.setSerializationInclusion(Include.NON_NULL);
        }

        if (prettyJson) {
            m.enable(SerializationFeature.INDENT_OUTPUT);
        }

        return m.writeValueAsString(object);
    }

    @SuppressWarnings("commented-out-code")
    /*public static <T> T toObjectFromJson(String json, Class<ca.jrvs.apps.twitter.model.Tweet> c) throws IOException {
        ObjectMapper m = new ObjectMapper();
        m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T)m.readValue(json, c);
    }
    public static <T> T toObjectFromJson(String json, Class c) throws IOException {
        ObjectMapper m = new ObjectMapper();
        m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T)m.readValue(json, c);
    }*/
    public static <T> T toObjectFromJson(String json, Class<T> c) throws IOException {
        ObjectMapper m = new ObjectMapper();
        m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return m.readValue(json, c);
    }

}
