package ru.stqa.pft.addressbook.appmanager;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getHomeAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("fax"), contactData.getFax());
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("homepage"), contactData.getHomePage());
        type(By.name("notes"), contactData.getNotes());
        attach(By.name("photo"), contactData.getPhoto());

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
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            //cut string on 3 with diff phone strings.
            /*String[] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withHomePhone(phones[0]).withMobile(phones[1]).withWorkPhone(phones[2])); */
            String allphones = element.findElement(By.xpath(".//td[6]")).getText();
            String allmails = element.findElement(By.xpath(".//td[5]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allphones).withAllMails(allmails).withHomeAddress(address));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        //This method is from Lesson 5.
        //initContactModificationById(contact.getId());
        //The next is my method -->
        selectToEditDeleteContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
        String title = wd.findElement(By.name("title")).getAttribute("value");
        String company = wd.findElement(By.name("company")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String fax = wd.findElement(By.name("fax")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String homepage = wd.findElement(By.name("homepage")).getAttribute("value");
        String address2 = wd.findElement(By.name("address2")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String notes = wd.findElement(By.name("notes")).getAttribute("value");

        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstname).withLastName(lastname).withNickName(nickname)
                .withCompany(company).withTitle(title).withHomePage(homepage)
                .withHomePhone(home).withMobile(mobile).withWorkPhone(work).withFax(fax).withPhone2(phone2)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withHomeAddress(address).withAddress2(address2).withNotes(notes);
    }

/*    public ContactData infoFromDetailsForm(ContactData contact) {
        selectToViewContactDetailsById(contact.getId());
        String allDetails = wd.findElement(By.xpath("//div[@id='content']")).getText();
        //System.out.println(allDetails);

        wd.navigate().back();
        return new ContactData()
                .withAllDetails(allDetails);
    } */

    public String infoFromDetailsForm(ContactData contact) {
        selectToViewContactDetailsById(contact.getId());
        String allDetails = wd.findElement(By.xpath("//div[@id='content']")).getText();
        //System.out.println(allDetails);

        wd.navigate().back();
        return allDetails;
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

    private void selectToViewContactDetailsById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }

    // This new added method is the same as my selectToEditDeleteContactById method.
    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

}
