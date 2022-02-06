package ru.company.qa2.framework;

import org.testng.annotations.DataProvider;
import ru.company.qa2.models.Contact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public Iterator<Object[]> newContact(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Ivan", "Ivanov", "88007775553", "iv@gm.com", "Ivanovo", "description"});
        list.add(new Object[]{"Ivan", "Ivanov", "88004445553", "iv1@gm.com", "Ivanovo", "description"});
        list.add(new Object[]{"Ivan", "Ivanov", "88002225553", "iv78@gm.com", "Ivanovo", "description"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newContactFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Contacts.csv")));

        String line = reader.readLine();

        while (line!=null) {

            String[] split = line.split(",");

            list.add(new Object[]{new Contact().setName(split[0]).setSurName(split[1]).setPhone(split[2])
                    .setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
            line = reader.readLine();
        }

        return list.iterator();
    }
}
