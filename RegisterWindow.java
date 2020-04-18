package coe528.project;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Rohan
 */
public class RegisterWindow {
    public static void display(){
        Stage window = new Stage();
        window.setTitle("Register");
        GridPane grid = new GridPane();
        VBox mainLayout = new VBox(10);
        
        grid.setVgap(8);
        grid.setHgap(8);
        Label usernameExistsLabel = new Label();
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        Label usernameLabel = new Label("Username");
        Label passLabel = new Label("Password");
        
        TextField firstNameInput = new TextField();
        TextField lastNameInput = new TextField();
        TextField usernameInput = new TextField();
        TextField passInput = new TextField();
        firstNameInput.setPromptText("first name");
        lastNameInput.setPromptText("last name");
        usernameInput.setPromptText("username");
        passInput.setPromptText("password");
        Button registerButton = new Button("Register");
        
        GridPane.setConstraints(firstNameLabel, 0, 0);
        GridPane.setConstraints(lastNameLabel, 0, 1);
        GridPane.setConstraints(usernameLabel, 0, 2);
        GridPane.setConstraints(passLabel, 0, 3);
        GridPane.setConstraints(firstNameInput, 1, 0);
        GridPane.setConstraints(lastNameInput, 1, 1);
        GridPane.setConstraints(usernameInput, 1, 2);
        GridPane.setConstraints(passInput, 1, 3);
        GridPane.setConstraints(registerButton, 1, 4);
        
        
        grid.getChildren().addAll(firstNameLabel, lastNameLabel, usernameLabel, passLabel, firstNameInput, lastNameInput, usernameInput, passInput, registerButton);
        
        mainLayout.getChildren().addAll(usernameExistsLabel, grid);
        mainLayout.setPadding(new Insets(10, 10, 10,10));
        grid.setAlignment(Pos.CENTER);
        mainLayout.setAlignment(Pos.CENTER);
        
        Scene registerWindow = new Scene(mainLayout, 300, 200);
        window.setScene(registerWindow);
        
        registerButton.setOnAction(e -> {
            Manager m = Manager.getInstance();
            boolean added = m.addCustomer(firstNameInput.getText(), lastNameInput.getText(), usernameInput.getText(), passInput.getText());
            if(added){
                RegisterConfirmationWindow.display(firstNameInput, usernameInput, lastNameInput, passInput);
                window.close();
            }else{
                usernameExistsLabel.setText("Username already exists!");
            }
            
                });


        window.show();
        

    }
}
