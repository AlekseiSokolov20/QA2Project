package ru.company.qa2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddContactTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if(!isSignOutTabPresent()){
            clickOnLoginTab();

            login(new User().setEmail("regMe12@gm.com").setPassword( "Abc1234$"));
        }
    }

    @Test
    public void addContactPositiveTest() throws InterruptedException {
        int i = (int) ((System.currentTimeMillis()) / 1000) % 3600;
        addNewContact("Aleksei", "Sokolov", "12345" + i, "qwe" + i + "@gm.com", "Helsinki", "description");
        // click(By.cssSelector(".add_form__2rsm2 button"));
        Thread.sleep(1000);
        Assert.assertTrue(isContactCreated("Aleksei"));
    }

}






