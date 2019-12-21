package ru.makhnev;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IssueFormPage extends Page {

    public IssueFormPage(@NotNull WebDriver driver, @NotNull String url) {
        super(driver, url);
    }

    private static final String SUMMARY_ID_D = "id_l.D.ni.ei.eit.summary";
    private static final String DESCRIPTION_ID_D = "id_l.D.ni.ei.eit.description";
    private static final String SUBMIT_BUTTON_CLASS = "submit-btn";


    public void setDescription(@NotNull String description) {
        waitElementVisible(DESCRIPTION_ID_D, 10).sendKeys(description);
    }

    public void setSummary(@NotNull String summary) {
        waitElementVisible(SUMMARY_ID_D, 10).sendKeys(summary);
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
}
