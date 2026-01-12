package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;

import data.ClientsData;
import data.MechanicsData;
import domain.Clients;
import domain.Mechanics;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

public class EditMechanicController {
	@FXML
	private Label lbID;
	@FXML
	private TextField tfID;
	@FXML
	private Label lbFullName;
	@FXML
	private TextField tfFullName;
	@FXML
	private Label lbSpeciality;
	@FXML
	private Label lbPhone;
	@FXML
	private TextField tfPhone;
	@FXML
	private Label lbEmail;
	@FXML
	private TextField tfEmail;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnCancel;
	@FXML
	private ComboBox cbxSpeciality;
	
	private Mechanics originalMechanic;

	
	@FXML
	public void initialize() {
		cbxSpeciality.getItems().addAll("mecánica general", "frenos", "electricidad", "motor");
	}
	
	public void initData(Mechanics mechanic) {
        this.originalMechanic = mechanic;

        // Llenar los campos visuales
        tfID.setText(mechanic.getID());
        tfFullName.setText(mechanic.getFullName());
        tfPhone.setText(mechanic.getPhone());
        tfEmail.setText(mechanic.getEmail());
        cbxSpeciality.setValue(mechanic.getSpeciality());

        // Deshabilitar el ID para que no lo cambien (es la llave primaria)
        tfID.setDisable(true);
    }
	// Método de validación siguiendo el patrón anterior
		private boolean validForm() {
			String message = "";

			// Validación Nombre Completo
			if (this.tfFullName.getText().trim().isEmpty()) {
				tfFullName.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Nombre completo vacío \n";
			} else {
				tfFullName.setStyle("");
			}

			// Validación Especialidad (ComboBox)
			if (cbxSpeciality.getValue() == null) {
				cbxSpeciality.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Especialidad no seleccionada \n";
			} else {
				cbxSpeciality.setStyle("");
			}

			// Validación Teléfono (Numérico)
			if (this.tfPhone.getText().trim().isEmpty()) {
				tfPhone.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Número de teléfono vacío \n";
			} else {
				try {
					Integer.parseInt(tfPhone.getText().trim());
					tfPhone.setStyle("");
				} catch (NumberFormatException e) {
					message += "No es un número de teléfono válido \n";
					tfPhone.setStyle("-fx-border-color:red; -fx-border-width:2;");
				}
			}

			// Validación Email
			if (this.tfEmail.getText().trim().isEmpty()) {
				tfEmail.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Correo electrónico vacío \n";
			} else {
				tfEmail.setStyle("");
			}

			// Mostrar Alerta si hay errores
			if (!message.isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Campos Faltantes o Inválidos");
				alert.setTitle("Ups!");
				alert.setContentText(message);
				alert.show();
			}

			return message.isEmpty();
		}

		// Event Listener on Button[#btnEdit].onAction
		@FXML
		public void EditMechanic(ActionEvent event) {
			if (validForm()) {

	            String name = tfFullName.getText().trim();
	            String phone = tfPhone.getText().trim();
	            String email = tfEmail.getText().trim();
	            String speciality = cbxSpeciality.getValue().toString();
	           
	            Mechanics updatedMechanic = new Mechanics(tfID.getText(), name, speciality, email, phone);
	            // 4. Llamar a la capa de datos para guardar
	            // Pasamos el objeto nuevo y el ID original para que el sistema sepa cuál reemplazar
	           System.out.println(tfID.getText()+"\n"+originalMechanic.getID());
	            if (MechanicsData.edit(updatedMechanic, originalMechanic.getID())) {
	                
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
				
				System.out.println("Datos de mecánico válidos. Guardando cambios...");
			}
		}
		
	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void Cancel(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GUIMantenimientoDeMecanicos.fxml"));
			Parent root = loader.load();
			Scene scene = btnCancel.getScene();
			scene.setRoot(root);
			scene.getWindow().sizeToScene();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
