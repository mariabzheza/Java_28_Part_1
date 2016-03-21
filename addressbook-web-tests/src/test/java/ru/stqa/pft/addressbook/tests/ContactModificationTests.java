package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by maria on 02.03.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if ( !app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "nickname",
                    "jobtitle", "company", "homeaddress", "mobile", null, "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectToEditDeleteContact(before.size());
        ContactData contact = new ContactData(before.get(before.size()-1).getId(), "firstname11", "lastname11",
                "nickname11", null, "company11", "homeaddress11", "mobile11", "workphone11", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() -1);
        before.add(contact);
        //using new in java 8 to sort before and after lists
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
        //The old version of comparison
        //Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}
