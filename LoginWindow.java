package coe528.project;

/**
 *
 * @author Rohan
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginWindow{
    
    
    public static void display(){
        Stage window = new Stage();
        window.setTitle("Login");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(-10);
        Label nameLabel = new Label("Username");
        Label passLabel = new Label("Password");
        Label wrongInputLabel = new Label();
        TextField nameInput = new TextField();
        TextField passInput = new TextField();
        nameInput.setPromptText("username");
        passInput.setPromptText("password");
        Button loginButton = new Button("Login");
        ChoiceBox<String> userRole = new ChoiceBox<String>();
        userRole.getItems().addAll("Customer", "Manager");
        userRole.setValue("Customer");
        
        loginButton.setOnAction(e -> {
            String type = userRole.getValue();
            //People p = null;
            if(type.equals("Customer")){
                
                //change later
                //need to find customer with .txt file
                
                People p = new Customer();
                p.setRole(p);
                boolean login = p.login(nameInput, passInput);
                if(login){
                    Customer c = Customer.getLoginInstance();
                   
                    
                    CustomerWindow.display(c.getName(), c.getLevelString());
                    window.close();
                }else{
                    wrongInputLabel.setText("Wrong Input");
                }
                
                
            }else{
                People p = Manager.getInstance();
                p.setRole(p);
                boolean login = p.login(nameInput, passInput);
                if(login){
                    ManagerWindow.display();
                    window.close();
                }else{
                    wrongInputLabel.setText("Wrong Input");
                }
            }
                });
        window.setOnCloseRequest(e -> {
            e.consume();
            boolean answer = ClosingWindow.display("Alert", "Are you sure?");
            if(answer){
                window.close();
        }
        });
        
        GridPane.setConstraints(userRole, 0, 0);
        GridPane.setConstraints(wrongInputLabel, 0, 1);
        GridPane.setConstraints(nameLabel, 0, 2);
        GridPane.setConstraints(passLabel, 0, 3);
        GridPane.setConstraints(nameInput, 1, 2);
        GridPane.setConstraints(passInput, 1, 3);
        GridPane.setConstraints(loginButton, 1, 4);

        grid.getChildren().addAll(userRole, wrongInputLabel, nameLabel, nameInput, passLabel, passInput, loginButton);
        grid.setAlignment(Pos.CENTER);
        Scene loginWindow = new Scene(grid, 270, 170);
        window.setScene(loginWindow);
        window.show();
    }

    
}
