package ru.makhnev;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page {

    private static final String LOGIN_ID = "id_l.L.login";
    private static final String PASSWORD_ID = "id_l.L.password";
    private static final String LOGIN_BUTTON_ID = "id_l.L.loginButton";

    public LoginPage(@NotNull WebDriver driver, @NotNull String url) {
        super(driver, url);
    }

    public void login(String loginText, String passwordText) {
        waitElementVisible(LOGIN_ID, 10).sendKeys(loginText);
        waitElementVisible(PASSWORD_ID, 10).sendKeys(passwordText);
        waitElementClickable(LOGIN_BUTTON_ID, 10).click();
    }
}