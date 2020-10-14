package me.echols.issuetracker.forms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import me.echols.issuetracker.Main;

public class LandingForm {

    Button loginButton = new Button("Login");
    Button registerButton = new Button("Register");
    GridPane gridPane = new GridPane();
    Registration registration = new Registration();
    LoginForm login = new LoginForm();


    public GridPane createLandingForm() {
        RowConstraints rowConstraints = new RowConstraints(100,100,Double.MAX_VALUE);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40,40,40,40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        addUIControls(gridPane);
        return gridPane;
    }

    public void addUIControls(GridPane gridPane) {
        //Welcome Label
        Label label = new Label("Issue Tracker");
        label.setFont(Font.font("Arial", FontWeight.BOLD,40));

        gridPane.add(label,0,1,2,2 );

        //Login Button
        loginButton.setPrefSize(150,50);
        loginButton.setPadding(new Insets(0,50,0,50));
        loginButton.setDefaultButton(true);
        gridPane.add(loginButton,0,3,1,1);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene scene = new Scene(login.loginPane, 800, 500);
                login.createLoginForm();

                Main.getStage().setScene(scene);
                Main.getStage().show();
            }
        });
        //Register Button
        registerButton.setPrefSize(150, 50);
        registerButton.setPadding(new Insets(0,50,0,50));
        registerButton.setDefaultButton(true);
        gridPane.add(registerButton,1,3,1,1);
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene scene = new Scene(registration.registerPane, 800, 500);
                registration.createRegistrationForm();

                Main.getStage().setScene(scene);
                Main.getStage().show();
            }
        });
    }
}
