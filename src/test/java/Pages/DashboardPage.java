package Pages;

import org.openqa.selenium.By;
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

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'ramalaso')]")
    public WebElement btnAccount;

    @FindBy(how = How.CSS, using = ".pvXpn__Button--positive")
    public WebElement btnAcceptCreateProject;

    @FindBy(how = How.XPATH, using = "//input[@value='private']")
    public WebElement optPrivate;

    @FindBy(how = How.XPATH, using = "//input[@value='public']")
    public WebElement optPublic;

    public void ClickElement(WebElement element){
        element.submit();
    }

    public void SendKeysToElement(WebElement element, String text){
        element.sendKeys(text);
    }

    public void SelectPrivacity(Boolean value){
        if (value){
            optPrivate.click();
        } else {
            optPublic.click();
        }
    }

    public WebElement SelectAccount(String account){
        WebElement optAccount=null;
        System.out.println("element = " + "//div[contains(text(),'"+ account +"')]");
        return optAccount.findElement(By.xpath("//div[contains(text(),'"+ account +"')]"));
    }
}
