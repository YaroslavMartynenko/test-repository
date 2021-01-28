package interesting_test_app.util;

import com.google.gson.Gson;

public final class JsonConverter {

    private static final Gson mapper = new Gson();

    public static String convertToJson(Object objectToConvert) {
        return mapper.toJson(objectToConvert);
    }

    public static <T> T convertFromJson(String jsonString, Class<T> valueType) {
        return mapper.fromJson(jsonString, valueType);
    }
}
