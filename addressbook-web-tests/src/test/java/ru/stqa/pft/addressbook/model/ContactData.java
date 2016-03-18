package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String jobTitle;
    private final String company;
    private final String homeAddress;
    private final String mobile;
    private final String workPhone;
    private String group;

    public ContactData(String firstName, String lastName, String nickName, String jobTitle, String company, String homeAddress, String mobile, String workPhone, String group) {
        this.id = Integer.MAX_VALUE;//щоб контакт при сортуванні був самим останнім. Якщо поставити 0,то буде першим.
        // але це потрібно тільки тоді, коли не будемо порівнювати по Id + тоді ще потрібно буде переробити методи equals та hashCode!!!
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.jobTitle = jobTitle;
        this.company = company;
        this.homeAddress = homeAddress;
        this.mobile = mobile;
        this.workPhone = workPhone;
        this.group = group;
    }


    public ContactData(int id, String firstName, String lastName, String nickName, String jobTitle, String company, String homeAddress, String mobile, String workPhone, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.jobTitle = jobTitle;
        this.company = company;
        this.homeAddress = homeAddress;
        this.mobile = mobile;
        this.workPhone = workPhone;
        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getHomeAddress() {
        return homeAddress;
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
