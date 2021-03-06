package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @DataProvider
    // Ітератор масивів обєктів
    public Iterator<Object[]> validContacts() throws IOException {
        File photo = new File("src/test/resources/stru.png");
        List<Object[]> list = new ArrayList<Object[]>();
        /*list.add(new Object[] {new ContactData()
                .withFirstName("firstname").withLastName("lastname")
                .withNickName("nickname").withTitle("jobtitle").withCompany("company")
                .withHomeAddress("homeaddress").withAddress2("address2")
                .withHomePhone("22-33-15").withMobile("+7(34) 55").withWorkPhone("33-33")
                .withPhone2("77-89").withFax("987")
                .withEmail("email@e.com").withEmail2("email2@e.com").withEmail3("email3@e.com")
                .withHomePage("homepage.com").withNotes("notes")
                .withGroup("test1").withPhoto(photo)}); */

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData()
                    .withFirstName(split[0]).withLastName(split[1])
                    .withNickName(split[2]).withTitle(split[3]).withCompany(split[4])
                    .withHomeAddress(split[5]).withAddress2(split[6])
                    .withHomePhone(split[7]).withMobile(split[8]).withWorkPhone(split[9])
                    .withPhone2(split[10]).withFax(split[11])
                    .withEmail(split[12]).withEmail2(split[13]).withEmail3(split[14])
                    .withHomePage(split[15]).withNotes(split[16])
                    .withGroup(split[17]).withPhoto(photo)});
            line= reader.readLine();
        }
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {

        app.goTo().homePage();
        Contacts before = app.contact().all(); //change .getList method to .all
        app.contact().create(contact);
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.contact().all(); //change .getList method to .all
        //assertEquals(after.size(), before.size() +1);

        // Comparator
        //contact.withId(after.stream().max((cont1, cont2) -> Integer.compare(cont1.getId(), cont2.getId())).get().getId());
        //easiest comparator was bellow:
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());

        assertThat(after, equalTo(before.withAdded(contact)));
    }

    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}
