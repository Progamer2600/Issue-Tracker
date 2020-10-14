package me.echols.issuetracker.forms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;

public class LoginForm {

   public static GridPane loginPane = new GridPane();

    public  GridPane createLoginForm() {

        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200,Double.MAX_VALUE);

        //Set the formatting
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setPadding(new Insets(40,40,40,40));
        loginPane.setHgap(10);
        loginPane.setVgap(10);

        columnOneConstraints.setHgrow(Priority.ALWAYS);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        loginPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
        addUIControls(loginPane);
        return loginPane;
    }
    private  void addUIControls(GridPane gridPane) {

        Label headerLabel = new Label("Login");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        //Email Label
        Label emailLabel = new Label("Email : ");
        emailLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        gridPane.add(emailLabel, 0, 3);

        //Email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 3);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        passwordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        gridPane.add(passwordLabel, 0, 4);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 4);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
        submitButton.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent actionEvent) {
                if (emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your email id");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }

            }
        });
    }
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}