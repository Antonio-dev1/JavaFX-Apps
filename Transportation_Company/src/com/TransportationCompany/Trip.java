package com.TransportationCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Trip extends SQL_Connector{
    public int tripID;
    public String requestTime;
    public String startTime;
    public String endTime;

    public Trip( String requestTime, String startTime, String endTime) {

        this.requestTime = requestTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getTripID() {
        return tripID;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void insertrip() throws SQLException {
        Connection connection = getDBConnection();
        String stmnt = "INSERT INTO DRIVER(requestTime,startTime,endTime)values(?,?,?)";
        PreparedStatement sqlstmnt = connection.prepareStatement(stmnt);
        sqlstmnt.setString(1, getRequestTime());
        sqlstmnt.setString(2, getStartTime());
        sqlstmnt.setString(3, getEndTime());
        sqlstmnt.executeUpdate();
        closeConnection(connection, sqlstmnt);
    }
}
