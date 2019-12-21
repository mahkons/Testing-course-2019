package ru.makhnev;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


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

    private void createIssueHelper(String summary, String description, String expectedSummary, String expectedDescription) {
        Issue issueAdded = new Issue(summary, description);
        Issue expectedIssue = new Issue(expectedSummary, expectedDescription);

        IssueFormPage formPage = root.toIssueForm();
        formPage.createIssue(issueAdded);
        IssuePage issuePage = root.toIssuePage();
        Issue issue = issuePage.getLastIssue();
        assertEquals(expectedIssue, issue);
        issuePage.deleteLastIssue();
    }

    private void createIssueHelper(String summary, String description) {
        createIssueHelper(summary, description, summary, description);
    }

    private void deleteLastIssue() {
        IssuePage issuePage = root.toIssuePage();
        issuePage.getLastIssue();
        issuePage.deleteLastIssue();
    }

    private String genRepeat(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append('a');
        }
        return builder.toString();
    }

    @Test
    public void simpleIssue() {
        createIssueHelper("aaa", "bbb");
    }

    @Test
    public void emptySummary() {
        IssueFormPage formPage = root.toIssueForm();
        formPage.createIssue("", "bbb");
        formPage.closeError();
    }

    @Test
    public void emptyDescription() {
        createIssueHelper("aaa", "", "aaa", "No description");
    }

    @Test
    public void whiteSpaceSummary() {
        createIssueHelper(" ", "bbb", "", "bbb");
    }

    @Test
    public void specialSymbols() {
        StringBuilder textBuilder = new StringBuilder();
        for (int i = 32; i < (1 << 8); i++) {
            textBuilder.append((char)i);
        }
        String text = textBuilder.toString();

        try {
            createIssueHelper(text, text);
            fail();
        } catch (AssertionError e) {
            cleanUp();
        }
    }

    @Test
    public void longSummaryAndDescription() {
        createIssueHelper(genRepeat(1000), genRepeat(1000));
    }

    @Test
    public void russianAlphabet() {
        createIssueHelper("Так тоже", "Должно работать");
    }

    @Test
    public void multilineSummary() {
        String summary = "  a\n    a, \n aaaa\n  \n";
        String description = "bbb";
        createIssueHelper(summary, description, StringUtils.normalizeSpace(summary), description);
    }

    @Test
    public void multilineDescription() {
        String summary = "aaa";
        String description = "   bbb\n bbbb  \n   ";
        createIssueHelper(summary, description, summary, description);
    }
    
    @Test
    public void multipleIssues() {
        String summary = "aaa";
        String description = "bbb";
        int issuesN = 5;

        Issue issueAdded = new Issue(summary, description);
        Issue expectedIssue = new Issue(summary, description);

        for (int i = 0; i < issuesN; i++) {
            IssueFormPage formPage = root.toIssueForm();
            formPage.createIssue(issueAdded);
            IssuePage issuePage = root.toIssuePage();
            Issue issue = issuePage.getLastIssue();
            assertEquals(expectedIssue, issue);
            root.switchTo();
        }

        for (int i = 0; i < issuesN; i++) {
            deleteLastIssue();
        }
    }

}
