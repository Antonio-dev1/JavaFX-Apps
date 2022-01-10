package com.TransportationCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Location extends SQL_Connector{
    public int locationId;
    public String cityname;
    public String zipCode;
    public String district;
    public String relativeLocation;
    public String locationArrivalTime;

    public Location(String cityname, String zipCode, String district, String relativeLocation, String locationArrivalTime) {
        this.cityname = cityname;
        this.zipCode = zipCode;
        this.district = district;
        this.relativeLocation = relativeLocation;
        this.locationArrivalTime = locationArrivalTime;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getCityname() {
        return cityname;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getDistrict() {
        return district;
    }

    public String getRelativeLocation() {
        return relativeLocation;
    }

    public String getLocationArrivalTime() {
        return locationArrivalTime;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setRelativeLocation(String relativeLocation) {
        this.relativeLocation = relativeLocation;
    }

    public void setLocationArrivalTime(String locationArrivalTime) {
        this.locationArrivalTime = locationArrivalTime;
    }


    public void InsertLocation() throws SQLException {
        Connection connection = getDBConnection();
        String stmnt = "INSERT INTO DRIVER(cityName,zipCode,district,relativeLocationID,locationArrivalTime) values(?,?,?,?,?)";
        PreparedStatement sqlstmnt = connection.prepareStatement(stmnt);
        sqlstmnt.setString(1, getCityname());
        sqlstmnt.setString(2, getZipCode());
        sqlstmnt.setString(3, getDistrict());
        sqlstmnt.setString(4, getRelativeLocation());
        sqlstmnt.setString(5, getLocationArrivalTime());
        sqlstmnt.executeUpdate();
        closeConnection(connection, sqlstmnt);
    }
}
