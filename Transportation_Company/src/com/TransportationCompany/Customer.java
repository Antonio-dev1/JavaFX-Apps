package com.TransportationCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer extends SQL_Connector {
    public String firstName;
    public String lastName;
    public int age;
    public String gender;
    public String email;
    public String password;
    public String phoneNumber;
    public String address;

    public Customer( String firstName, String lastName, int age, String gender, String email, String password, String phoneNumber,String address) {
        this.address=address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void InsertCustomer() throws SQLException {
        Connection connection = getDBConnection();
        String stmnt = "INSERT INTO CUSTOMER (firstName,lastName,age,gender,email,password,phoneNumber,address) VALUES(?,?,?,?,?,?,?,?) ";
        PreparedStatement sqlstmnt = connection.prepareStatement(stmnt);
        sqlstmnt.setString(1, getFirstName());
        sqlstmnt.setString(2, getLastName());
        sqlstmnt.setInt(3, getAge());
        sqlstmnt.setString(4, getGender());
        sqlstmnt.setString(5, getEmail());
        sqlstmnt.setString(6, getPassword());
        sqlstmnt.setString(7, getPhoneNumber());
        sqlstmnt.setString(8, getAddress());
        sqlstmnt.executeUpdate();
        closeConnection(connection,sqlstmnt);
    }
}
