package ru.company.qa2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.company.qa2.models.Contact;
import ru.company.qa2.models.User;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if(!app.getUser().isSignOutTabPresent()){
            app.getUser().clickOnLoginTab();

            app.getUser().login(new User().setEmail("neuer@gm.com").setPassword( "Neuer12345~"));
        }
        int i = (int) ((System.currentTimeMillis()) / 1000) % 3600;
        app.getContact().addNewContact(new Contact().setName("Aleksei").setSurName("Sokolov")
                .setPhone("12345" + i).setEmail("qwe" + i + "@gm.com")
                .setAddress("Helsinki").setDescription("description"));

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
    public void RemoveContactTest() throws InterruptedException {
        int sizeBefore = app.getContact().sizeOfContacts();
        app.getContact().removeContact();
        Thread.sleep(2000);
        int sizeAfter = app.getContact().sizeOfContacts();
        Assert.assertEquals(sizeBefore-1, sizeAfter);
    }

}




