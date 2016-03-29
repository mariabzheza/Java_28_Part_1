package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if ( app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("firstname").withLastName("lastname")
                    .withNickName("nickname").withTitle("jobtitle").withCompany("company")
                    .withHomeAddress("homeaddress").withMobile("mobile").withGroup("test1"));
        }
    }
    
    @Test
    public void ContactDeletionTests() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().getContactCount(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        //assertEquals(after.size(), before.size() - 1);

        assertThat(after, CoreMatchers.equalTo(before.withOut(deletedContact)));
    }

}