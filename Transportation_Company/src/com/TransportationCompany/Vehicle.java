package com.TransportationCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Vehicle extends SQL_Connector{

public String vehicleID;
public String model;
public String manufacturer;
public String licensePlate;
public String type;
public String startTime;
public String endTime;
public int driverID;
public Driver driver;

    public Vehicle(String model, String manufacturer, String licensePlate, String type, String startTime, String endTime, int driverID) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.driverID = driverID;
    }
    public Vehicle(){}

    public String getVehicleID() {
        return vehicleID;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getType() {
        return type;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public void InsertVehicle() throws SQLException {
        Connection connection = getDBConnection();
        String stmnt = "INSERT INTO DRIVER(model,manufacturer,licenseplate,type,driverId)values(?,?,?,?,?,?,?)";
        PreparedStatement sqlstmnt = connection.prepareStatement(stmnt);
        sqlstmnt.setString(1, getModel());
        sqlstmnt.setString(2, getManufacturer());
        sqlstmnt.setString(3, getLicensePlate());
        sqlstmnt.setString(4, getType());
        sqlstmnt.setInt(5, getDriverID());
        sqlstmnt.executeUpdate();
        closeConnection(connection, sqlstmnt);
    }
}
