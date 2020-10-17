package me.echols.issuetracker.forms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;
import me.echols.issuetracker.Main;
import me.echols.issuetracker.db.JdbcDao;

import java.sql.SQLException;

public class Registration {
  public static GridPane registerPane = new GridPane();
  private static final LoginForm login = new LoginForm();

    public static GridPane createRegistrationForm() {

        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200,Double.MAX_VALUE);

        //Set the formatting
        registerPane.setAlignment(Pos.CENTER);
        registerPane.setPadding(new Insets(40,40,40,40));
        registerPane.setHgap(10);
        registerPane.setVgap(10);

        columnOneConstraints.setHgrow(Priority.ALWAYS);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        registerPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
        addUIControls(registerPane);
        return registerPane;
    }

    private static void addUIControls(GridPane gridPane) {

        //Create the header
        Label headerLabel = new Label("Registration");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        //Add Name Label
        Label firstNameLabel = new Label("First name: ");
        Label lastNameLabel = new Label("Last Name: ");
        gridPane.add(firstNameLabel, 0,1);
        gridPane.add(lastNameLabel,0,2);

        //Add text field
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        firstNameField.setPrefHeight(40);
        lastNameField.setPrefHeight(40);
        gridPane.add(firstNameField, 1,1);
        gridPane.add(lastNameField, 1,2);

        // Add Email Label
        Label emailLabel = new Label("Email : ");
        gridPane.add(emailLabel, 0, 3);

        // Add Email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 3);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
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
            public void handle(ActionEvent event) {
                if(firstNameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                    return;
                }
                if(lastNameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                    return;
                }
                if(emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your email id");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }
                if (!emailField.getText().contains("@")) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a valid email!");
                    return;
                }


                JdbcDao jdbcDao = new JdbcDao();
                try {
                    jdbcDao.insertRecord(firstNameField.getText(), lastNameField.getText(), emailField.getText(), passwordField.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!",
                        "Welcome " + firstNameField.getText() + " " + lastNameField.getText());
            }
        });
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
