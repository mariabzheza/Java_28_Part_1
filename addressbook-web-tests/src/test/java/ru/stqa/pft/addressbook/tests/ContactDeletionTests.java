package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
    
    @Test
    public void ContactDeletionTests() {
        app.getNavigationHelper().goToHomePage();
        //int before = app.getContactHelper().getContactCount();
        if ( !app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "nickname",
                    "jobtitle", "company", "homeaddress", "mobile", null, "test11"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectToEditDeleteContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }

}