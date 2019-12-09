package model;

public class Passenger {

    private String name;
    private char gender;
    private int age;
    private String bloodGroup;
    private String mobileNumber;
    private String emailAddress;

    public Passenger() {
        // default constructor
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Passenger {" + " name='" + getName() + "'" + ", gender='" + getGender() + "'" + ", age='" + getAge()
                + "'" + ", bloodGroup='" + getBloodGroup() + "'" + ", mobileNumber='" + getMobileNumber() + "'"
                + ", emailAddress='" + getEmailAddress() + "'" + "}";
    }

}