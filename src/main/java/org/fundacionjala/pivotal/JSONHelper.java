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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * The class permit read JSON files.
 *
 * @author Fernando Hinojosa on 11/02/2019.
 * @version v1.0
 */
public final class JSONHelper {
    /**
     * Constructor JSONHelper classes.
     */
    private JSONHelper() {
    }

    /**
     * This class read a Json file.
     *
     * @param fileName of the json.
     * @return a JSON Object.
     */
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
