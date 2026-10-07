package ddf.test;

import ddf.test.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DartBluPage {

    private final WebDriver driver;
    private final Utils utils;


    // =========================
    // LOCATORS
    // =========================

    //homePage
    private final By title = By.tagName("h1");

    private final By loginButton = By.id("btnLogin");

    private final By signUpButton = By.id("btnSignUp");


    //login
    private final By lastMatchButton = By.id("btnLastMatch");

    private final By monthsButton = By.id("btnMonths");

    private final By historyButton = By.id("btnHistory");

    private final By loginEmail = By.id("loginEmail");

    private final By loginPassword = By.id("loginPassword");

    private final By submitLogin = By.id("loginButton");

    private final By successMessage = By.className("success");

    private final By userGreeting = By.className("user-greeting");

    private final By profileButton = By.id("btnProfile");

    private final By insertButton = By.id("btnInsert");

    private final By logoutButton = By.id("btnLogout");

    //signUp
    private final By signUpSubmitButton = By.id("signUpButton");

    private final By nameInput = By.id("nome");

    private final By nicknameInput = By.id("soprannome");

    private final By signUpEmailInput = By.id("signUpEmail");

    private final By signUpPasswordInput = By.id("password");


    // =========================
    // CONSTRUCTOR
    // =========================

    public DartBluPage(WebDriver driver) {

        this.driver = driver;
        this.utils = new Utils(driver);
    }


    // =========================
    // GETTERS
    // =========================

    public WebElement getTitle() {

        return utils.waitForVisible(title);
    }

    public WebElement getLoginButton() {

        return utils.waitForVisible(loginButton);
    }

    public WebElement getSignUpButton() {

        return utils.waitForVisible(signUpButton);
    }

    public WebElement getLastMatchButton() {

        return utils.waitForVisible(lastMatchButton);
    }

    public WebElement getMonthsButton() {

        return utils.waitForVisible(monthsButton);
    }

    public WebElement getHistoryButton() {

        return utils.waitForVisible(historyButton);
    }

    public WebElement getLoginEmail() {

        return utils.waitForVisible(loginEmail);
    }

    public WebElement getLoginPassword() {

        return utils.waitForVisible(loginPassword);
    }

    public WebElement getSubmitLogin() {

        return utils.waitForVisible(submitLogin);
    }

    public WebElement getSuccessMessage() {

        return utils.waitForVisible(successMessage);
    }

    public WebElement getUserGreeting() {

        return utils.waitForVisible(userGreeting);
    }

    public WebElement getProfileButton() {

        return utils.waitForVisible(profileButton);
    }

    public WebElement getInsertButton() {

        return utils.waitForVisible(insertButton);
    }

    public WebElement getLogoutButton() {

        return utils.waitForVisible(logoutButton);
    }

    public WebElement getSignUpSubmitButton() {
        return utils.waitForVisible(signUpSubmitButton);
    }


    // =========================
    // ACTIONS
    // =========================

    public void clickLogin() {

        utils.click(loginButton);
    }

    public void enterEmail(String email) {

        utils.type(loginEmail, email);
    }

    public void enterPassword(String password) {

        utils.type(loginPassword, password);
    }

    public void submitLogin() {

        utils.click(submitLogin);
    }

    public void logout() {

        utils.click(logoutButton);
    }


    //signUP

    public void clickSignUp() {
        utils.click(signUpButton);
    }

    public void enterName(String name) {
        utils.type(nameInput, name);
    }

    public void enterNickname(String nickname) {
        utils.type(nicknameInput, nickname);
    }

    public void enterSignUpEmail(String email) {
        utils.type(signUpEmailInput, email);
    }

    public void enterSignUpPassword(String password) {
        utils.type(signUpPasswordInput, password);
    }

    public void submitSignUp() {
        utils.click(signUpSubmitButton);
    }
}
