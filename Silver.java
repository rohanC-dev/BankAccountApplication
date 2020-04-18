package coe528.project;

/**
 *
 * @author Rohan
 */
public class Silver extends Level{

    @Override
    public double onlinePurchase(double price){
        price = price + 20;
        return price;
    }

    @Override
    public void promote(Customer c) {
        c.setLevel(new Gold());
    }

    @Override
    public void demote(Customer c) {
        c.setLevel(new Silver());
    }

    

    

    
    
    
}
