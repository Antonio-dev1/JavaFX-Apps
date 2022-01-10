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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller6 extends Controller1 implements Initializable {
    @FXML
    protected Label firstname;
    @FXML
    protected Label lastname;
    @FXML
    protected Label customerid;
    @FXML
    protected Label age;
    @FXML
    protected Label phonenumber;
    @FXML
    protected Label emailuser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            settinglabels();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void settinglabels() throws SQLException {
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String stmtn = "SELECT * FROM CUSTOMER WHERE customerID = "+nowcustomerid;
        Statement sqlstmtn = conn.createStatement();
        ResultSet rs = sqlstmtn.executeQuery(stmtn);
        while (rs.next()) {
            firstname.setText(rs.getString(2));
            lastname.setText(rs.getString(3));
            customerid.setText(""+rs.getInt(1));
            age.setText(""+rs.getInt(4));
            phonenumber.setText(rs.getString(8));
            emailuser.setText(rs.getString(6));

        }
    }

    public void movetohome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/Homepage.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotochangepassword(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/Passwordchange.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
