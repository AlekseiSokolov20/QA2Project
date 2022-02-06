package ru.company.qa2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.company.qa2.models.User;

public class CreateAccountTest extends TestBase {
    // Предусловие: подтверждение выхода из аккаунта
    @BeforeMethod
    public void ensurePreconditions(){
        if (!app.getUser().isLoginTabPresent()){
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test //(enabled = false)
    public void registrationPositiveTest() throws InterruptedException {
        app.getUser().clickOnLoginTab();
        Assert.assertTrue(app.getUser().isLoginRegistrationFormPresent());

        app.getUser().createNewAccount(new User().setEmail("neuer@gm.com").setPassword( "Neuer12345~"));
        Thread.sleep(2000);
        Assert.assertTrue(app.getUser().isSignOutTabPresent());
    }

    @Test
    public void registrationNegativeWithoutPasswordTest(){
        app.getUser().clickOnLoginTab();
        Assert.assertTrue(app.getUser().isLoginRegistrationFormPresent());

        app.getUser().createNewAccount(new User().setEmail("neuer@gm.com"));
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

}


