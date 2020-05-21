package timemanager.gui.model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanager.be.User;
import timemanager.bll.UserManager;

/**
 *
 * @author andreasvillumsen
 */
public class UserModel {
    private static UserModel single_instance = null; 
    private UserManager um;
    private LoginModel lm;
    private ObservableList<User> users;

    public static UserModel getInstance() throws IOException {
        if (single_instance == null) 
            single_instance = new UserModel(); 
  
        return single_instance; 
    }
    
    private UserModel() throws IOException {
        um = new UserManager();
        users = FXCollections.observableArrayList();
    }
    
    public void refreshData() {
        users.clear();
        users.addAll(um.getUsers());
    }
    
    public ObservableList<User> getUsers() {
        refreshData();
        return users;
    }
    
    public void storeUser(String name, String email, String password, Boolean isAdmin){
        try {
            um.storeUser(name, email, password, isAdmin);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshData();
    }
    
    public void updateUser(User user) {
        um.updateUser(user);
        refreshData();
    }

    public void deleteUser(User user) {
        um.deleteUser(user);
        refreshData();
    }
}
