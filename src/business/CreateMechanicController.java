package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

public class CreateMechanicController {
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
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private ComboBox cbxSpeciality;

	@FXML
	public void initialize() {
		cbxSpeciality.getItems().addAll("","mecánica general", "frenos", "electricidad", "motor");
	}
	
		private boolean validForm() {
			String message = "";

			
			if (this.tfFullName.getText().trim().isEmpty()) {
				tfFullName.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Nombre completo vacío \n";
			} else {
				tfFullName.setStyle("");
			}

			
			if (cbxSpeciality.getValue() == null) {
				cbxSpeciality.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Especialidad no seleccionada \n";
			} else {
				cbxSpeciality.setStyle("");
			}

			
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

			
			if (this.tfEmail.getText().trim().isEmpty()) {
				tfEmail.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Correo electrónico vacío \n";
			} else {
				tfEmail.setStyle("");
			}

			
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
				
				System.out.println("Datos de mecánico válidos. Guardando cambios...");
			}
		}
		
	// Event Listener on Button[#btnCancel].onAction
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
