package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String jobTitle;
    private final String company;
    private final String homeAddress;
    private final String mobile;
    private final String workPhone;

    public ContactData(String firstName, String lastName, String nickName, String jobTitle, String company, String homeAddress, String mobile, String workPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.jobTitle = jobTitle;
        this.company = company;
        this.homeAddress = homeAddress;
        this.mobile = mobile;
        this.workPhone = workPhone;
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
}
