package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    
    @Test
    public void ContactDeletionTests() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectToEditDeleteContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomePage();
    }

}