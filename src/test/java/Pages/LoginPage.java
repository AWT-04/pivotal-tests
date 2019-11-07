package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using ="#credentials_username")
    public WebElement txtUserName;

    @FindBy(how = How.CSS, using =".app_signin_action_button")
    public WebElement btnActionButton;

    @FindBy(how = How.CSS, using = "#credentials_password")
    public WebElement txtPassword;
}
