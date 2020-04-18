package coe528.project;

/**
 *
 * @author Rohan
 */
public class Platinum extends Level{

    @Override
    public double onlinePurchase(double price) {
        return price;
    }

    @Override
    public void promote(Customer c) {
        c.setLevel(new Platinum());
    }

    @Override
    public void demote(Customer c) {
        c.setLevel(new Gold());
    }
    
    
}
