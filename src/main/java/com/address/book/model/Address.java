package com.address.book.model;

import org.joda.time.DateTime;

public class Address {

    private String firstName;
    private String lastName;
    private Gender gender;
    private DateTime birthday;

    public Address() {

    }

    public Address(String firstName, String lastName, Gender gender, DateTime birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    /*public void setGender(Gender gender) {
        this.gender = gender;
    }*/

    public DateTime getBirthday() {
        return birthday;
    }

/*
    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
    }
*/

    @Override
    public String toString() {
        return "Address{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }


}
