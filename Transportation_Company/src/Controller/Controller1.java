package Controller;

import com.TransportationCompany.SQL_Connector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller1 {
    protected Stage stage;
    protected Scene scene;
    protected static int nowcustomerid;
    @FXML
    protected TextField user;
    @FXML
    protected PasswordField password;
    @FXML
    protected Button loginbutton;

    public void login(ActionEvent actionEvent) throws IOException, SQLException {
        if (user.getText().isBlank() == false && password.getText().isBlank() == false){
            validatelogin(actionEvent);
        }

    }

    public void Signup(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/Signinn.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void validatelogin(ActionEvent actionEvent) throws SQLException, IOException {
        SQL_Connector dbconnection = new SQL_Connector();
        Connection conn = dbconnection.getDBConnection();
        String verifylogin = "Select customerID, COUNT(*) FROM CUSTOMER WHERE email = '" + user.getText() +"' AND password = '"+password.getText()+"'";

        Statement sqlstmnt = conn.createStatement();
        ResultSet queryresult = sqlstmnt.executeQuery(verifylogin);
        try {
            while (queryresult.next()) {
                if (queryresult.getInt(2) == 1) {
                    Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/Homepage.fxml"));
                    stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    nowcustomerid = queryresult.getInt(1);
                    System.out.println(nowcustomerid);            //track customer id

                } else {
                    System.out.println("Wrong Username or Password");
                }


            }
        }catch(Exception e){
                System.out.println("SQL did not work");
                e.printStackTrace();

        }






    }

}
