package org.fundacionjala.pivotal;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import com.jayway.jsonpath.DocumentContext;

public final class Environment {
    private static final String CONF_JSON_FILE = "./configJson/config.json";
    private static Environment environmentInstance;
    private DocumentContext documentContext;

    private Environment() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(CONF_JSON_FILE)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            documentContext = JsonPath.parse(jsonObject);
        } catch (IOException | ParseException e) {
            throw new AWT04exception(1, e.getMessage());
        }
    }

    public static Environment getInstance() {
        if (environmentInstance == null) {
            environmentInstance = new Environment();
        }
        return environmentInstance;
    }

    public String getValue(final String value) {
        return this.documentContext.read(value);
    }

    public String getUser(final String value) {
        final String userName = String.format("credentials,%s,username",value);
        return Environment.getInstance().getValue(userName);
    }

    public String getAccount(final String value) {
        return getKey(getUser(value), '@').replace(".", "");
    }

    public String getKey(final String line, final char limit) {
        final int index = line.indexOf(limit);
        return line.substring(0, index);
    }
}
