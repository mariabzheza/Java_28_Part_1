package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by maria on 02.03.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectToEditDeleteContact();
        app.getContactHelper().fillContactForm(new ContactData("firstname11", "lastname11", "nickname11", "jobtitle11", "company11", "homeaddress11", "mobile11", "workphone11", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();

    }

}