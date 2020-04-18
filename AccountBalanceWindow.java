package coe528.project;

/**
 *
 * @author Rohan
 */

import java.text.DecimalFormat;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class AccountBalanceWindow {
    public static void display(){
        Stage window = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(15);
        
        BorderPane layout = new BorderPane();
        VBox leftLayout = new VBox(40);
        VBox rightLayout = new VBox(20);
        layout.setPadding(new Insets(10,30,10,10));
        
        Label balanceLabel = new Label("Balance:");
        balanceLabel.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14)); 
        Label mainAccountLabel = new Label("Main Account:");
        mainAccountLabel.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        DecimalFormat df = new DecimalFormat("#.##");
        Label blankSpace = new Label();
        Label balance = new Label("$" + df.format(Customer.getLoginInstance().getBankAccount().getBalance()));
        balance.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14)); 
        Button goBackButton = new Button("Go Back");
        
        leftLayout.getChildren().addAll(goBackButton, mainAccountLabel);
        rightLayout.getChildren().addAll(blankSpace, balanceLabel, balance);
        
        layout.setLeft(leftLayout);
        layout.setRight(rightLayout);
        GridPane.setConstraints(goBackButton, 0, 0);
        GridPane.setConstraints(balanceLabel, 5, 1);
        GridPane.setConstraints(mainAccountLabel, 0, 2);
        GridPane.setConstraints(balance, 5, 2);
        
        grid.getChildren().addAll(balanceLabel, mainAccountLabel, balance, goBackButton);
        grid.setAlignment(Pos.CENTER);
        
        goBackButton.setOnAction(e -> {
            window.close();
            CustomerWindow.display(Customer.getLoginInstance().getName(), Customer.getLoginInstance().getLevelString());
                });
        
        Scene accountbalanceWindow = new Scene(grid, 340, 120);
        
        window.setScene(accountbalanceWindow);
        window.show();
        
        
    }
}
