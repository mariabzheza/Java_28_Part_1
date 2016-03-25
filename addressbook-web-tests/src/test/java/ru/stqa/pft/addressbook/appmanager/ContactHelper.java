package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
        type(By.name("home"), contactData.getHomePhone());
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
/*
    public void selectToEditDeleteContact(int index) {
        //wd.findElements(By.name("selected[]")).get(index).click();
        //!!!!!!!!!!!!!!!!!!!!
        click(By.xpath("(//img[@alt='Edit'])[" + (index) + "]"));
    }

    private void selectToEditDeleteContactById(int id) {
        wd.findElement(By.cssSelector(".center>a[href='edit.php?id=" + id + "']>img[title='Edit']")).click();
    }
    */

    public void submitContactModification() {
        click(By.xpath("html/body/div/div[4]/form[1]/input[22]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(int index, ContactData contact) {
        selectToEditDeleteContact(index + 1);
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectToEditDeleteContactById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(int index) {
        selectToEditDeleteContact(index + 1);
        deleteContact();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectToEditDeleteContactById(contact.getId());
        deleteContact();
        contactCache = null;
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

    public List<ContactData> getList() {

        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = getElementsRows();
        for (WebElement element: elements) {
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }

    private List<WebElement> getElementsRows() {
        return wd.findElements(By.xpath("//tr[@name='entry']"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = getElementsRows();
        for (WebElement element: elements) {
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        //selectToEditDeleteContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobile(mobile).withWorkPhone(work);
    }

    public void selectToEditDeleteContact(int index) {
        //wd.findElements(By.name("selected[]")).get(index).click();
        //!!!!!!!!!!!!!!!!!!!!
        click(By.xpath("(//img[@alt='Edit'])[" + (index) + "]"));
    }

    private void selectToEditDeleteContactById(int id) {
        //wd.findElement(By.cssSelector(".center>a[href='edit.php?id=" + id + "']>img[title='Edit']")).click();
        //wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']>img[title='Edit']")).click();
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    // This new added method is the same as my selectToEditDeleteContactById method.
    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

}
