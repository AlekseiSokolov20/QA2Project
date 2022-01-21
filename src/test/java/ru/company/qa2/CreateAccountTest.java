package ru.company.qa2;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTest extends TestBase {
    // Предусловие: подтверждение выхода из аккаунта
    @BeforeMethod
    public void ensurePreconditions(){
        if (!isLoginTabPresent()){
            clickOnSignOutButton();
        }
    }

    @Test
    public void registrationPositiveTest(){
        clickOnLoginTab();
        Assert.assertTrue(isLoginRegistrationFormPresent());

        createNewAccount(new User().setEmail("regMe12@gm.com").setPassword( "Abc1234$"));
        Assert.assertTrue(isSignOutTabPresent());
    }

    @Test
    public void registrationNegativeWithoutPasswordTest(){
        clickOnLoginTab();
        Assert.assertTrue(isLoginRegistrationFormPresent());

        createNewAccount(new User().setEmail("regMe12@gm.com"));
        Assert.assertTrue(isAlertPresent());
    }

}


