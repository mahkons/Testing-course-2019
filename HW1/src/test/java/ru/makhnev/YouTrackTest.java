package ru.makhnev;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class YouTrackTest {

    private WebDriver driver;
    private HomePage root;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/WebDriver/bin/chromedriver");
        driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver, "http://localhost:8080/dashboard");
        loginPage.switchTo();
        loginPage.login("root", "1"); // should be root to be able delete issues

        root = new HomePage(driver, "http://localhost:8080/dashboard");
        root.switchTo();
    }


    @AfterEach
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void createIssue() {
        Issue expectedIssue = new Issue("aaa", "bbb");

        IssueFormPage formPage = root.toIssueForm();
        formPage.createIssue(expectedIssue);
        IssuePage issuePage = root.toIssuePage();
        Issue issue = issuePage.getLastIssue();
        assertEquals(expectedIssue, issue);
        issuePage.deleteLastIssue();
    }

}
