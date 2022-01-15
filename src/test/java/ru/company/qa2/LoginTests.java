package ru.company.qa2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends TestBase {

    // Предусловие: подтверждение выхода из аккаунта
    @BeforeMethod
    public void ensurePreconditions(){
        if (!isLoginTabPresent()){
            click(By.xpath("//button[contains(.,'Sign Out')]"));
        }
    }

    @Test
    public void loginRegisteredUserPositiveTest(){
        click(By.xpath("//a[contains(.,'LOGIN')]"));
        Assert.assertTrue(isLoginRegistrationFormPresent());

        type(By.cssSelector("[placeholder='Email']"), "regMe12@gm.com");

        type(By.cssSelector("[placeholder='Password']"), "Abc1234$");

        click(By.xpath("//button[contains(.,'Login')]"));
        Assert.assertTrue(isSignOutTabPresent());
    }

    @Test
    public void loginRegisteredUserNegativeWithWrongPasswordTest(){
        click(By.xpath("//a[contains(.,'LOGIN')]"));
        Assert.assertTrue(isLoginRegistrationFormPresent());

        type(By.cssSelector("[placeholder='Email']"), "regMe12@gm.com");

        type(By.cssSelector("[placeholder='Password']"), "qwe");

        click(By.xpath("//button[contains(.,'Login')]"));
        Assert.assertTrue(isAlertPresent());
    }

}






