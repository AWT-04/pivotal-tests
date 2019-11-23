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

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.List;

/**
 * This class delete all.
 */
@CucumberOptions(
        glue = {"org.fundacionjala"},
        features = "src/test/resources/features/"
)
public class Runner extends AbstractTestNGCucumberTests {

    private static final String OWNER = "owner";

    /**
     * This method delete before all scenarios.
     */
    @BeforeTest
    public void beforeAllScenarios() {

        System.setProperty("dataproviderthreadcount", "5");

        Response response = RequestManager.get(Authentication.getRequestSpecification(OWNER), "/projects");
        List<Integer> allID = response.jsonPath().getList("id");
        for (Integer id : allID) {
            RequestManager.delete(Authentication.getRequestSpecification(OWNER), "/projects/" + id);
        }
        response = RequestManager.get(Authentication.getRequestSpecification(OWNER), "/my/workspaces");
        allID = response.jsonPath().getList("id");
        for (Integer id : allID) {
            RequestManager.delete(Authentication.getRequestSpecification(OWNER), "/my/workspaces/" + id);
        }
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterTest
    public void afterAllScenarios() {
        // clean data
    }
}
