package ru.company.qa2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    // Предусловие: подтверждение выхода из аккаунта
    @BeforeMethod
    public void ensurePreconditions(){
        if (!isLoginTabPresent()){
            clickOnSignOutButton();
        }
    }

    @Test(priority = 2)
    public void loginRegisteredUserPositiveTest(){
        clickOnLoginTab();
        Assert.assertTrue(isLoginRegistrationFormPresent());

        login(new User().setEmail("regMe12@gm.com").setPassword( "Abc1234$"));
        Assert.assertTrue(isSignOutTabPresent());
    }

    @Test(priority = 1)
    public void loginRegisteredUserNegativeWithWrongPasswordTest(){
        clickOnLoginTab();
        Assert.assertTrue(isLoginRegistrationFormPresent());

        login(new User().setEmail("regMe12@gm.com").setPassword( "qwe"));
        Assert.assertTrue(isAlertPresent());
    }

}






