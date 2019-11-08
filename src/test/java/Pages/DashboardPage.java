package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using ="#create-project-button")
    public WebElement btnCreateProject;

    @FindBy(how = How.CSS, using =".tc-form__input")
    public WebElement txtProjectName;

    @FindBy(how = How.CSS, using = ".tc-account-selector__header")
    public WebElement btnSelection;

    @FindBy(how = How.XPATH, using = "//*[@id=\"modal_area\"]/div/div[2]/div/form/div/div/fieldset/div/div[2]/div[1]/ul/li/span[1]/div")
    public WebElement optAccount;

    @FindBy(how = How.CSS, using = ".pvXpn__Button--positive")
    public WebElement btnAcceptCreateProject;

    public void ClickElement(WebElement element){
        element.submit();
    }

    public void SendKeysToElement(WebElement element, String text){
        element.sendKeys(text);
    }
}
