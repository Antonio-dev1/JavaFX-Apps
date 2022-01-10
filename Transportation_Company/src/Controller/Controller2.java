package Controller;

import com.TransportationCompany.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller2  {
    protected Stage stage;
    protected Scene scene;
    @FXML
    protected TextField firstName;
    @FXML
    protected TextField lastName;
    @FXML
    protected TextField email;
    @FXML
    protected TextField age;
    @FXML
    protected TextField password;
    @FXML
    protected TextField confirmpassword;
    @FXML
    protected TextField phonenumber;

    @FXML
    protected RadioButton maleRadio;
    @FXML
    protected RadioButton femaleRadio;
    @FXML
    protected TextArea address;



    public void Signup(ActionEvent actionEvent) throws IOException {
        String gender = "";
        if (femaleRadio.isSelected())
            gender = "Female";
        else
            gender = "Male";
        if(confirmpassword.getText().equals(password.getText())){
            Customer customer = new Customer(firstName.getText(),lastName.getText(),Integer.parseInt(age.getText()),gender,email.getText(),password.getText(),phonenumber.getText(),address.getText());
            try{
                customer.InsertCustomer();
            }catch(Exception e){
                System.out.println("Insert Query was not Executed");
            }
            MoveScenes(actionEvent);
        }

    }

    public void MoveScenes(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/LOGIN.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
