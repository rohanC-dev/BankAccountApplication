package coe528.project;


/**
 *
 * @author Rohan
 */

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CustomerWindow {
    
    
    public static void display(String customerName, String levelString){
        Stage window = new Stage();
        window.setTitle("Banking Application");
        
        Label name = new Label(customerName);
        Label status = new Label("Status: " + levelString);
        name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24)); 
        status.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24)); 
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button balanceButton = new Button("Account Balance");
        Button onlinePurchaseButton = new Button("Online Purchase");
        Button logoutButton = new Button("Logout");
        
        depositButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        withdrawButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        balanceButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        onlinePurchaseButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        
        depositButton.setMinSize(400, 250);
        withdrawButton.setMinSize(400, 250);
        balanceButton.setMinSize(400, 250);
        onlinePurchaseButton.setMinSize(400, 250);
        logoutButton.setMinSize(100, 50);
        
        BorderPane topSide = new BorderPane();
        topSide.setPadding(new Insets(0, 0, 30, 0));
        BorderPane layout = new BorderPane();
        VBox leftSide = new VBox(20);
        VBox rightSide = new VBox(20);
        topSide.setRight(name);
        topSide.setLeft(logoutButton);
        topSide.setCenter(status);
        
        
        
        
        leftSide.getChildren().addAll(depositButton, withdrawButton);
        rightSide.getChildren().addAll(balanceButton, onlinePurchaseButton);
        layout.setLeft(leftSide);
        layout.setRight(rightSide);
        layout.setTop(topSide);
        layout.setPadding(new Insets(30, 30, 30, 30));
        
        
        
        
        depositButton.setOnAction(e -> {
            int x = 1;
            DepositOrWithdrawWindow.display(x);
            window.close();
                });
        withdrawButton.setOnAction(e -> {
            int x = 2;
            DepositOrWithdrawWindow.display(x);
            window.close();
                });
        balanceButton.setOnAction(e -> AccountBalanceWindow.display());
        
        onlinePurchaseButton.setOnAction(e -> OnlinePurchaseWindow.display());
        
        //fix this later
        //logoutButton.setOnAction(e -> People.logout());
        
        
        //TEMPORARY
        logoutButton.setOnAction(e -> {
            window.close();
            Customer.getLoginInstance().logout();
                });
        window.setOnCloseRequest(e -> {
            e.consume();
            boolean answer = ClosingWindow.display("Alert", "Are you sure?");
            if(answer){
                window.close();
        }
        });
        
        
        
        
        Scene customerWindow = new Scene(layout, 972, 678);
        window.setScene(customerWindow);
        window.show();
    }
    
    
    
    
}
