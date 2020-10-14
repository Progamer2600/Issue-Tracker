package me.echols.issuetracker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import me.echols.issuetracker.db.DatabaseConnect;
import me.echols.issuetracker.forms.LandingForm;

public class Main extends Application {

    LandingForm landingForm = new LandingForm();
    private static Stage currentStage;
   static DatabaseConnect db = new DatabaseConnect();


    public static void main(String[] args) {
        db.connectdb();
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        currentStage = primaryStage;

        GridPane gridPane = landingForm.createLandingForm();
        Scene scene = new Scene(gridPane, 800, 500);



        primaryStage.setTitle("Issue tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Stage getStage() {
        return currentStage;
    }


}
