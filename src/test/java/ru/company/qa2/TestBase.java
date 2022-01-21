package ru.company.qa2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.List;

public class TestBase {
    static WebDriver driver;

    @BeforeSuite
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public boolean isHomeComponentPresent(){
        return driver.findElements(By.cssSelector("div:nth-child(2) > div > div")).size() > 0;
    }

    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }

    public boolean isHomeComponentPresent2(){
        try {
            driver.findElement(By.cssSelector("div:nth-child(2) > div > div"));
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    public boolean isElementPresent2(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    @AfterSuite(enabled = true)
    public void tearDown(){
        driver.quit();
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if (text != null){
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        }
    }

    public boolean isLoginTabPresent() {
        return isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public boolean isSignOutTabPresent() {
        return isElementPresent(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public boolean isLoginRegistrationFormPresent() {
        return isElementPresent(By.cssSelector(".login_login__3EHKB"));
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.alertIsPresent());
        if (alert == null){
            return false;
        }
        else {
            driver.switchTo().alert();
            alert.accept();
            return true;
        }
    }

    public void clickWithAction(By save) {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(save);
        action.moveToElement(element).build().perform();
        element.click();
    }

    public void pause(int millis) {
        new WebDriverWait(driver, Duration.ofSeconds(millis));
    }

    public void clickOnSignOutButton() {
        click(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public void clickOnLoginTab() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public void createNewAccount(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());

        type(By.cssSelector("[placeholder='Password']"), user.getPassword());

        click(By.xpath("//button[contains(.,'Registration')]"));
    }

    public void login(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());

        type(By.cssSelector("[placeholder='Password']"), user.getPassword());

        click(By.xpath("//button[contains(.,'Login')]"));
    }

    public void addNewContact(String name, String surName, String phone, String email, String address, String description) {

        click(By.cssSelector("a:nth-child(5)"));

        type(By.cssSelector("[placeholder='Name']"), name);
        type(By.cssSelector("input:nth-child(2)"), surName);
        type(By.cssSelector("input:nth-child(3)"), phone);
        type(By.cssSelector("input:nth-child(4)"), email);
        type(By.cssSelector("input:nth-child(5)"), address);
        type(By.cssSelector("input:nth-child(6)"), description);

        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
    }

    public boolean isContactCreated(String text) {
        List<WebElement> contacts = driver.findElements(By.xpath("//h2"));
        for (WebElement el:contacts){
            if(el.getText().contains(text)){
                return true;
            }
        } return false;
    }

    public void removeContact(){
        if (!isContactListEmpty()){
            driver.findElement(By.cssSelector(".contact-item_card__2SOIM")).click();
            driver.findElement(By.xpath("//button[text()='Remove']")).click();
        }
    }

    public boolean isContactListEmpty() {
        return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }

    public int sizeOfContacts() {
        if (driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() > 0){
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        }
        else return -1;
    }
}


