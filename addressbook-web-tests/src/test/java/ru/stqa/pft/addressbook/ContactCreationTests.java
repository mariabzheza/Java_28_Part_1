package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("firstname", "lastname", "nickname", "jobtitle", "company", "homeaddress", "mobile", "workphone"));
        submitContactCreation();
        returnToHomePage();
    }

}
