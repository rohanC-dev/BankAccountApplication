package coe528.project;

/**
 *
 * @author Rohan
 */

import java.io.File;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*; 

public class DeleteWindow {
    public static void display(){
        Stage window = new Stage();
        window.setTitle("Remove Customer");
        
        VBox mainLayout = new VBox();
        
        BorderPane bottomLayout = new BorderPane();
        
        bottomLayout.setPadding(new Insets(10, 10, 10, 10));
        
        TableView<Customer> table = new TableView<>();
        
        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Customer, String> usernameColumn = new TableColumn<>("Userame");
        usernameColumn.setMinWidth(100);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        
        TableColumn<Customer, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setMinWidth(100);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        ArrayList<Customer> listOfCustomers = Manager.getInstance().getCustomers();
        for(int i = 0; i < listOfCustomers.size(); i++){
            customers.add(listOfCustomers.get(i));
        }
        
        table.setItems(customers);
        table.getColumns().addAll(nameColumn, usernameColumn, passwordColumn);
        
        Button remove = new Button("REMOVE");
        remove.setMinSize(100, 50);
       
        remove.setOnAction(e -> {
            ObservableList<Customer> customerSelected = table.getSelectionModel().getSelectedItems();
            Manager.getInstance().deleteCustomer(customerSelected.get(0));
            File file = new File(customerSelected.get(0).getUsername() + ".txt");
            file = new File(file.getAbsolutePath());
            if(file.delete()){
                System.out.println("Deleted");
            }else{
                System.out.println("Not Deleted");
            }
            customerSelected.forEach(customers::remove);
            
            File listOfUsernames = new File("listOfUsernames.txt");
            file = new File(listOfUsernames.getAbsolutePath());
            if(listOfUsernames.delete()){
                System.out.println("List Of Usernames Deleted");
            }else{
                System.out.println("List Of Usernames Not Deleted");
            }
            try {
                FileWriter fw = new FileWriter("listOfUsernames.txt");
                for(int i = 0; i < listOfCustomers.size(); i++){
                    fw.write(Manager.getInstance().getCustomers().get(i).getUsername());
                }
                fw.close();
            } catch (IOException ex) {
                System.out.println("An error occurred.");
            }
            
            
            table.getSelectionModel().clearSelection();
            
        });
        
        bottomLayout.setCenter(remove);
        
        mainLayout.getChildren().addAll(table, bottomLayout);
        
        Scene scene = new Scene(mainLayout);
        window.setScene(scene);
        window.show();
                
        
    }
    
            

    
}
