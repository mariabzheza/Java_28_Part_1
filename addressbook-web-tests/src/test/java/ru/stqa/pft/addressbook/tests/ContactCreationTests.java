package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().getList();
        ContactData contact = new ContactData()
                .withFirstName("firstname").withLastName("lastname").withNickName("nickname")
                .withJobTitle("jobtitle").withCompany("company").withHomeAddress("homeaddress")
                .withMobile("mobile").withGroup("test1");
        app.contact().create(contact);
        List<ContactData> after = app.contact().getList();
        Assert.assertEquals(after.size(), before.size() +1);
        // use Comparator without previous for
        contact.withId(after.stream().max((cont1, cont2) -> Integer.compare(cont1.getId(), cont2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
        //The old version of comparison
        //Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}
