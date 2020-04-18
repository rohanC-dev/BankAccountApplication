package coe528.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Rohan
 */
public class DepositOrWithdrawWindow {
    public static void display(int x){
        Stage window = new Stage();
        BorderPane mainLayout = new BorderPane();
        GridPane topLayout = new GridPane();
        mainLayout.setPadding(new Insets(30,30,30,30));
        VBox leftLayout = new VBox(20);
        VBox rightLayout = new VBox(20);
        VBox centreLayout = new VBox(20);
        VBox labelLayout = new VBox(1000);
        centreLayout.setAlignment(Pos.CENTER);
        topLayout.setPadding(new Insets(0, 0, 30, 0));
        topLayout.setVgap(8);
        topLayout.setHgap(100);
        
        Label exchangeLabel = new Label();
        Button dollar10Button = new Button("$10");
        Button dollar20Button = new Button("$20");
        Button dollar50Button = new Button("$50");
        Button dollar100Button = new Button("$100");
        Button dollar500Button = new Button("$500");
        Button dollar1000Button = new Button("$1000");
        Button goBackButton = new Button("Go Back");
        Label customDollar = new Label("Custom Amount");
        
        Button exchange = null;
        if(x == 1){
            exchange = new Button("Deposit");
            exchangeLabel.setText("Deposit");
            window.setTitle("Deposit Winodw");
        }else if(x == 2){
            exchange = new Button("Withdraw");
            exchangeLabel.setText("Withdraw");
            window.setTitle("Withdraw Window");
        }
        
        dollar10Button.setMinSize(300, 200);
        dollar20Button.setMinSize(300, 200);
        dollar50Button.setMinSize(300, 200);
        dollar100Button.setMinSize(300, 200);
        dollar500Button.setMinSize(300, 200);
        dollar1000Button.setMinSize(300, 200);
        goBackButton.setMinSize(100, 50);
        
        TextField customInput = new TextField();
        customInput.setMaxSize(200, 60);
        
        topLayout.getChildren().add(goBackButton);
        
        exchangeLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        labelLayout.getChildren().addAll(exchangeLabel);
        labelLayout.setAlignment(Pos.CENTER);
        leftLayout.getChildren().addAll(dollar10Button, dollar20Button, dollar50Button);
        rightLayout.getChildren().addAll(dollar100Button, dollar500Button, dollar1000Button);
        centreLayout.getChildren().addAll(labelLayout, customDollar, customInput, exchange);
        
        goBackButton.setOnAction(e -> {
            CustomerWindow.display(Customer.getLoginInstance().getName(), Customer.getLoginInstance().getLevelString());
            window.close();
                });
        
        dollar10Button.setOnAction(e -> {  
            if(x == 1){
                depositOrWithdraw(1,10);
                
            }else if(x == 2){
                depositOrWithdraw(2,10);
            } 
        });
        dollar20Button.setOnAction(e -> {
            if(x == 1){
                depositOrWithdraw(1,20);
                
            }else if(x == 2){
                depositOrWithdraw(2,20);
            }
        });
        dollar50Button.setOnAction(e -> {
            if(x == 1){
                depositOrWithdraw(1,50);
                
            }else if(x == 2){
                depositOrWithdraw(2,50);
            }
        });
        dollar100Button.setOnAction(e -> {
            if(x == 1){
                depositOrWithdraw(1,100);
                
            }else if(x == 2){
                depositOrWithdraw(2,100);
            }
        });
        dollar500Button.setOnAction(e -> {
            if(x == 1){
                depositOrWithdraw(1,500);
                
            }else if(x == 2){
                depositOrWithdraw(2,500);
            }
        });
        dollar1000Button.setOnAction(e -> {
            if(x == 1){
                depositOrWithdraw(1,1000);
                
            }else if(x == 2){
                depositOrWithdraw(2,1000);
            }
        });
        
        exchange.setOnAction(e -> {
            double money = Double.parseDouble(customInput.getText());
            Customer c = Customer.getLoginInstance();
            BankAccount b = c.getBankAccount();
            
            if(x == 1){
                b.deposit(money, c.getUsername());
            }else if(x == 2){
                b.withdraw(money, c.getUsername());
            }
                });
        

        window.setOnCloseRequest(e -> {
            e.consume();
            boolean answer = ClosingWindow.display("Alert", "Are you sure?");
            if(answer){
                window.close();
        }
        });
        mainLayout.setLeft(leftLayout);
        mainLayout.setRight(rightLayout);
        mainLayout.setTop(topLayout);
        mainLayout.setCenter(centreLayout);
        
        Scene depositorwithdrawWindow = new Scene(mainLayout, 1280, 768);
        window.setScene(depositorwithdrawWindow);
        window.show(); 
    }
    
    private static void depositOrWithdraw(int x, double money){
        Customer c = Customer.getLoginInstance();
        BankAccount b = c.getBankAccount();
        if(x == 1){
            b.deposit(money, c.getUsername());
        }else if(x == 2){
            b.withdraw(money, c.getUsername());
        } 
    }
}
