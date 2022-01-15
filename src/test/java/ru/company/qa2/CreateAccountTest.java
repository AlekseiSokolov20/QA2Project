package ru.company.qa2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTest extends TestBase {
    // Предусловие: подтверждение выхода из аккаунта
    @BeforeMethod
    public void ensurePreconditions(){
        if (!isLoginTabPresent()){
            click(By.xpath("//button[contains(.,'Sign Out')]"));
        }
    }

    @Test
    public void registrationPositiveTest(){
        click(By.xpath("//a[contains(.,'LOGIN')]"));
        Assert.assertTrue(isLoginRegistrationFormPresent());

        type(By.cssSelector("[placeholder='Email']"), "regMe12@gm.com");

        type(By.cssSelector("[placeholder='Password']"), "Abc1234$");

        click(By.xpath("//button[contains(.,'Registration')]"));
        Assert.assertTrue(isSignOutTabPresent());
    }

}


