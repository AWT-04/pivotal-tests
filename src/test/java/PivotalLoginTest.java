import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class PivotalLoginTest {

    @Test
    public void login() throws InterruptedException {
        final String title = LocalDateTime.now().toString();
        WebDriverManager.chromedriver().setup();
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        browser.get("https://www.pivotaltracker.com/signin");
        browser.findElement(By.cssSelector("#credentials_username")).sendKeys("raullaredo");
        browser.findElement(By.cssSelector(".app_signin_action_button")).click();
        browser.findElement(By.cssSelector("#credentials_password")).sendKeys("r4514812L*");
        browser.findElement(By.cssSelector(".app_signin_action_button")).click();
        browser.findElement(By.cssSelector("#create-project-button")).click();
        browser.findElement(By.cssSelector(".tc-form__input")).sendKeys(title);
        browser.findElement(By.cssSelector(".tc-account-selector__header")).click();
        browser.findElement(By.xpath("//*[@id=\"modal_area\"]/div/div[2]/div/form/div/div/fieldset/div/div[2]/div[1]/ul/li/span[1]/div")).click();
        browser.findElement(By.cssSelector(".pvXpn__Button--positive")).click();
        (new WebDriverWait(browser, 30)).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".scrimVisible")));
        Assert.assertTrue(browser.getTitle().contains(title));
        browser.quit();
    }

    public static void ExplicitWait(WebDriver driver, String css){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
    }

    public static void ImplicitWait(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}

