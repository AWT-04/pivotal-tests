/**
 * Copyright (c) 2019 Jalasoft.
 * <p>
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package org.fundacionjala.core.utils;

import org.fundacionjala.pivotal.ScenarioContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class build the different endpoint.
 *
 * @author Fernando Hinojosa on 11/02/2019.
 * @version v1.0
 */
public final class EndpointHelper {

    private EndpointHelper() {
    }

    /**
     * Build endpoint using regular expressions.
     *
     * @param endPoint basic information.
     * @param context  basic.
     * @return string with the final endpoint.
     */
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
