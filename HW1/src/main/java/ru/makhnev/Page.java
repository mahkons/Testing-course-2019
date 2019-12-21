package ru.makhnev;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Page {

    protected WebDriver driver;
    protected String url;

    public Page(@NotNull WebDriver driver, @NotNull String url) {
        this.driver = driver;
        this.url = url;
    }

    public void switchTo() {
        driver.get(url);
    }

    public WebElement getElement(@NotNull String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement getElement(@NotNull By by) {
        return driver.findElement(by);
    }

    public List<WebElement> getElementsList(@NotNull By by) {
        return driver.findElements(by);
    }

    public WebElement waitElementVisible(@NotNull String id, long timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        return getElement(id);
    }

    public WebElement waitElementClickable(@NotNull String id, long timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(By.id(id)));
        return getElement(id);
    }

    public WebElement waitElementClickable(@NotNull WebElement element, long timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public WebElement waitElementVisible(@NotNull By by, long timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(by));
        return getElement(by);
    }

    public WebElement waitElementClickable(@NotNull By by, long timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(by));
        return getElement(by);
    }
}
