package coe528.project;
/**
 *
 * @author Rohan
 */
public abstract class Level {
    
    public Level(){
        
    }
    public abstract double onlinePurchase(double price);
    
    public abstract void promote(Customer c);
    public abstract void demote(Customer c);
    
}
