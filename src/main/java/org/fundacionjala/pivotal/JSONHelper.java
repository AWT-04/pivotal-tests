package org.fundacionjala.pivotal;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public final class JSONHelper {
    private JSONHelper() {
    }

    public static JSONObject getJsonObject(final String fileName) {
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw new AWT04exception(1, e.getMessage());
        }
        return jsonObject;
    }
}
