package com.TransportationCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Shift extends SQL_Connector{
    public int shiftID;
    public String loginTime;
    public String logoutTime;
    public String shiftStartTime;
    public String shiftEndTime;

    public Shift(String loginTime, String logoutTime, String shiftStartTime, String shiftEndTime) {
        this.shiftID = shiftID;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
    }
    public Shift(){}

    public int getShiftID() {
        return shiftID;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public String getShiftStartTime() {
        return shiftStartTime;
    }

    public String getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public void setShiftStartTime(String shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public void setShiftEndTime(String shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    public void INSERTSHIFT () throws SQLException {
        Connection connection = getDBConnection();
        String stmnt = "INSERT INTO DRIVER(loginTime,logoutTime,shiftStartTime,shiftEndTime)values(?,?,?,?)";
        PreparedStatement sqlstmnt = connection.prepareStatement(stmnt);
        sqlstmnt.setString(1, getLoginTime());
        sqlstmnt.setString(2, getLogoutTime());
        sqlstmnt.setString(3, getShiftStartTime());
        sqlstmnt.setString(4, getShiftEndTime());
        sqlstmnt.executeUpdate();
        closeConnection(connection, sqlstmnt);
    }
}
