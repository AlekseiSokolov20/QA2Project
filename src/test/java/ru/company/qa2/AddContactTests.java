package ru.company.qa2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if(!isSignOutTabPresent()){
            click(By.xpath("//a[contains(.,'LOGIN')]"));

            type(By.cssSelector("[placeholder='Email']"), "neuer@gm.com");

            type(By.cssSelector("[placeholder='Password']"), "Neuer12345~");

            click(By.xpath("//button[contains(.,'Login')]"));
        }
    }

    @Test
    public void addContactPositiveTest(){
        int i = (int) ((System.currentTimeMillis()) / 1000) % 3600;
        click(By.cssSelector("a:nth-child(5)"));
        pause(1);
        type(By.cssSelector("[placeholder='Name']"),"Aleksei");
        type(By.cssSelector("input:nth-child(2)"),"Sokolov");
        type(By.cssSelector("input:nth-child(3)"),"12345" + i);
        type(By.cssSelector("input:nth-child(4)"),"qwe" + i + "@gm.com");
        type(By.cssSelector("input:nth-child(5)"),"Helsinki");
        type(By.cssSelector("input:nth-child(6)"),"description");

        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
        // click(By.cssSelector(".add_form__2rsm2 button"));
    }

}






