/**
 * Copyright (c) 2019 Jalasoft.
 * <p>
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package org.fundacionjala.pivotal;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import com.jayway.jsonpath.DocumentContext;

/**
 * This class use the different variable environment.
 *
 * @author Fernando Hinojosa on 11/02/2019.
 * @version v1.0
 */
public final class Environment {
    private static final String CONF_JSON_FILE = "./configJson/config.json";
    private static Environment environmentInstance;
    private DocumentContext documentContext;

    /**
     * This is the constructor of the classes.
     */
    private Environment() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(CONF_JSON_FILE)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            documentContext = JsonPath.parse(jsonObject);
        } catch (IOException | ParseException e) {
            throw new AWT04exception(1, e.getMessage());
        }
    }

    /**
     * Verify the exists a instances of environment classes.
     *
     * @return environment.
     */
    public static Environment getInstance() {
        if (environmentInstance == null) {
            environmentInstance = new Environment();
        }
        return environmentInstance;
    }

    /**
     * This method return the value of the json file.
     *
     * @param value of the json file.
     * @return information value.
     */
    public String getValue(final String value) {
        return this.documentContext.read(value);
    }

    /**
     * This method gets the user name.
     *
     * @param value of the json file.
     * @return string with the user name.
     */
    public String getUser(final String value) {
        final String userName = String.format("credentials,%s,username", value);
        return Environment.getInstance().getValue(userName);
    }

    /**
     * This method gets the account name.
     *
     * @param value of the json file.
     * @return string with the account information.
     */
    public String getAccount(final String value) {
        return getKey(getUser(value), '@').replace(".", "");
    }

    /**
     * This method read a specific line in the json file.
     *
     * @param line  specific in the json file.
     * @param limit of the line.
     * @return string with the key.
     */
    public String getKey(final String line, final char limit) {
        final int index = line.indexOf(limit);
        return line.substring(0, index);
    }
}
