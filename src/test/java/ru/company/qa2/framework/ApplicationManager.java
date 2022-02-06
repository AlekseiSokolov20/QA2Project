package ru.company.qa2.framework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.company.qa2.tests.TestBase;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    //static WebDriver driver;

    static EventFiringWebDriver driver;
    String browser;

    UserHelper user;
    ContactHelper contact;
    HomeHelper home;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public static class MyListener extends AbstractWebDriverEventListener {

        Logger logger = LoggerFactory.getLogger(TestBase.class);

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            super.beforeFindBy(by, element, driver);
            logger.info("Start search " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            super.afterFindBy(by, element, driver);
            logger.info(by + " found.");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            super.onException(throwable, driver);
            logger.error(throwable.toString());
        }
    }

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public HomeHelper getHome() {
        return home;
    }

    public void init() {
        if (browser.equals(BrowserType.CHROME)){
            driver = new EventFiringWebDriver (new ChromeDriver());
        }
        else if (browser.equals(BrowserType.FIREFOX)){
            driver = new EventFiringWebDriver (new FirefoxDriver());
        }

        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        user = new UserHelper(driver);
        contact = new ContactHelper(driver);
        home = new HomeHelper(driver);

        driver.register(new MyListener());
    }

    public void stop() {
        driver.quit();
    }

}

