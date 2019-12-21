package ru.makhnev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IssuePage extends Page {

    private static final String SUMMARY_ID = "id_l.I.ic.icr.it.issSum";
    private static final String DESCRIPTION_ID = "id_l.I.ic.icr.d.description";
    private static final String ISSUE_ID_CLASS = "issueIdAnchor";
    private static final String DELETE_ISSUE_ID = "id_l.I.tb.deleteIssueLink";

    public IssuePage(WebDriver driver, String url) {
        super(driver, url);
    }

    public List<WebElement> getIssues() {
        return getElementsList(By.className(ISSUE_ID_CLASS));
    }

    public Issue getLastIssue() {
        WebElement issue = getIssues().get(0);
        waitElementClickable(issue, 10).click();
        String summary = waitElementVisible(SUMMARY_ID, 10).getText();
        String description = waitElementVisible(DESCRIPTION_ID, 10).getText();

        return new Issue(summary, description);
    }

    public void deleteLastIssue() {
        waitElementClickable(DELETE_ISSUE_ID, 10).click();
        driver.switchTo().alert().accept();
    }

}
