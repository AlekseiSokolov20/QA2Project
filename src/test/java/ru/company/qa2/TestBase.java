package ru.company.qa2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    WebDriver driver;

    @BeforeMethod
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

    @AfterMethod(enabled = false)
    public void tearDown(){
        driver.quit();
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
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

}


