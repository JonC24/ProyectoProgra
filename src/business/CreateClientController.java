package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.IOException;

public class CreateClientController {
    @FXML private Label lbID;
    @FXML private TextField tfID;
    @FXML private Label lbFullName;
    @FXML private TextField tfFullName;
    @FXML private Label lbPhone;
    @FXML private TextField tfPhone;
    @FXML private Label lbEmail;
    @FXML private TextField tfEmail;
    @FXML private Label lbDirection;
    @FXML private TextField tfDirection;
    @FXML private Button bthSave;
    @FXML private Button btnCancel;

    @FXML
    public void SaveClient(ActionEvent event) {
        if (validForm()) {
            System.out.println("Guardando cliente...");
            
        }
    }

    private boolean validForm() {
        String message = "";

        
        if (this.tfID.getText().trim().isEmpty()) {
            tfID.setStyle("-fx-border-color:red; -fx-border-width:2;");
            message += "ID/Cédula vacío \n";
        } else {
            tfID.setStyle("");
        }

        
        if (this.tfFullName.getText().trim().isEmpty()) {
            tfFullName.setStyle("-fx-border-color:red; -fx-border-width:2;");
            message += "Nombre completo vacío \n";
        } else {
            tfFullName.setStyle("");
        }

       
        if (this.tfPhone.getText().trim().isEmpty()) {
            tfPhone.setStyle("-fx-border-color:red; -fx-border-width:2;");
            message += "Número de teléfono vacío \n";
        } else {
            try {
                Long.parseLong(tfPhone.getText().trim()); // Usamos Long por si es un número grande
                tfPhone.setStyle("");
            } catch (NumberFormatException e) {
                message += "El teléfono debe ser numérico \n";
                tfPhone.setStyle("-fx-border-color:red; -fx-border-width:2;");
            }
        }

        
        if (this.tfEmail.getText().trim().isEmpty()) {
            tfEmail.setStyle("-fx-border-color:red; -fx-border-width:2;");
            message += "Email vacío \n";
        } else {
            tfEmail.setStyle("");
        }

       
        if (this.tfDirection.getText().trim().isEmpty()) {
            tfDirection.setStyle("-fx-border-color:red; -fx-border-width:2;");
            message += "Dirección vacía \n";
        } else {
            tfDirection.setStyle("");
        }

        if (!message.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Campos Faltantes o Incorrectos");
            alert.setTitle("Validación");
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