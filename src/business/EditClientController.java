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

// Importaciones necesarias
import domain.Clients;
import data.ClientsData;

public class EditClientController {
    @FXML private TextField tfID;
    @FXML private TextField tfFullName;
    @FXML private TextField tfPhone;
    @FXML private TextField tfEmail;
    @FXML private TextField tfDirection;
    @FXML private Button bthEdit;
    @FXML private Button btnCancel;

    // Variable para guardar el cliente original que estamos editando
    private Clients originalClient;

    public void initData(Clients client) {
        this.originalClient = client;

        // Llenar los campos visuales
        tfID.setText(client.getID());
        tfFullName.setText(client.getName());
        tfPhone.setText(client.getPhone());
        tfEmail.setText(client.getEmail());
        tfDirection.setText(client.getAdress());

        // Deshabilitar el ID para que no lo cambien (es la llave primaria)
        tfID.setDisable(true);
    }

    @FXML
    public void EditClient(ActionEvent event) {
        // 1. Validar el formulario
        if (validForm()) {
            
            // 2. Obtener los nuevos datos de los campos de texto
            String id = tfID.getText().trim(); // El ID se mantiene igual
            String name = tfFullName.getText().trim();
            String phone = tfPhone.getText().trim();
            String email = tfEmail.getText().trim();
            String address = tfDirection.getText().trim();

            // 3. Crear el objeto con los datos actualizados
            // NOTA: El orden debe coincidir con tu constructor en Clients.java: (ID, Phone, Name, Email, Adress)
            Clients updatedClient = new Clients(id, phone, name, email, address);

            // 4. Llamar a la capa de datos para guardar
            // Pasamos el objeto nuevo y el ID original para que el sistema sepa cuál reemplazar
            if (ClientsData.edit(updatedClient, originalClient.getID())) {
                
                // ÉXITO
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Actualización Exitosa");
                alert.setTitle("Éxito");
                alert.setContentText("Los datos del cliente se han actualizado correctamente.");
                alert.showAndWait(); // Esperamos a que el usuario de OK
                
                // Regresamos a la lista
                Cancel(event); 

            } else {
                // ERROR
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Error al Actualizar");
                alert.setTitle("Error");
                alert.setContentText("No se pudo actualizar la información en la base de datos.");
                alert.show();
            }
        }
    }

    private boolean validForm() {
        String message = "";
        
        // Validación ID
        if (tfID.getText().trim().isEmpty()) { 
            tfID.setStyle("-fx-border-color:red; -fx-border-width:2;"); 
            message += "ID vacío\n"; 
        } else { 
            tfID.setStyle(""); 
        }

        // Validación Nombre
        if (tfFullName.getText().trim().isEmpty()) { 
            tfFullName.setStyle("-fx-border-color:red; -fx-border-width:2;"); 
            message += "Nombre vacío\n"; 
        } else { 
            tfFullName.setStyle(""); 
        }

        // Validación Teléfono
        if (tfPhone.getText().trim().isEmpty()) {
            tfPhone.setStyle("-fx-border-color:red; -fx-border-width:2;"); 
            message += "Teléfono vacío\n";
        } else {
            try { 
                Long.parseLong(tfPhone.getText().trim()); 
                tfPhone.setStyle(""); 
            } catch(NumberFormatException e) { 
                tfPhone.setStyle("-fx-border-color:red; -fx-border-width:2;"); 
                message += "Teléfono inválido (solo números)\n"; 
            }
        }

        // Validación Email
        if (tfEmail.getText().trim().isEmpty()) { 
            tfEmail.setStyle("-fx-border-color:red; -fx-border-width:2;"); 
            message += "Email vacío\n"; 
        } else { 
            tfEmail.setStyle(""); 
        }

        // Validación Dirección
        if (tfDirection.getText().trim().isEmpty()) { 
            tfDirection.setStyle("-fx-border-color:red; -fx-border-width:2;"); 
            message += "Dirección vacía\n"; 
        } else { 
            tfDirection.setStyle(""); 
        }

        if (!message.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Campos inválidos");
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