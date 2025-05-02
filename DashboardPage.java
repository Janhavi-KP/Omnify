package Omnify_assignment;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;

public class DashboardPage {
    WebDriver driver;

    @FindBy(xpath = "//span[text()='PIM']")
    WebElement pimMenu;

    @FindBy(xpath="//i[@class=\"oxd-icon bi-caret-down-fill oxd-userdropdown-icon\"]")
    WebElement userDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    WebElement logout;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPIM() {
        new Actions(driver).moveToElement(pimMenu).click().perform();
    }

    public void logout() {
        userDropdown.click();
        logout.click();
    }
}

