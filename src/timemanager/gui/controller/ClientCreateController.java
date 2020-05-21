package timemanager.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import timemanager.TimeManagerStart;
import timemanager.be.Client;
import timemanager.gui.model.ClientModel;
import timemanager.utils.validation.Validate;

/**
 * FXML Controller class
 *
 * @author andreasvillumsen
 */
public class ClientCreateController implements Initializable {
    
    TimeManagerStart tms = new TimeManagerStart();
    ClientModel cm;
    Client client = null;
    
    @FXML
    private Button saveUserButton;
    @FXML
    private Button cancelSaveClient;
    @FXML
    private TextField clientName;
    @FXML
    private Label errorLabel;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            cm = ClientModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setClient(Client client) {
        this.client = client;
        if(client != null) setupFields();
    }
    
    @FXML
    private void saveClient(ActionEvent event) {
        String name = clientName.getText();
        if(validateName(name)) {
            if(client != null) {
                client.setName(name);
                cm.updateClient(client);
            } else {
                cm.storeClient(new Client(0, name));
            }
            
            Stage window = (Stage) (cancelSaveClient.getScene().getWindow());
            window.close();
        } else {
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void cancelSaveClient(ActionEvent event) {
        Stage window = (Stage) (cancelSaveClient.getScene().getWindow());
        window.close();
    }
    
    private boolean validateName(String name) {
        return !(Validate.isNull(name) || !Validate.containsAtLeast(name, 3));
    }

    private void setupFields() {
        clientName.setText(client.getName());
    }
}
