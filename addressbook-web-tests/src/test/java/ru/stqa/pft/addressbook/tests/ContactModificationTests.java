package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by maria on 02.03.2016.
 */
public class ContactModificationTests extends TestBase {

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
    public void testContactModification() {
        List<ContactData> before = app.contact().getList();
        int index = before.size()-1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withFirstName("firstname11")
                .withLastName("lastname11").withNickName("nickname11").withCompany("company11")
                .withHomeAddress("homeaddress11").withMobile("mobile11").withWorkPhone("workphone11");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().getList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
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
