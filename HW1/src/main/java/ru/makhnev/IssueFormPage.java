package ru.makhnev;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IssueFormPage extends Page {

    public IssueFormPage(@NotNull WebDriver driver, @NotNull String url) {
        super(driver, url);
    }

    private static final String SUMMARY_ID_D = "id_l.D.ni.ei.eit.summary";
    private static final String DESCRIPTION_ID_D = "id_l.D.ni.ei.eit.description";
    private static final String SUBMIT_BUTTON_CLASS = "submit-btn";

    private static final String ERROR_SEVERITY_CSS = "a[title=\"close\"]";


    public void setDescription(@NotNull String description) {
        WebElement textArea = waitElementVisible(DESCRIPTION_ID_D, 10);
        textArea.clear();
        textArea.sendKeys(description);
    }

    public void setSummary(@NotNull String summary) {
        WebElement textArea = waitElementVisible(SUMMARY_ID_D, 10);
        textArea.clear();
        textArea.sendKeys(summary);
    }

    public void submit() {
        waitElementClickable(By.className(SUBMIT_BUTTON_CLASS), 10).click();
    }

    public void createIssue(@NotNull String summary, @NotNull String description) {
        setSummary(summary);
        setDescription(description);
        submit();
    }

    public void createIssue(@NotNull Issue issue) {
        createIssue(issue.getSummary(), issue.getDescription());
    }

    public void closeError() {
        waitElementClickable(By.cssSelector(ERROR_SEVERITY_CSS), 10).click();
    }

}
