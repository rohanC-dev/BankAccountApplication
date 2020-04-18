package coe528.project;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Rohan
 */
public class RegisterConfirmationWindow {
    
    public static void display(TextField firstNameInput, TextField usernameInput, TextField lastNameInput, TextField passInput){
        Stage window = new Stage();
        window.setTitle("Register");
        GridPane grid = new GridPane();
        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.CENTER);
        
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(8);
        
        Label message = new Label("Successfully registered customer.");
            
        Label nameText = new Label("Name: ");
        Label usernameText = new Label("Username: ");
        Label passText = new Label("Password: ");
            
        Label name = new Label(firstNameInput.getText() + " " + lastNameInput.getText());
        Label username = new Label(usernameInput.getText());
        Label pass = new Label(passInput.getText());
        
        name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12)); 
        username.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12)); 
        pass.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12)); 
        
        Button registerAgainButton = new Button("Register Again");
        
        registerAgainButton.setOnAction(e -> {
            RegisterWindow.display();
            window.close();
                });
        
        GridPane.setConstraints(nameText, 0, 0);
        GridPane.setConstraints(usernameText, 0, 1);
        GridPane.setConstraints(passText, 0, 2);
        GridPane.setConstraints(name, 1, 0);
        GridPane.setConstraints(username, 1, 1);
        GridPane.setConstraints(pass, 1, 2);
        
        grid.getChildren().addAll(nameText, usernameText, passText, name, username, pass);
        grid.setAlignment(Pos.CENTER);
        
        mainLayout.getChildren().addAll(message, grid, registerAgainButton);
        
        Scene registerConformation = new Scene(mainLayout, 300, 200);
        window.setScene(registerConformation);
        window.show();
        
    }
    
}
