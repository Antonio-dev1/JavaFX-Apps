package com.TransportationCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Shift_Instance extends SQL_Connector{
    public String shiftInstanceID;
    public String startTime;
    public String endTime;
    public String dayorTime;
    public String drivername;
    public int shiftID;
    public Shift shift;

    public Shift_Instance(String startTime, String endTime, String dayorTime, String drivername, int shiftID) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayorTime = dayorTime;
        this.drivername = drivername;
        this.shiftID = shiftID;
    }
    public Shift_Instance(){}

    public String getShiftInstanceID() {
        return shiftInstanceID;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDayorTime() {
        return dayorTime;
    }

    public String getDrivername() {
        return drivername;
    }

    public int getShiftID() {
        return shiftID;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShiftInstanceID(String shiftInstanceID) {
        this.shiftInstanceID = shiftInstanceID;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDayorTime(String dayorTime) {
        this.dayorTime = dayorTime;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public void InsertSHIFT_INSTANCE() throws SQLException {
        Connection connection = getDBConnection();
        String stmnt = "INSERT INTO DRIVER(startTime,endTime,dayortime,shiftID)values(?,?,?,?)";
        PreparedStatement sqlstmnt = connection.prepareStatement(stmnt);
        sqlstmnt.setString(1, getStartTime());
        sqlstmnt.setString(2, getEndTime());
        sqlstmnt.setString(3, getDayorTime());
        sqlstmnt.setInt(4, getShiftID());
        sqlstmnt.executeUpdate();
        closeConnection(connection, sqlstmnt);
    }
}
