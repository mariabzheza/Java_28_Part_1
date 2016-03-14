package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria on 02.03.2016.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean isCreationForm) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getJobTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getHomeAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWorkPhone());

        if (isCreationForm) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void deleteContact() {
        click(By.xpath("html/body/div/div[4]/form[2]/input[2]"));
    }

    public void selectToEditDeleteContact(int index) {
        //wd.findElements(By.name("selected[]")).get(index).click();
        //!!!!!!!!!!!!!!!!!!!!
        click(By.xpath("(//img[@alt='Edit'])[" + (index) + "]"));
    }

    public void submitContactModification() {
        click(By.xpath("html/body/div/div[4]/form[1]/input[22]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(new ContactData("firstname", "lastname", "nickname", "jobtitle", "company", "homeaddress", "mobile", null, "test1"), true);
        submitContactCreation();
        returnToHomePage();

    }
    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getGroupList() {

        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = getElementsRows();
        for (WebElement element: elements) {
            String firstName = wd.findElement(By.xpath(".//td[3]")).getText();
            String lastName = wd.findElement(By.xpath(".//td[2]")).getText();
            ContactData contact = new ContactData(firstName, lastName, null, null, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }

    private List<WebElement> getElementsRows() {
        return wd.findElements(By.xpath("//tr[@name='entry']"));
    }

}
