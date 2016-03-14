package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        //int before = app.getContactHelper().getContactCount();
        List<ContactData> before = app.getContactHelper().getGroupList();
        app.getContactHelper().createContact(new ContactData("firstname", "lastname", "nickname",
                "jobtitle", "company", "homeaddress", "mobile", null, "test1"));
        //int after = app.getContactHelper().getContactCount();
        List<ContactData> after = app.getContactHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() +1);
    }

}
