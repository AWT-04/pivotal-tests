package org.fundacionjala.pivotal.steps;

import io.cucumber.java.After;
import io.restassured.response.Response;
import org.fundacionjala.pivotal.RequestManager;

import java.util.List;

public class Hooks {
    @After("@cleanProjects")
    public void iSendDeleteAllToByPrefix() {
        final String prefix = " ";
        Response response = RequestManager.setGet("/projects");
        List<String> allName = response.jsonPath().getList("name");
        List<Integer> allID = response.jsonPath().getList("id");
        for (int i = 0; i < allName.size(); i++) {
            if (allName.get(i) != null && allName.get(i).contains(prefix)) {
                RequestManager.delete("/projects/" + allID.get(i));
            }
        }
    }
}
