package com.TransportationCompany;

import java.sql.*;
import java.sql.Date;


public class Driver extends SQL_Connector {
    public String driverID;
    public String firstName;
    public String lastName;
    public String age;
    public String gender;
    public Date datetime;
    public int year;
    public int month;
    public int day;
    public String phoneNumber;
    public String address;
    public double balance;

    public Driver(String driverID, String firstName, String lastName, String age, String gender, String phoneNumber, String address, double balance, int year, int day, int month) {
        this.year = year;
        this.day = day;
        this.month = month;
        datetime = new Date(year, month, day);
        this.driverID = driverID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.balance = balance;
    }

    public Driver() {
    }

    public String getDriverID() {
        return driverID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Date getStartDate() {
        return datetime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void InsertDriver() throws SQLException {
        Connection connection = getDBConnection();
        String stmnt = "INSERT INTO DRIVER(firstName,lastName,age,gender,startDate,phoneNumber,address,balance) values(?,?,?,?,?,?,?,?)";
        PreparedStatement sqlstmnt = connection.prepareStatement(stmnt);
        sqlstmnt.setString(1, getFirstName());
        sqlstmnt.setString(2, getLastName());
        sqlstmnt.setString(3, getAge());
        sqlstmnt.setString(4, getGender());
        sqlstmnt.setDate(5, datetime);
        sqlstmnt.setString(6, getPhoneNumber());
        sqlstmnt.setString(7, getAddress());
        sqlstmnt.setDouble(8, getBalance());
        sqlstmnt.executeUpdate();
        closeConnection(connection, sqlstmnt);


    }


}
