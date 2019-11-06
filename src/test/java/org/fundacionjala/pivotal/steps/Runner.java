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

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.restassured.response.Response;

import org.fundacionjala.core.api.Authentication;
import org.fundacionjala.core.api.RequestManager;
import org.testng.annotations.BeforeTest;

import java.util.List;

@CucumberOptions(
        glue = {"org.fundacionjala"},
        features = "src/test/resources/features/"
)

/**
 * This class delete all.
 */
public class Runner extends AbstractTestNGCucumberTests {

    /**
     * This method delete before all scenarios.
     */
    @BeforeTest
    public void beforeAllScenarios() {
        Response response = RequestManager.get(Authentication.getRequestSpecification("owner"), "/projects");
        List<Integer> allID = response.jsonPath().getList("id");
        for (Integer id : allID) {
            RequestManager.delete(Authentication.getRequestSpecification("owner"), "/projects/" + id);
        }
        response = RequestManager.get(Authentication.getRequestSpecification("owner"), "/my/workspaces");
        allID = response.jsonPath().getList("id");
        for (Integer id : allID) {
            RequestManager.delete(Authentication.getRequestSpecification("owner"), "/my/workspaces/" + id);
        }
    }
}
