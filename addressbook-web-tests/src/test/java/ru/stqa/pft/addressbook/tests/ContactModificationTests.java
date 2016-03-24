package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by maria on 02.03.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if ( app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("firstname").withLastName("lastname")
                    .withNickName("nickname").withJobTitle("jobtitle").withCompany("company")
                    .withHomeAddress("homeaddress").withMobile("mobile").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("firstname11")
                .withLastName("lastname11").withNickName("nickname11").withCompany("company11")
                .withHomeAddress("homeaddress11").withMobile("mobile11").withWorkPhone("workphone11");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }

}
