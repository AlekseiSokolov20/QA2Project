package ru.company.qa2.framework;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HelperBase {
    static WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }

    public boolean isElementPresent2(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
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

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, 20).until(ExpectedConditions.alertIsPresent());
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
        new WebDriverWait(driver, millis);
    }

    public String takeScreenshot() {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshot/screen-" + System.currentTimeMillis() + ".png");

    try {
        Files.copy(tmp, screenshot);
    }
    catch (IOException e){
        e.printStackTrace();
    }
    return screenshot.getAbsolutePath();
    }

}





