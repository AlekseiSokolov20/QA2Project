package ru.company.qa2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if(!isSignOutTabPresent()){
            clickOnLoginTab();

            login(new User().setEmail("regMe12@gm.com").setPassword( "Abc1234$"));
        }
        int i = (int) ((System.currentTimeMillis()) / 1000) % 3600;
        addNewContact("Aleksei", "Sokolov", "12345" + i, "qwe" + i + "@gm.com", "Helsinki", "description");

       /* click(By.cssSelector("a:nth-child(5)"));
        pause(1);
        type(By.cssSelector("[placeholder='Name']"),"Aleksei");
        type(By.cssSelector("input:nth-child(2)"),"Sokolov");
        type(By.cssSelector("input:nth-child(3)"),"12345" + i);
        type(By.cssSelector("input:nth-child(4)"),"qwe" + i + "@gm.com");
        type(By.cssSelector("input:nth-child(5)"),"Helsinki");
        type(By.cssSelector("input:nth-child(6)"),"description");

        clickWithAction(By.cssSelector(".add_form__2rsm2 button")); */
    }

    @Test
    public void RemoveContactTest(){
        int sizeBefore = sizeOfContacts();
        removeContact();
        int sizeAfter = sizeOfContacts();
        Assert.assertEquals(sizeBefore, sizeAfter);
    }

}




