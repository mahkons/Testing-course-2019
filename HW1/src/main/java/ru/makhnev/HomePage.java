package ru.makhnev;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

    private static String ISSUE_FORM_CSS = "a[title=\"Create Issue\"]";
    private static String ISSUES_CSS = "a[href=\"/issues\"]";

    public HomePage(@NotNull WebDriver driver, @NotNull String url) {
        super(driver, url);
    }

    public IssueFormPage toIssueForm() {
        IssueFormPage formPage = new IssueFormPage(driver, "http://localhost:8080/dashboard#newissue=yes");
        waitElementClickable(By.cssSelector(ISSUE_FORM_CSS), 10).click();
        return formPage;
    }

    public IssuePage toIssuePage() {
        IssuePage issuePage = new IssuePage(driver, "http://localhost:8080/issues");
        waitElementClickable(By.cssSelector(ISSUES_CSS), 10).click();
        return issuePage;
    }
}
