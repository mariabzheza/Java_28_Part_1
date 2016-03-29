package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by maria on 02.03.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if ( app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("firstname111").withLastName("lastname111")
                    .withNickName("nickname111").withTitle("jobtitle111").withCompany("company111")
                    .withHomeAddress("homeaddress111").withAddress2("address112")
                    .withHomePhone("22-33-1511").withMobile("+7(34) 55 11").withWorkPhone("33-33 -11")
                    .withPhone2("77-89-11").withFax("987 11")
                    .withHomePage("homepage.com111").withNotes("notes111")
                    .withEmail("email@e.com111").withEmail2("email2@e.com111").withEmail3("email3@e.com111")
                    .withGroup("test1"));
                    /*.withFirstName("firstname").withLastName("lastname")
                    .withNickName("nickname").withTitle("jobtitle").withCompany("company")
                    .withHomeAddress("homeaddress").withMobile("mobile").withGroup("test1"));*/
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("firstname22").withLastName("lastname22")
                .withNickName("nickname22").withTitle("jobtitle22").withCompany("company22")
                .withHomeAddress("homeaddress22").withAddress2("address2_22")
                .withHomePhone("22-33-1522").withMobile("+7(34) 55 22").withWorkPhone("33-33 -22")
                .withPhone2("77-89-22").withFax("987 22")
                .withHomePage("homepage.com22").withNotes("notes22")
                .withEmail("email@e.com22").withEmail2("email2@e.com22").withEmail3("email3@e.com22")
                .withGroup("test1");
                /*.withId(modifiedContact.getId()).withFirstName("firstname11")
                .withLastName("lastname11").withNickName("nickname11").withCompany("company11")
                .withHomeAddress("homeaddress11").withMobile("mobile11").withWorkPhone("workphone11");*/
        app.contact().modify(contact);
        assertThat(app.contact().getContactCount(), equalTo(before.size()));
        Contacts after = app.contact().all();
        //assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }

}
