package coe528.project;
/**
 *
 * @author Rohan
 */

import javafx.scene.control.TextField;

public abstract class People {
    private People role;
    private String username;
    private String password;
    
    public abstract boolean login(TextField nameInput, TextField passInput);
    
    public void logout(){
        LoginWindow.display();
    }
    public void setRole(People p){
        role = p;
    }
    
    
    
}
