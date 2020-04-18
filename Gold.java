package coe528.project;

/**
 *
 * @author Rohan
 */
public class Gold extends Level{

    @Override
    public double onlinePurchase(double price) {
        price = price + 10;
        return price;
    }

    
    
    @Override
    public void promote(Customer c) {
        c.setLevel(new Platinum());
    }

    @Override
    public void demote(Customer c) {
        c.setLevel(new Silver());
    }
    
    
}
