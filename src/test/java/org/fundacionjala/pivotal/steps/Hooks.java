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

import java.util.List;

import io.cucumber.java.After;

import org.fundacionjala.core.api.Authentication;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.pivotal.ScenarioContext;

/**
 * This class delete all scenarios using a Prefix.
 */
public class Hooks {

    private static final String OWNER = "owner";

    private ScenarioContext context;

    public Hooks(final ScenarioContext context) {
        this.context = context;
    }

    @After("@cleanData")
    public void cleanData() {
        List<String> endpoints = context.getEndpoints();
        for (String endpoint : endpoints) {
            RequestManager.delete(Authentication.getRequestSpecification(OWNER), endpoint);
        }
    }
}
