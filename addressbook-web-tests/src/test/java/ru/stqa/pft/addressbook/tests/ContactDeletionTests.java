package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    
    @Test
    public void ContactDeletionTests() {
        app.getNavigationHelper().goToHomePage();
        if ( !app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "nickname",
                    "jobtitle", "company", "homeaddress", "mobile", null, "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectToEditDeleteContact(before.size());
        app.getContactHelper().deleteContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before,after);
    }

}