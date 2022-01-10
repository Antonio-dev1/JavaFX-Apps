package Controller;

import com.TransportationCompany.SQL_Connector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller8 extends Controller1{
    @FXML
    protected RadioButton triprating1;
    @FXML
    protected RadioButton triprating2;
    @FXML
    protected RadioButton triprating3;
    @FXML
    protected RadioButton triprating4;
    @FXML
    protected RadioButton triprating5;
    @FXML
    protected RadioButton driverrating1;
    @FXML
    protected RadioButton driverrating2;
    @FXML
    protected RadioButton driverrating3;
    @FXML
    protected RadioButton driverrating4;
    @FXML
    protected RadioButton driverrating5;
    @FXML
    protected TextArea t1;



    public void submit(ActionEvent actionEvent) throws SQLException, IOException {
        reviewDriver();
        reviewTrip();
        insertservestrip();
        MovetoNextThankYou(actionEvent);
    }

    public void reviewDriver() throws SQLException {
        int driverrating = getDriveRating();
        int driverID = getDriverID();
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "INSERT INTO REVIEWS (customerID,driverID,rating,comment) VALUES (?,?,?,?)";
        PreparedStatement sqlstmnt = conn.prepareStatement(stmtn);
        sqlstmnt.setInt(1,nowcustomerid);
        sqlstmnt.setInt(2,driverID);
        sqlstmnt.setInt(3,driverrating);
        sqlstmnt.setString(4,t1.getText());
        sqlstmnt.executeUpdate();
        dbconnection.closeConnection(conn,sqlstmnt);

    }

    public void reviewTrip() throws SQLException {
        int triprating = getTripRating();
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "INSERT INTO REVIEWSTRIP (tripID,customerID,rating) VALUES (?,?,?)";
        PreparedStatement sqlstmnt = conn.prepareStatement(stmtn);
        sqlstmnt.setInt(1,Controller4.usedtripid);
        sqlstmnt.setInt(2,nowcustomerid);
        sqlstmnt.setInt(3,triprating);
        sqlstmnt.executeUpdate();
        dbconnection.closeConnection(conn,sqlstmnt);
    }
    public int getDriveRating(){
        int rating = 0;
        if(driverrating1.isSelected())
            rating=1;
        else if(driverrating2.isSelected())
                rating=2;
        else if (driverrating3.isSelected())
            rating = 3;
        else if(driverrating4.isSelected())
            rating = 4;
        else if(driverrating5.isSelected())
            rating = 5;
        return rating;
    }
    public int getTripRating(){
        int rating = 0;
        if(triprating1.isSelected())
            rating=1;
        else if(triprating2.isSelected())
            rating=2;
        else if (triprating3.isSelected())
            rating = 3;
        else if(triprating4.isSelected())
            rating = 4;
        else if(triprating5.isSelected())
            rating = 5;
        return rating;
    }
    public int getDriverID() throws SQLException {
        int driverID = 0;
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmnt = "SELECT driverID FROM VEHICLE WHERE cartype = '" + Controller4.vehiclemodel +"'";

        Statement sqlstmnt = conn.createStatement();
        ResultSet queryresult = sqlstmnt.executeQuery(stmnt);
        while(queryresult.next()){
            driverID = queryresult.getInt(1);
        }
        return driverID;
    }
    public void MovetoNextThankYou(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/Homepage.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void insertservestrip() throws SQLException {
        int driverID3 = getDriverID();
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "INSERT INTO SERVESTRIP (tripID,driverID) VALUES (?,?)";
        PreparedStatement sqlstmnt = conn.prepareStatement(stmtn);
        sqlstmnt.setInt(1,Controller4.usedtripid);
        sqlstmnt.setInt(2,driverID3);
        sqlstmnt.executeUpdate();
        dbconnection.closeConnection(conn,sqlstmnt);
    }

    public void sendreview(ActionEvent actionEvent) {
    }
}
