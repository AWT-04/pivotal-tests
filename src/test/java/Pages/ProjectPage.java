package Pages;

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

    @FindBy(how = How.CSS, using =".tc_projects_menu_list .tc_projects_menu_item. tc_projects_menu_item_link. raw_project_name")
    public WebElement itemProject;
}
