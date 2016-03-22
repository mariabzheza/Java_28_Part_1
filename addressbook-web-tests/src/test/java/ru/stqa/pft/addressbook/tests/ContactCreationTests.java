package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all(); //change .getList method to .all
        ContactData contact = new ContactData()
                .withFirstName("firstname").withLastName("lastname").withNickName("nickname")
                .withJobTitle("jobtitle").withCompany("company").withHomeAddress("homeaddress")
                .withMobile("mobile").withGroup("test1");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all(); //change .getList method to .all
        Assert.assertEquals(after.size(), before.size() +1);

        // Comparator
        //contact.withId(after.stream().max((cont1, cont2) -> Integer.compare(cont1.getId(), cont2.getId())).get().getId());
        //easiest comparator was bellow:
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());

        before.add(contact);
        Assert.assertEquals(before,after);
    }

}
