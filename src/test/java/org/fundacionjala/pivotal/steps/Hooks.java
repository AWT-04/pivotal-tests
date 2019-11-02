/**
 * Copyright (c) 2019 Jalasoft.
 * <p>
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package org.fundacionjala.pivotal.steps;

import io.cucumber.java.After;
import io.restassured.response.Response;
import org.fundacionjala.core.api.RequestManager;

import java.util.List;

/**
 * This class delete all scenarios using a Prefix.
 *
 * @author Fernando Hinojosa on 11/02/2019.
 * @version v1.0
 */
public class Hooks {
    /**
     * Delete all projects using a prefix.
     */
    @After("@cleanProjects")
    public void iSendDeleteAllToByPrefix() {
        final String prefix = " ";
        Response response = RequestManager.get("/projects");
        List<String> allName = response.jsonPath().getList("name");
        List<Integer> allID = response.jsonPath().getList("id");
        for (int i = 0; i < allName.size(); i++) {
            if (allName.get(i) != null && allName.get(i).contains(prefix)) {
                RequestManager.delete("/projects/" + allID.get(i));
            }
        }
    }

    /**
     * Delete all workspaces using a prefix.
     */
    @After("@cleanWorkspaces")
    public void iSendDeleteAllWSToByPrefix() {
        final String prefix = "AT_";
        Response response = RequestManager.get("/my/workspaces");
        List<String> allName = response.jsonPath().getList("name");
        List<Integer> allID = response.jsonPath().getList("id");
        for (int i = 0; i < allName.size(); i++) {
            if (allName.get(i) != null && allName.get(i).contains(prefix)) {
                RequestManager.delete("/my/workspaces/" + allID.get(i));
            }
        }
    }
}
