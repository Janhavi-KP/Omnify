package Omnify_assignment;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRMTest {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    PIMPage pimPage;

    String[][] employees = {
        {"John","J", "Doe"},
        {"Jane","M", "Smith"},
        {"Mike","K", "Brown"},
        {"Sara","O", "Lee"}
    };

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        pimPage = new PIMPage(driver);
    }

    @Test(priority = 1)
    public void loginToOrangeHRM() {
        loginPage.login("Admin", "admin123");
    }

    @Test(priority = 2)
    public void navigateToPIMModule() {
        dashboardPage.goToPIM();
    }

    @Test(priority = 3)
    public void addMultipleEmployees() {
        pimPage.addEmployee("John", "J", "Doe");
        pimPage.addEmployee("Jane", "M", "Smith");
        pimPage.addEmployee("Mike", "K", "Brown");
        pimPage.addEmployee("Sara", "O", "Lee");
    }
    @Test(priority = 3)
    public void verifyEmployeesInList() {
        pimPage.verifyEmployee("John");
        pimPage.verifyEmployee("Jane");
        pimPage.verifyEmployee("Mike");
        pimPage.verifyEmployee("Sara");
    }

    @Test(priority = 5)
    public void logoutFromApplication() {
        dashboardPage.logout();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

