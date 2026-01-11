package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

public class EditClientController {
    @FXML private TextField tfID;
    @FXML private TextField tfFullName;
    @FXML private TextField tfPhone;
    @FXML private TextField tfEmail;
    @FXML private TextField tfDirection;
    @FXML private Button bthEdit;
    @FXML private Button btnCancel;

    @FXML
    public void EditClient(ActionEvent event) {
        if (validForm()) {
            System.out.println("Actualizando datos del cliente...");
        }
    }

    private boolean validForm() {
        String message = "";
        
        
        if (tfID.getText().trim().isEmpty()) { 
            tfID.setStyle("-fx-border-color:red;"); message += "ID vacío\n"; 
        } else { tfID.setStyle(""); }

        if (tfFullName.getText().trim().isEmpty()) { 
            tfFullName.setStyle("-fx-border-color:red;"); message += "Nombre vacío\n"; 
        } else { tfFullName.setStyle(""); }

        if (tfPhone.getText().trim().isEmpty()) {
            tfPhone.setStyle("-fx-border-color:red;"); message += "Teléfono vacío\n";
        } else {
            try { Long.parseLong(tfPhone.getText().trim()); tfPhone.setStyle(""); }
            catch(NumberFormatException e) { 
                tfPhone.setStyle("-fx-border-color:red;"); message += "Teléfono inválido\n"; 
            }
        }

        if (tfEmail.getText().trim().isEmpty()) { 
            tfEmail.setStyle("-fx-border-color:red;"); message += "Email vacío\n"; 
        } else { tfEmail.setStyle(""); }

        if (tfDirection.getText().trim().isEmpty()) { 
            tfDirection.setStyle("-fx-border-color:red;"); message += "Dirección vacía\n"; 
        } else { tfDirection.setStyle(""); }

        if (!message.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText(message);
            alert.show();
        }
        return message.isEmpty();
    }

    	@FXML
    	public void Cancel(ActionEvent event) {
        	try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GUIMantenimientoDeClientes.fxml"));
            Parent root = loader.load();
            Scene scene = btnCancel.getScene();
            scene.setRoot(root);
            scene.getWindow().sizeToScene();
        	} catch (IOException e) {
            e.printStackTrace();
        	}
    	}
}