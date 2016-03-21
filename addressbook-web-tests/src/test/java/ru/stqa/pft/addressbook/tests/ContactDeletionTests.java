package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if ( app.contact().getList().size() == 0) {
            app.contact().create(new ContactData().withFirstName("firstname").withLastName("lastname")
                    .withNickName("nickname").withJobTitle("jobtitle").withCompany("company")
                    .withHomeAddress("homeaddress").withMobile("mobile").withGroup("test1"));
        }
    }
    
    @Test
    public void ContactDeletionTests() {
        List<ContactData> before = app.contact().getList();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().getList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before,after);
    }

}