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
import io.cucumber.java.Before;
import io.restassured.response.Response;

import org.fundacionjala.core.api.Authentication;
import org.fundacionjala.core.api.RequestManager;

import java.util.List;

/**
 * This class delete all scenarios using a Prefix.
 */
public class Hooks {
    /**
     * Delete all projects using a prefix.
     */
    @After("@cleanProjects")
    public void iSendDeleteAllToByPrefix() {
        final String prefix = "AT_";
        Response response = RequestManager.get(Authentication.getRequestSpecification("owner"),
                "/projects");
        List<String> allName = response.jsonPath().getList("name");
        List<Integer> allID = response.jsonPath().getList("id");
        for (int i = 0; i < allName.size(); i++) {
            if (allName.get(i) != null && allName.get(i).contains(prefix)) {
                RequestManager.delete(Authentication.getRequestSpecification("owner"),
                "/projects/" + allID.get(i));
            }
        }
    }

    /**
     * Delete all projects using a prefix before running the scenaro.
     */
    @Before("@cleanProjectsBefore")
    public void iSendDeleteAllToByPrefixBefore() {
        final String prefix = "AT_";
        Response response = RequestManager.get(Authentication.getRequestSpecification("owner"),
                "/projects");
        List<String> allName = response.jsonPath().getList("name");
        List<Integer> allID = response.jsonPath().getList("id");
        for (int i = 0; i < allName.size(); i++) {
            if (allName.get(i) != null && allName.get(i).contains(prefix)) {
                RequestManager.delete(Authentication.getRequestSpecification("owner"),
                "/projects/" + allID.get(i));
            }
        }
    }


    /**
     * Delete all workspaces using a prefix.
     */

    @After("@cleanWorkspaces")
    public void iSendDeleteAllWSToByPrefix() {
        final String prefix = "AT_";
        Response response = RequestManager.get(Authentication.getRequestSpecification("owner"),
                "/my/workspaces");
        List<String> allName = response.jsonPath().getList("name");
        List<Integer> allID = response.jsonPath().getList("id");
        for (int i = 0; i < allName.size(); i++) {
            if (allName.get(i) != null && allName.get(i).contains(prefix)) {
                RequestManager.delete(Authentication.getRequestSpecification("owner"),
                        "/my/workspaces/" + allID.get(i));
            }
        }
    }

    /**
     * Delete all workspaces using a prefix before running the scenaro.
     */
    @Before("@cleanWorkspacesBefore")
    public void iSendDeleteAllWSToByPrefixBefore() {
        final String prefix = "AT_";
        Response response = RequestManager.get(Authentication.getRequestSpecification("owner"),
                "/my/workspaces");
        List<String> allName = response.jsonPath().getList("name");
        List<Integer> allID = response.jsonPath().getList("id");
        for (int i = 0; i < allName.size(); i++) {
            if (allName.get(i) != null && allName.get(i).contains(prefix)) {
                RequestManager.delete(Authentication.getRequestSpecification("owner"),
                "/my/workspaces/" + allID.get(i));
            }
        }
    }
}
