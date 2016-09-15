package org.fundacionjala.pivotaltracker.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.restassured.response.Response;

import static io.restassured.path.json.JsonPath.from;


public class Mapper {

    private static final String REGEX_INSIDE_BRACKETS = "(?<=\\[)(.*?)(?=\\])";

    private static final String REGEX_DOT = "\\.";

    private static final String REGEX_BRACKETS = "[\\[\\]]";

    private static final int KEY = 0;

    private static final int VALUE = 1;

    private static final int LIMIT = 2;

    public static final Map<String, Response> RESPONSE_VALUES = new HashMap<>();


    private Mapper() {
    }

    public static String getField(Response response, String parameter) {
        return from(response.asString()).get(parameter).toString();
    }

    public static String mapEndpoint(String endPoint) {
        Matcher matches = Pattern.compile(REGEX_INSIDE_BRACKETS).matcher(endPoint);
        StringBuffer newEndPoint = new StringBuffer();

        while (matches.find()) {
            String[] parametersParts = matches.group().split(REGEX_DOT, LIMIT);
            String key = parametersParts[KEY];
            String value = parametersParts[VALUE];
            String replaceParameter = Mapper.getField(RESPONSE_VALUES.get(key), value);
            matches.appendReplacement(newEndPoint, replaceParameter);
        }
        matches.appendTail(newEndPoint);
        return newEndPoint.toString().replaceAll(REGEX_BRACKETS, "");
    }

    public static void addResponse(String key, Response response) {
        RESPONSE_VALUES.put(key, response);
    }
}