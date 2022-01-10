package com.TransportationCompany;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        String driverID = "A12", FirstName = "Antonio",LastName = "Doumit",age="12",gender="Male",phoneNumber="76659399",address="Hboub";
        int year = 2001-1900,day=10,month=1-1;
        double balance =1300;
        Driver driver = new Driver(driverID,FirstName,LastName,age,gender,phoneNumber,address,balance,year,day,month);
        //driver.InsertDriver();
        System.out.println(driver.getStartDate());
        Customer customer = new Customer("Alex","Anlian",18,"Male","alex.anlian@hotmail.com","Alex123","79000333","JBEIL");
        customer.InsertCustomer();
    }

}

