package Controller;

import com.TransportationCompany.SQL_Connector;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Controller4 extends Controller1 implements Initializable {
    Date requestdate;
    Date startdate;
    protected static String vehiclemodel;
    protected static String nowcurrcity;
    protected static String nowdestcity;
    protected static String nowcurrregion;
    protected static String nowdestregion;

    protected static int usedvehicleid;
    protected static int usedtripid;
    @FXML
    protected ChoiceBox currregion;
    @FXML
    protected ChoiceBox currcity;
    @FXML
    protected ChoiceBox destregion;
    @FXML
    protected ChoiceBox destcity;
    @FXML
    protected ChoiceBox vehicle;
    @FXML
    protected TextArea destinfo;
    @FXML
    protected TextArea gfinfo;
    protected static String destinationinf;
    protected static String sourcecity;



    protected static long request;
    protected static long time;
    protected static String platenumber;

    protected ObservableList<String> currentcity = FXCollections.observableArrayList();
    protected ObservableList<String> currentregion = FXCollections.observableArrayList();
    protected ObservableList<String> vehiclelist = FXCollections.observableArrayList();





    public void backtoHomepage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/Homepage.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void nextpage(ActionEvent actionEvent) throws IOException, SQLException {
        Createtrip();
        InsertBook();
        InsertHasLocation();
        initializevalues();
        destinationinf = destinfo.getText();
        sourcecity = gfinfo.getText();
        movetoNextpage(actionEvent);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populatedestination(currentcity,currentregion);
            populatevehicle(vehiclelist);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void populatedestination(ObservableList<String> list,ObservableList<String> list2) throws SQLException {
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "SELECT cityName,district FROM LOCATION";
        Statement sqlstmtn = conn.createStatement();
        ResultSet rs = sqlstmtn.executeQuery(stmtn);
        while(rs.next()){
            list.add(rs.getString(1));
            list2.add(rs.getString(2));
        }
        currcity.setItems(list);
        destcity.setItems(list);
        currregion.setItems(list2);
        destregion.setItems(list2);
        //dbconnection.closeConnection(conn,sqlstmtn);



    }
    public void populatevehicle(ObservableList<String> list) throws SQLException {
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "SELECT cartype FROM Vehicle";
        Statement sqlstmtn = conn.createStatement();
        ResultSet rs = sqlstmtn.executeQuery(stmtn);
        while(rs.next()){
            list.add(rs.getString(1));
        }
        vehicle.setItems(list);

    }
    public void movetoNextpage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/confirm.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void Createtrip() throws SQLException {
         request = System.currentTimeMillis();
        requestdate = new Date(request);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         time = requestdate.getTime();
        Timestamp tsr = new Timestamp(time);
        long  start = System.currentTimeMillis()+10;
        startdate =  new Date(start);
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "INSERT INTO trip (requestTime,startTime) VALUES (?,?)";
        PreparedStatement sqlstmnt = conn.prepareStatement(stmtn);
        sqlstmnt.setString(1,formatter.format(requestdate));
        sqlstmnt.setDate(2,startdate);
        sqlstmnt.executeUpdate();
        dbconnection.closeConnection(conn,sqlstmnt);
    }
    public int getTripid(Date requestdate) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(requestdate)+"   " +requestdate.toString()+" "+requestdate.toLocalDate());
        int tripid = 0;
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmnt = "Select tripID FROM TRIP WHERE requestTime ='" + formatter.format(requestdate)+"'";

        Statement sqlstmnt = conn.createStatement();
        ResultSet queryresult = sqlstmnt.executeQuery(stmnt);
        while(queryresult.next()){
            tripid = queryresult.getInt(1);
        }
        usedtripid=tripid;
        System.out.println(tripid);
        return tripid;

    }
    public void InsertBook() throws SQLException {
        int vehicleid = getVehicleid((String) vehicle.getSelectionModel().getSelectedItem());
        Date date1 = new Date(request);
        Timestamp ts1 = new Timestamp(time);
        int trip =  getTripid(date1);
        System.out.println(ts1);
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "INSERT INTO BOOKS (tripID,customerID,vehicleID,bookingDate) VALUES (?,?,?,?)";
        PreparedStatement sqlstmnt = conn.prepareStatement(stmtn);
        sqlstmnt.setInt(1,trip);
        sqlstmnt.setInt(2,nowcustomerid);
        sqlstmnt.setInt(3,vehicleid);
        sqlstmnt.setDate(4,date1);
        sqlstmnt.executeUpdate();
        dbconnection.closeConnection(conn,sqlstmnt);


    }
    public int getVehicleid(String name) throws SQLException {
        int vehicleid = 0;
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmnt = "SELECT vehicleID,licensePlate FROM VEHICLE WHERE carType = '" + name +"'";

        Statement sqlstmnt = conn.createStatement();
        ResultSet queryresult = sqlstmnt.executeQuery(stmnt);
        while(queryresult.next()){
            vehicleid = queryresult.getInt(1);
            platenumber = queryresult.getString(2);
        }
        usedvehicleid=vehicleid;
        System.out.println(vehicleid);
        return vehicleid;
    }
    public void InsertHasLocation() throws SQLException {
        int locationid = getLocationID((String)destcity.getSelectionModel().getSelectedItem());
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "INSERT INTO has_loc (locationID,tripID) VALUES (?,?)";
        PreparedStatement sqlstmnt = conn.prepareStatement(stmtn);
        sqlstmnt.setInt(1,locationid);
        sqlstmnt.setInt(2,usedtripid);

        sqlstmnt.executeUpdate();
        dbconnection.closeConnection(conn,sqlstmnt);
    }
    public int getLocationID(String destcityName) throws SQLException {
        int locationID = 0;
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmnt = "SELECT locationID FROM LOCATION WHERE cityName = '" + destcityName +"'";

        Statement sqlstmnt = conn.createStatement();
        ResultSet queryresult = sqlstmnt.executeQuery(stmnt);
        while(queryresult.next()){
            locationID = queryresult.getInt(1);
        }
        return locationID;

    }
    public void initializevalues(){
        vehiclemodel = (String)vehicle.getSelectionModel().getSelectedItem();
        nowcurrcity = (String)currcity.getSelectionModel().getSelectedItem();
        nowcurrregion = (String)currregion.getSelectionModel().getSelectedItem();
        nowdestcity = (String)destcity.getSelectionModel().getSelectedItem();
        nowdestregion = (String)destregion.getSelectionModel().getSelectedItem();
    }


}
