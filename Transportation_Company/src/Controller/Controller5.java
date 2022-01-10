package Controller;

import com.TransportationCompany.SQL_Connector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller5 extends Controller1 implements Initializable {
    @FXML
    protected Label currdistrict;
    @FXML
    protected Label currcity5;
    @FXML
    protected Label currinfo;
    @FXML
    protected Label destcity5;
    @FXML
    protected Label destdistrict;
    @FXML
    protected Label destinfo;
    @FXML
    protected Label cartype;
    @FXML
    protected Label price;
    @FXML
    protected Label platenumber1;

    static double tripprice;




    public void returnfromstart(ActionEvent actionEvent) throws IOException, SQLException {
        deleteFromBook();
        deleteFromInsert_HasLocation();
        deleteFromTrip();
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/Homepage.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setLabels();
    }

    public void setLabels(){
        double price1 = getPrice(Controller4.nowcurrregion,Controller4.nowdestregion);
        System.out.println(Controller4.nowcurrcity);
        currdistrict.setText(Controller4.nowcurrregion);
        currcity5.setText(Controller4.nowcurrcity);
        destcity5.setText(Controller4.nowdestcity);
        destdistrict.setText(Controller4.nowdestregion);
        cartype.setText(Controller4.vehiclemodel);
        price.setText(""+price1);
        platenumber1.setText(Controller4.platenumber);
        currinfo.setText(Controller4.sourcecity);
        destinfo.setText(Controller4.destinationinf);
    }
    public double getPrice (String srcloc, String destloc) {
        double price = 0;
        if (srcloc.equals(destloc))
            price = 10.0;
        else if((srcloc.equals("Mount Lebanon") && destloc.equals("North")) || (srcloc.equals("North") && destloc.equals("Mount Lebanon")))
            price = 50.0;
        else if((srcloc.equals("Beirut") && destloc.equals("North")) || (srcloc.equals("North") && destloc.equals("Beirut")))
            price = 100.0;
        else if((srcloc.equals("Mount Lebanon") && destloc.equals("Beirut")) || (srcloc.equals("Beirut") && destloc.equals("Mount Lebanon")))
            price = 70.0;
        return price;
    }

    public void InsertPayment() throws SQLException {
         tripprice = getPrice(Controller4.nowcurrregion,Controller4.nowdestregion);
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "INSERT INTO PAYMENT (paymentType,paymentAmount) VALUES (?,?)";
        PreparedStatement sqlstmnt = conn.prepareStatement(stmtn);
        sqlstmnt.setString(1,"Cash");
        sqlstmnt.setDouble(2,tripprice);

        sqlstmnt.executeUpdate();
        dbconnection.closeConnection(conn,sqlstmnt);
    }
    public void UpdateDriverBalance() throws SQLException {
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "UPDATE DRIVER SET balance = balance+" + tripprice + " WHERE driverID = (SELECT driverID FROM VEHICLE WHERE " +
                "vehicleid = "+Controller4.usedvehicleid+")";
        Statement sqlstmtn = conn.createStatement();
        sqlstmtn.execute(stmtn);
        dbconnection.closeConnection(conn,sqlstmtn);
    }

    public void paythetrip() throws SQLException {
        int paymentid = getPaymentID();
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "INSERT INTO PAYS_FOR_TRIP (paymentID,tripID,customerID) VALUES (?,?,?)";
        PreparedStatement sqlstmnt = conn.prepareStatement(stmtn);
        sqlstmnt.setInt(1,paymentid);
        sqlstmnt.setInt(2,Controller4.usedtripid);
        sqlstmnt.setInt(3,nowcustomerid);
        sqlstmnt.executeUpdate();
        dbconnection.closeConnection(conn,sqlstmnt);



    }
    public int getPaymentID() throws SQLException {
        int paymentID = 0;
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmnt = "SELECT paymentID FROM PAYMENT ORDER BY paymentID DESC LIMIT 1";
        Statement sqlstmnt = conn.createStatement();
        ResultSet queryresult = sqlstmnt.executeQuery(stmnt);
        while(queryresult.next()){
            paymentID = queryresult.getInt(1);
        }
        return paymentID;
    }

    public void confirm(ActionEvent actionEvent) throws SQLException, IOException {
        InsertPayment();
        paythetrip();
        UpdateDriverBalance();
        movetoreview(actionEvent);
    }
    public void movetoreview(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/ThankYou.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void deleteFromInsert_HasLocation() throws SQLException {
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmnt = "DELETE FROM HAS_LOC WHERE tripID =" + Controller4.usedtripid;

        Statement sqlstmnt = conn.createStatement();
        sqlstmnt.execute(stmnt);
        dbconnection.closeConnection(conn,sqlstmnt);

    }
    public void deleteFromBook() throws SQLException {
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmnt = "DELETE FROM BOOKS WHERE tripID =" + Controller4.usedtripid;

        Statement sqlstmnt = conn.createStatement();
        sqlstmnt.execute(stmnt);
        dbconnection.closeConnection(conn,sqlstmnt);
    }
    public void deleteFromTrip() throws SQLException {
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmnt = "DELETE FROM TRIP WHERE tripID =" + Controller4.usedtripid;

        Statement sqlstmnt = conn.createStatement();
        sqlstmnt.execute(stmnt);
        dbconnection.closeConnection(conn,sqlstmnt);
    }

}
