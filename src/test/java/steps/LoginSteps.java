package steps;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import org.testng.Assert;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("the user is on the OrangeHRM login page")
    public void user_is_on_login_page() throws Exception {
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        loginPage = new LoginPage(driver);
    }

    @When("the user enters valid credentials")
    public void user_enters_valid_credentials() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
    }

    @And("clicks on the login button")
    public void clicks_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("the user should be redirected to the homepage")
    public void user_should_be_redirected_to_homepage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        driver.quit();
    }
}
