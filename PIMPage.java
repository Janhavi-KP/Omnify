package Omnify_assignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage {
    WebDriver driver;

    @FindBy(xpath = "//a[text()='Add Employee']")
    WebElement addEmployeeBtn;
    @FindBy(xpath="(//input[@class=\"oxd-input oxd-input--active\"])[2]")
    WebElement empID;
    @FindBy(name = "firstName")
    WebElement firstName;

    @FindBy(xpath = "//input[@name='middleName']")
    WebElement midname;

    @FindBy(name = "lastName")
    WebElement lastName;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveBtn;

    @FindBy(xpath = "//a[text()='Employee List']")
    WebElement employeeListLink;
    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']")
    List<WebElement> employeeRows;
    @FindBy(xpath = "//i[@class=\"oxd-icon bi-caret-down-fill\"]") WebElement icon;
    @FindBy(xpath = "(//input[@placeholder=\"Type for hints...\"])[1]") WebElement searchname;
	@FindBy(xpath = "//button[@type=\"submit\"]") WebElement searchicon;
	@FindBy(xpath = "(//div[@class=\"data\"])[2]") WebElement valname;

   // @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']")
   // List<WebElement> employeeRows;

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addEmployee(String fName, String mName, String lName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Step 1: Click Add Employee button when clickable
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeBtn)).click();
        empID.clear();
        // Step 2: Fill the form when visible
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fName);
        midname.sendKeys(mName);
        lastName.sendKeys(lName);

        // Step 3: Wait for save button to be clickable and click it
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();

        // Step 4: Wait for redirect OR handle failure
        try {
            wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
            // Extra check: wait for personal details heading
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']")));
            System.out.println(" Employee added: " + fName + " " + lName);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println(" Employee not added: " + fName + " " + lName);
            System.out.println(" Current URL: " + driver.getCurrentUrl());

            // Step 5: Print error messages from the form (if any)
            List<WebElement> errors = driver.findElements(By.className("oxd-input-field-error-message"));
            for (WebElement err : errors) {
                System.out.println(" Form error: " + err.getText());
            }
        }
    }
    public void verifyEmployee(String firstName) {
        // Click on the employee list
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", employeeListLink);

        // Wait for the table to load (adjust XPath if needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-table-body')]")));

        // Get the rows in the table
        List<WebElement> rows = driver.findElements(By.xpath("//div[contains(@class, 'oxd-table-body')]//div[contains(@role, 'row')]"));
        
        boolean found = false;
        
        // Loop through the rows and check for the employee name
        for (WebElement row : rows) {
            if (row.getText().contains(firstName)) {
                System.out.println(" Name Found: " + firstName);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(" Name Not Found: " + firstName);
        }
    }

    }
   



