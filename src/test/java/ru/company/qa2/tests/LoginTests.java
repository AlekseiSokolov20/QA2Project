package ru.company.qa2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.company.qa2.models.User;

public class LoginTests extends TestBase {

    // Предусловие: подтверждение выхода из аккаунта
    @BeforeMethod
    public void ensurePreconditions(){
        if (!app.getUser().isLoginTabPresent()){
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test(priority = 2)
    public void loginRegisteredUserPositiveTest(){
        app.getUser().clickOnLoginTab();
        Assert.assertTrue(app.getUser().isLoginRegistrationFormPresent());

        app.getUser().login(new User().setEmail("neuer@gm.com").setPassword( "Neuer12345~"));
        Assert.assertTrue(app.getUser().isSignOutTabPresent());
    }

    @Test(priority = 1)
    public void loginRegisteredUserNegativeWithWrongPasswordTest(){
        app.getUser().clickOnLoginTab();
        Assert.assertTrue(app.getUser().isLoginRegistrationFormPresent());

        app.getUser().login(new User().setEmail("neuer@gm.com").setPassword( "qwe"));
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

}






