package ru.stqa.pft.addressbook.model;

import java.io.File;

public class ContactData {
    private int id = Integer.MAX_VALUE;//щоб контакт при сортуванні був самим останнім. Якщо поставити 0,то буде першим.
    // але це потрібно тільки тоді, коли не будемо порівнювати по Id + тоді ще потрібно буде переробити методи equals та hashCode!!!;
    private String firstName;
    private String lastName;
    private String nickName;
    private String title;
    private String company;
    private String homeAddress;
    private String homePhone;
    private String mobile;
    private String workPhone;
    private String group;
    private String allPhones;
    private String email;
    private String email2;
    private String email3;
    private String allMails;
    private String phone2;
    private String fax;
    private String address2;
    private String homePage;
    private String notes;
    private String firstLastName;
    private String allDetails;
    private File photo;


/*    public ContactData(String firstName, String lastName, String nickName, String title, String company, String homeAddress, String mobile, String workPhone, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
        this.homeAddress = homeAddress;
        this.mobile = mobile;
        this.workPhone = workPhone;
        this.group = group;
    }


    public ContactData(int id, String firstName, String lastName, String nickName, String title, String company, String homeAddress, String mobile, String workPhone, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
        this.homeAddress = homeAddress;
        this.mobile = mobile;
        this.workPhone = workPhone;
        this.group = group;
    }
    */

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllMails(String allMails) {
        this.allMails = allMails;
        return this;
    }

    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public ContactData withHomePage(String homePage) {
        this.homePage = homePage;
        return this;
    }

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public ContactData withFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
        return this;
    }

    public ContactData withAllDetails(String allDetails) {
        this.allDetails = allDetails;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getGroup() {
        return group;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllMails() {
        return allMails;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getFax() {
        return fax;
    }

    public String getAddress2() {
        return address2;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getNotes() {
        return notes;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public String getAllDetails() {
        return allDetails;
    }

    public File getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
