package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by maria on 02.03.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        //int before = app.getContactHelper().getContactCount();
        if ( !app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "nickname",
                    "jobtitle", "company", "homeaddress", "mobile", null, "test1"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectToEditDeleteContact(before);
        app.getContactHelper().fillContactForm(new ContactData("firstname11", "lastname11",
                "nickname11", null, "company11", "homeaddress11", "mobile11", "workphone11", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);

    }

}
