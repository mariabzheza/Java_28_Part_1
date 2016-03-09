package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
    
    @Test
    public void ContactDeletionTests() {
        app.getNavigationHelper().goToHomePage();
        if ( !app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "nickname",
                    "jobtitle", "company", "homeaddress", "mobile", null, "test11"));
        }
        app.getContactHelper().selectToEditDeleteContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().returnToHomePage();
    }

}