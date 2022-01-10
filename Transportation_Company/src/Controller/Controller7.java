package Controller;

import com.TransportationCompany.SQL_Connector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller7 extends Controller1{
    @FXML
    PasswordField NewPassword;
    @FXML
    PasswordField ConfirmPassword;

    public void updatepassword(ActionEvent actionEvent) throws SQLException, IOException {
        if(NewPassword.getText().equals(ConfirmPassword.getText())) {
            SQL_Connector dbconnection = new SQL_Connector();
            Connection conn = dbconnection.getDBConnection();
            String stmtn = "UPDATE CUSTOMER SET password = '" + NewPassword.getText() + "' WHERE customerID = " + nowcustomerid;
            Statement sqlstmtn = conn.createStatement();
            sqlstmtn.execute(stmtn);
            movetoSignPage(actionEvent);
        }else
            System.out.println("New passwod not equal to Old password");
    }

    public void movetoSignPage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/LOGIN.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
