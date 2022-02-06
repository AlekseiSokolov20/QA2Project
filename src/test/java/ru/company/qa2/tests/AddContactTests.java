package ru.company.qa2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.company.qa2.framework.DataProviders;
import ru.company.qa2.models.Contact;
import ru.company.qa2.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if(!app.getUser().isSignOutTabPresent()){
            app.getUser().clickOnLoginTab();

            app.getUser().login(new User().setEmail("neuer@gm.com").setPassword( "Neuer12345~"));
        }
    }

    @Test
    public void addContactPositiveTest() throws InterruptedException {
        int i = (int) ((System.currentTimeMillis()) / 1000) % 3600;
        app.getContact().addNewContact(new Contact().setName("Aleksei").setSurName("Sokolov")
                .setPhone("12345" + i).setEmail("qwe" + i + "@gm.com")
                .setAddress("Helsinki").setDescription("description"));
        // click(By.cssSelector(".add_form__2rsm2 button"));
        Thread.sleep(1000);
        Assert.assertTrue(app.getContact().isContactCreated("Aleksei"));
    }

    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveFromDataProviderTest(String name, String surname, String phone, String email, String address, String description) throws InterruptedException {

        app.getContact().addNewContact(new Contact().setName(name).setSurName(surname)
                .setPhone(phone).setEmail(email)
                .setAddress(address).setDescription(description));
        app.getContact().removeContact();
        Thread.sleep(1000);
    }

    @Test(dataProvider = "newContactFromCSV", dataProviderClass = DataProviders.class)
    public void addContactPositiveFromCSVTest(Contact contact) throws InterruptedException {

        app.getContact().addNewContact(contact);
        app.getContact().removeContact();
        Thread.sleep(1000);
    }
}






