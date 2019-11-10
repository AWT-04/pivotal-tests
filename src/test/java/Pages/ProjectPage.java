package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProjectPage {
    public ProjectPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using =".tc_projects_dropdown_link.tc_context_name")
    public WebElement btnAllProjects;

    @FindBy(how = How.XPATH, using ="//span[contains(text(),'2019-11-08T08:05:13.697284')]")
    public WebElement itemProject;

    @FindBy(how = How.CSS, using =".tc_projects_dropdown_link.tc_context_name")
    public WebElement btnProjects;



//    public WebElement ProjectInList(String projectName, WebElement element){
//        System.out.println("xpath = " + String.format("//*[@class='raw_project_name public' and contains(text(),'%s')]", projectName));
//        return  .findElement(By.xpath(String.format("//*[@class='raw_project_name public' and contains(text(),'%s')]", projectName)));
//    }
}
