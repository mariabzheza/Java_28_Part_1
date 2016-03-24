package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all(); //change .getList method to .all
        ContactData contact = new ContactData()
                .withFirstName("firstname").withLastName("lastname").withNickName("nickname")
                .withJobTitle("jobtitle").withCompany("company").withHomeAddress("homeaddress")
                .withMobile("mobile").withGroup("test1");
        app.contact().create(contact);
        Contacts after = app.contact().all(); //change .getList method to .all
        assertEquals(after.size(), before.size() +1);

        // Comparator
        //contact.withId(after.stream().max((cont1, cont2) -> Integer.compare(cont1.getId(), cont2.getId())).get().getId());
        //easiest comparator was bellow:
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());

        assertThat(after, equalTo(before.withAdded(contact)));
    }

}
