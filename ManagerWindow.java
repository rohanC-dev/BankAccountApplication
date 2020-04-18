package coe528.project;


import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 *
 * @author Rohan
 */
public class ManagerWindow {
    
    public static void display(){
        Stage window = new Stage();
        window.setTitle("Banking Application");
        
        GridPane grid = new GridPane();
        
        grid.setVgap(10);
        grid.setHgap(50);
        grid.setPadding(new Insets(30, 30, 30, 30));
        Label name = new Label("ADMIN ACCESS");
        name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24)); 
        name.setPadding(new Insets(0, 0, 0, 190));
        Button addCustomerButton = new Button("ADD CUSTOMER");
        Button removeCustomerButton = new Button("REMOVE CUSTOMER");
        Button logoutButton = new Button("Logout");
        logoutButton.setMinSize(100, 50);
        
        addCustomerButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        removeCustomerButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        
        
        addCustomerButton.setMinSize(400, 250);
        removeCustomerButton.setMinSize(400, 250);
        
        addCustomerButton.setOnAction(e -> RegisterWindow.display());
        
        removeCustomerButton.setOnAction(e -> DeleteWindow.display());
        
        window.setOnCloseRequest(e -> {
            e.consume();
            boolean answer = ClosingWindow.display("Alert", "Are you sure?");
            if(answer){
                window.close();
        }
        });
        
        logoutButton.setOnAction(e -> {
            window.close();
            Manager.getInstance().logout();
                });
        
        
        GridPane.setConstraints(logoutButton, 0, 0);
        GridPane.setConstraints(name, 1, 0);
        GridPane.setConstraints(addCustomerButton, 0, 1);
        GridPane.setConstraints(removeCustomerButton, 1, 1);
        
        grid.getChildren().addAll(logoutButton, name, addCustomerButton, removeCustomerButton);
        grid.setAlignment(Pos.CENTER);
        Scene managerWindow = new Scene(grid, 900, 400);
        window.setScene(managerWindow);
        window.show();

    }
}
