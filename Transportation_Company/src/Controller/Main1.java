package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main1 extends Application {
    public Main1() throws IOException {

    }

    private static Stage stage;
    @Override
    public void start(Stage primarystage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFILES/LOGIN.fxml"));
        stage = primarystage;
        primarystage.setTitle("VROOM VROOM");
        primarystage.setScene(new Scene(root, 600 ,500));
        primarystage.setResizable(false);
        stage.getIcons().add(new Image("Pictures/Logo.jpg"));
        primarystage.show();

    }


    public static void main(String[] args){
        launch(args);
    }

}

