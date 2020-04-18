package coe528.project;

/**
 *
 * @author Rohan
 */
public class BankAccount {
    private double money;
    
    public BankAccount(double money){
        this.money = money;
    }

    
    public void deposit(double amount, String username){
        money = money + amount;
        Customer c = Customer.getLoginInstance();
        c.changeLevel();
        c.editTextFile(username);
 
    }
    public void withdraw(double amount, String username){
        
        money = money - amount;
        Customer c = Customer.getLoginInstance();
        c.changeLevel();
        c.editTextFile(username);

    }
    
    public double getBalance(){
        return money;
    }
    

   
}
