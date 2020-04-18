package coe528.project;


/**
 *
 * @author Rohan
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


public class Client extends Application{
    Stage window = new Stage();
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        
        loadCustomers();
        
        
        LoginWindow.display();
        
    }
    
    private void loadCustomers(){
        try {
            FileReader listOfUsername = new FileReader("listOfUsernames.txt");
            int i;
            String info = "";

            while((i = listOfUsername.read()) != -1){
                info = info + (char)i;
            }
            String[] usernames = info.split("\n");
            listOfUsername.close();
            for(int j = 0; j < usernames.length; j++){
                FileReader customers = new FileReader(usernames[j] + ".txt");
                int k;
                String customerData = "";

                while((k = customers.read()) != -1){
                    customerData = customerData + (char)k;
                }
                
                String[] userInfo = customerData.split(" ");
                customers.close();
                
                Manager m = Manager.getInstance();
                if(userInfo[4].equals("silver")){
                    m.addCustomerObject(new Customer(userInfo[0] + " " + userInfo[1], userInfo[2], userInfo[3], new BankAccount(Double.parseDouble(userInfo[5])), new Silver())); 
                }else if(userInfo[4].equals("gold")){
                    m.addCustomerObject(new Customer(userInfo[0] + " " + userInfo[1], userInfo[2], userInfo[3], new BankAccount(Double.parseDouble(userInfo[5])), new Gold()));
                }else if(userInfo[4].equals("platinum")){
                    m.addCustomerObject(new Customer(userInfo[0] + " " + userInfo[1], userInfo[2], userInfo[3], new BankAccount(Double.parseDouble(userInfo[5])), new Platinum()));
                }
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("An error occured");
        }
    }
    
    

    
      
}
