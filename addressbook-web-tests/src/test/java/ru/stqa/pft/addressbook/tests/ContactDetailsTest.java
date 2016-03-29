package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by maria on 28.03.2016.
 */
public class ContactDetailsTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if ( app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("firstname44").withLastName("lastname44")
                    .withNickName("nickname44").withTitle("jobtitle44").withCompany("company44")
                    .withHomeAddress("homeaddress44").withAddress2("address44_2")
                    .withHomePhone("22-33-1544").withMobile("+7(34) 55 44").withWorkPhone("33-33 -44")
                    .withPhone2("77-89-44").withFax("987 44")
                    .withHomePage("homepage.com444").withNotes("notes444")
                    .withEmail("email@e.com444").withEmail2("email2@e.com444").withEmail3("email3@e.com444")
                    .withGroup("test1"));
        }
    }

    @Test(enabled = false)
    public void ContactDetailsComparisonTest() {

        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contactInfoFromDetailsForm, equalTo(contactInfoFromEditForm));

    }

}
