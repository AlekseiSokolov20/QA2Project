package ru.company.qa2;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OpenHomePageTest extends TestBase {

    @Test
    public void homePageTest(){
        System.out.println("site opened");
        System.out.println("Home Component:" + isElementPresent2(By.cssSelector("div:nth-child(2) > div > div")));
    }

}
