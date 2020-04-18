package coe528.project;

/**
 *
 * @author Rohan
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.TextField;

public class Manager extends People{
    
    private static Manager instance;
    private ArrayList<Customer> customers;
    final private String USERNAME;
    final private String PASSWORD;
    
    /**
     * OVERVIEW: Handles logging in a Manager, and adding and removing a customer. A Manager has
     * a username "admin", a password "admin", and manages a list of customers. This class is mutable. This class is a Singleton.
     * 
     * ABSTRACTION FUNCTION:
     * 
     * AF(c) = a Manager m such that
     *      m.username = "admin"
     *      m.password = "admin"
     *      m.customers = c.customers = {c.customers.get(0), c.customers.get(1), ... , c.customers.get(n)} for each adjacent customer in m 
     *      and where n is the number of customers in the list
     * 
     * REP INVARIANT:
     * 
     * RI(c) = true if
     *      1. c.customers.get(i).getUsername() != c.customers.get(j).getUsername() where i and j are any number which a customer is indexed to, but i != j
     *      (i.e., each customer's username is distinct)
     *      2. c.username = "admin" && c.password = "admin"
     *      
     * 
     */
    
    
    
    private Manager(){
        customers = new ArrayList<Customer>();
        this.USERNAME = "admin";
        this.PASSWORD = "admin";
    }
    
    /**
     * EFFECTS: returns true or false whether the input of the username and password 
     * in the TextField is correct for the Manager
     * 
     * @param nameInput TextField where the Manager enters their username
     * @param passInput TextField where the Manager enters their password
     * @return a boolean true or false whether the input is correct for the Manager
     */
    @Override
    public boolean login(TextField nameInput, TextField passInput) {
        if((nameInput.getText()).equals(USERNAME) && (passInput.getText().equals(PASSWORD))){
            return true;
        }else{
            
            System.out.println("Wrong username or password");
            return false;
        }
        
    }
    
    /**
     * EFFECTS: returns true or false whether the customer has been successfully
     * added to the ArrayList and written to .txt file 
     * 
     * 
     * @param firstName String of first name of the customer
     * @param lastName String of last name of the customer
     * @param username String of username of the customer
     * @param password String of password of the customer
     * @return returns boolean true or false whether the customer has been successfully
     * added to the ArrayList and written to .txt file 
     */
    
    public boolean addCustomer(String firstName, String lastName, String username, String password){
        Customer c = new Customer(firstName + " " + lastName, username, password, new BankAccount(100), new Silver());
        boolean usernameExists = false;
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getUsername().equals(username)){
                System.out.println("Username already exists.");
                usernameExists = true;
                return false; 
            }
        }
        if(!usernameExists){
            customers.add(c);
            System.out.println("Customer successfully added.");
        }       
        
        FileWriter fw, fw2;
        try {
            fw = new FileWriter(username + ".txt", true);
            fw2 = new FileWriter("listOfUsernames.txt", true);
            fw.write(firstName);
            fw.write(" ");
            fw.write(lastName);
            fw.write(" ");
            fw.write(username);
            fw.write(" ");
            fw.write(password);
            fw.write(" ");
            fw.write("silver");
            fw.write(" ");
            fw.write(Double.toString(c.getBankAccount().getBalance()));
            fw.close();
            
            fw2.write(username);
            fw2.write("\n");
            fw2.close();
        }catch(IOException e){
            System.out.println("An error occurred.");
        }
        
        return true;
    }
    /**
     * EFFECTS: adds customer in parameters to ArrayList of Manager
     * 
     * 
     * @param c customer object
     */
    public void addCustomerObject(Customer c){
        customers.add(c);
    }
    /**
     * EFFECTS: removes customer in parameters in ArrayList of Manager
     * 
     * 
     * 
     * @param c customer object
     */
    public void deleteCustomer(Customer c){
        customers.remove(c);
    }
    /**
     * EFFECTS: returns instance of singleton Manager object
     * 
     * @return the single Manager object
     */
    public static Manager getInstance(){
        if(instance == null){
           instance = new Manager();
        }
        return instance;
    }
    
    /**
     * EFFECTS: returns list of customers
     * 
     * 
     * @return list of customers
     */
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    
    /**
     * EFFECTS: returns true if the Manager is valid (legal) Manager object, returns false otherwise (illegal)
     * @return true or false depending on if Manager object is legal
     */
    public boolean repOk(){
        for (int i = 0; i < customers.size(); i++) {
            for (int j = 0; j < customers.size(); j++) {
                if (customers.get(i).getUsername().equals(customers.get(j).getUsername()) && i != j) {
                    return false;
                }
            }
        }
        if(!this.USERNAME.equals("admin") || !this.PASSWORD.equals("admin")){
            return false;
        }
        return true;
    }
    /**
     * EFFECTS: returns a String containing the abstract concept of a Manager
     * @return a String that implements the abstraction function of the Manager
     */
    @Override
    public String toString(){
        String customerString = "{";
        for(int i = 0; i < customers.size(); i++){
            customerString = customerString + customers.get(i).getUsername();
            customerString = customerString + ", ";
        }
        customerString = customerString + "}";
        return "A Manager with username " + "\"" + this.USERNAME + "\"" + " and password " + "\"" +this.PASSWORD + "\"" + " manages a list of customers " + customerString;
    }
    


}