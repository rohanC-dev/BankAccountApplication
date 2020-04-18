package coe528.project;

/**
 *
 * @author Rohan
 */

import java.text.DecimalFormat;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OnlinePurchaseWindow {

    public static void display(){
        Stage window = new Stage();
        window.setTitle("Shopping Window");
        
        VBox mainLayout = new VBox();
        
        VBox embeddedLayout = new VBox(10);
        embeddedLayout.setPadding(new Insets(10,10,10,10));
        
        HBox bottomLayout = new HBox(10);
        
        Label customItem = new Label("Custom Item");
        
        TableView<Item> table = new TableView<>();
        
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Item, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        ObservableList<Item> items = FXCollections.observableArrayList();
        
        items.add(new Item("Shoes", 43.99));
        items.add(new Item("Laptop", 579.99));
        items.add(new Item("Android Phone", 399.99));
        items.add(new Item("Headphones", 50.99));
        items.add(new Item("Shirt", 79.99));
        items.add(new Item("Backpack", 50.99));
        items.add(new Item("Calculator", 51.99));
        
        
        table.setItems(items);
        
        table.getColumns().addAll(nameColumn, priceColumn);
        
        TextField itemInput = new TextField();
        itemInput.setPromptText("item");
        
        TextField priceInput = new TextField();
        priceInput.setPromptText("price");
        
        Button buyButton = new Button("BUY");
        
        buyButton.setOnAction(e -> {
            Customer c = Customer.getLoginInstance();
            BankAccount b = c.getBankAccount();
            DecimalFormat df = new DecimalFormat("#.##");
            
            boolean selectionError = false;
            if(table.getSelectionModel().getSelectedItem() != null && !itemInput.getText().isEmpty() || table.getSelectionModel().getSelectedItem() != null && !priceInput.getText().isEmpty() || table.getSelectionModel().getSelectedItem() != null && !itemInput.getText().isEmpty() && !priceInput.getText().isEmpty() ){
                AlertBoxWindow.display("Item Purchases", "Can only purchase one item at a time.");
                selectionError = true;
                table.getSelectionModel().clearSelection();
                itemInput.clear();
                priceInput.clear();
                
            }
            
            if(table.getSelectionModel().getSelectedItem() != null && itemInput.getText().isEmpty() && priceInput.getText().isEmpty() && !selectionError){
                
                double price = c.getLevel().onlinePurchase(table.getSelectionModel().getSelectedItem().getPrice());
                AlertBoxWindow.display("Item Purchases", "You purchased a " + table.getSelectionModel().getSelectedItem().getName() + " for $" + df.format(price));
                b.withdraw(price, c.getUsername());
                table.getSelectionModel().clearSelection();
            }else if(!itemInput.getText().isEmpty() && !priceInput.getText().isEmpty() && !selectionError){
                if((Double.parseDouble(priceInput.getText())) < 50){
                    AlertBoxWindow.display("Item Purchases", "Item must be $50 or more.");
                }else{
                    double price = c.getLevel().onlinePurchase(Double.parseDouble(priceInput.getText()));
                    AlertBoxWindow.display("Item Purchases", "You purchased a " + itemInput.getText() + " for $" + df.format(price));
                    b.withdraw(price, c.getUsername());
                    itemInput.clear();
                    priceInput.clear();
                }
            }
                });

        bottomLayout.getChildren().addAll(itemInput, priceInput, buyButton);
        embeddedLayout.getChildren().addAll(customItem, bottomLayout);
        
        embeddedLayout.setAlignment(Pos.CENTER);
        bottomLayout.setAlignment(Pos.CENTER);
        
        mainLayout.getChildren().addAll(table, embeddedLayout);
        
        Scene scene = new Scene(mainLayout);
        
        window.setScene(scene);
        window.show();

    }
    
}
