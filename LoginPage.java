package Omnify_assignment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage {
    WebDriver driver;

    @FindBy(xpath="//input[@name=\"username\"]")WebElement username;
	@FindBy(xpath = "//input[@name=\"password\"]")WebElement password;
	@FindBy(xpath="//button[@type=\"submit\"]")WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String uname, String pwd) {
        username.sendKeys(uname);
        password.sendKeys(pwd);
        loginBtn.click();
    }
}
