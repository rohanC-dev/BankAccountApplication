package coe528.project;


/**
 * @author Rohan
 */


import java.io.*;
import javafx.scene.control.TextField;


public class Customer extends People{
    
    private String name;
    private String username;
    private String password;
    private BankAccount account;
    private Level level = new Silver();
    private static Customer instance;
    
    
    public Customer(String name, String username, String password, BankAccount account, Level level){
        this.name = name;
        this.username = username;
        this.password = password;
        this.account = account;
        this.level = level;
        
    }
    
    public Customer(){
        
    }


    //what this method needs to do
    //takes input from textfield, checks file for the correct username, finds it? opens file and checks for correct username and password, yes? log the user in and return the specified customer back to caller
    @Override
    public boolean login(TextField nameInput, TextField passInput) {
        String username = nameInput.getText();
        String password = passInput.getText();
        Manager m = Manager.getInstance();
        
        try{
            
            FileReader fr = new FileReader(username + ".txt");
            int i;
            String info = "";
            while((i = fr.read()) != -1){
                info = info + (char)i;
            }
            
            //mapping in text file
            //userInfo[0] is the firstname
            //userInfo[1] is the lastname
            //userInfo[2] is the username
            //userInfo[3] is the password
            //userInfo[4] is the account level (silver, gold or platinum)
            //userInfo[5] is the amount of money in the account
            
            String[] userInfo = info.split(" ");
            

            if(username.equals(userInfo[2]) && password.equals(userInfo[3])){
                System.out.println("Customer Found");
                  
                if(userInfo[4].equals("silver")){
                    instance = new Customer(userInfo[0] + " " + userInfo[1], userInfo[2], userInfo[3], new BankAccount(Double.parseDouble(userInfo[5])), new Silver());
                }else if(userInfo[4].equals("gold")){
                    instance = new Customer(userInfo[0] + " " + userInfo[1], userInfo[2], userInfo[3], new BankAccount(Double.parseDouble(userInfo[5])), new Gold());
                }else if(userInfo[4].equals("platinum")){
                    instance = new Customer(userInfo[0] + " " + userInfo[1], userInfo[2], userInfo[3], new BankAccount(Double.parseDouble(userInfo[5])), new Platinum());
                }
                
                return true;
            }else{
                return false;
            }
            
            
        }catch(FileNotFoundException ex) {
            System.out.println("Customer not found");
            return false;
        }catch(IOException ex) {
            System.out.println("An error occured.");
            return false;
        }
    }
    public String getName(){
        return name;
    }
    
    public String getUsername(){
        return username;
    }

    private String getPassword(){
        return password;
    }
    
    // CHANGE THIS???
    public BankAccount getBankAccount(){
        return account;
    }
    
    public Level getLevel(){
        return level;
    }
    
    public static Customer getLoginInstance(){
        return instance;
    }
   
    
    
    // methods to change level (to traverse through state machine)
    public void promote(){
        level.promote(this);
    }
    // methods to change level (to traverse through state machine)
    public void demote(){
        level.demote(this);
    }
    
    public void setLevel(Level level){
        this.level = level;
    }
    
    
    
    public String getLevelString(){
        if(level instanceof Silver){
            return "Silver";
        }else if(level instanceof Gold){
            return "Gold";
        }else if(level instanceof Platinum){
            return "Platinum";
        }
        return "";
    }
    
    private Level checkLevel(){
        
        if(account.getBalance() < 10000){
            return new Silver();
        }else if(account.getBalance()  >= 10000 && account.getBalance()  < 20000){
            return new Gold();
        }else if(account.getBalance()  > 20000){
            return new Platinum();
        }
        
        
       return null; 
    }
    
    public void changeLevel(){
        if(level instanceof Silver){
            if(checkLevel() instanceof Gold){
                this.promote();
                System.out.println("PROMOTED");
                AlertBoxWindow.display("Alert", "You have been promoted to Gold Status.");
            }else if(checkLevel() instanceof Platinum){
                this.promote();
                this.promote();
                System.out.println("PROMOTED");
                AlertBoxWindow.display("Alert", "You have been promoted to Gold Status.");
                System.out.println("PROMOTED");
                AlertBoxWindow.display("Alert", "You have been promoted to Platinum Status.");
            }
        }else if(level instanceof Gold){
            if(checkLevel() instanceof Platinum){
                this.promote();
                System.out.println("PROMOTED");
                AlertBoxWindow.display("Alert", "You have been promoted to Platinum Status.");
            }else if(checkLevel() instanceof Silver){
                this.demote();
                System.out.println("DEMOTED");
                AlertBoxWindow.display("Alert", "You have been demoted to Silver Status.");
            }
        }else if(level instanceof Platinum){
            if(checkLevel() instanceof Gold){
                this.demote();
                System.out.println("DEMOTED");
                AlertBoxWindow.display("Alert", "You have been demoted to Gold Status.");
            }else if(checkLevel() instanceof Silver){
                this.demote();
                this.demote();
                System.out.println("DEMOTED");
                AlertBoxWindow.display("Alert", "You have been demoted to Gold Status.");
                System.out.println("DEMOTED");
                AlertBoxWindow.display("Alert", "You have been demoted to Silver Status.");
            }
        }
    }
    
    public void editTextFile(String username){
        Level level  = this.getLevel();
        
        String stringLevel = "";
        if(level instanceof Silver){
            stringLevel = "silver";
        }else if(level instanceof Gold){
            stringLevel = "gold";
        }else if(level instanceof Platinum){
            stringLevel = "platinum";
        }
        try {
            FileWriter fw = new FileWriter(username + ".txt", false);
            fw.close();
            
        }catch(IOException e){
            System.out.println("An error occurred.");
        }
        
        
        try {
            FileWriter fw = new FileWriter(username + ".txt", true);
            
            Customer c = Customer.getLoginInstance();
            
            fw.write(c.getName());
            fw.write(" ");
            fw.write(c.getUsername());
            fw.write(" ");
            fw.write(c.getPassword());
            fw.write(" ");
            fw.write(stringLevel);
            fw.write(" ");
            fw.write(Double.toString(c.getBankAccount().getBalance()));
            fw.close();
        }catch(IOException e){
            System.out.println("An error occurred.");
        }
        
    }
    
    
    
}
