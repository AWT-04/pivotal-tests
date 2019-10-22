package org.fundacionjala.pivotal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EndpointHelper {

    private EndpointHelper() {
    }

    public static String buildEndpoint(final String endPoint, final ScenarioContext context) {
        String[] endpointSplit = endPoint.split("/");
        for (int i = 0; i < endpointSplit.length; i++) {
            Pattern pattern = Pattern.compile("(?<=\\{)(.*?)(?=\\})");
            Matcher matcher = pattern.matcher(endpointSplit[i]);
            if (matcher.find()) {
                String[] keyValue = matcher.group().split("\\.");
                String valueToReplace = context.getContext(keyValue[0]).jsonPath().getString(keyValue[1]);
                endpointSplit[i] = valueToReplace;
            }
        }
        return String.join("/", endpointSplit);
    }
}
