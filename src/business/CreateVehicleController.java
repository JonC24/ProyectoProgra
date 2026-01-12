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

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

public class CreateVehicleController {
	@FXML
	private Label lbPlate;
	@FXML
	private TextField tfPlate;
	@FXML
	private Label lbBrand;
	@FXML
	private TextField tfBrand;
	@FXML
	private Label lbModel;
	@FXML
	private TextField tfModel;
	@FXML
	private Label lbYear;
	@FXML
	private TextField tfYear;
	@FXML
	private Label lbFuelType;
	@FXML
	private Button btnVehicle;
	@FXML
	private Button btnCancel;
	@FXML
	private ComboBox cbxTypeFuel;
	@FXML
	private Label lbOwner;
	@FXML
	private ComboBox cbxOwner;
	
	@FXML
	public void initialize() {
		cbxTypeFuel.getItems().addAll("","Gasolina","Diesel", "Electrico","Hibrido");
	}

	private boolean validForm() {
		String message = "";

		
		if (this.tfPlate.getText().trim().isEmpty()) {
			tfPlate.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Placa vacía \n";
		} else {
			tfPlate.setStyle("");
		}

		
		if (this.tfBrand.getText().trim().isEmpty()) {
			tfBrand.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Marca vacía \n";
		} else {
			tfBrand.setStyle("");
		}

		
		if (this.tfModel.getText().trim().isEmpty()) {
			tfModel.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Modelo vacío \n";
		} else {
			tfModel.setStyle("");
		}

		
		if (this.tfYear.getText().trim().isEmpty()) {
			tfYear.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Año del vehículo vacío \n";
		} else {
			try {
				Integer.parseInt(tfYear.getText().trim());
				tfYear.setStyle("");
			} catch (NumberFormatException e) {
				message += "El año no es un número válido \n";
				tfYear.setStyle("-fx-border-color:red; -fx-border-width:2;");
			}
		}

	
		if (cbxTypeFuel.getValue() == null) {
			cbxTypeFuel.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Tipo de combustible no seleccionado \n";
		} else {
			cbxTypeFuel.setStyle("");
		}

		
		if (cbxOwner.getValue() == null) {
			cbxOwner.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Dueño no seleccionado \n";
		} else {
			cbxOwner.setStyle("");
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

	// Event Listener on Button[#btnVehicle].onAction
	@FXML
	public void SaveVehicle(ActionEvent event) {
		if (validForm()) {
			
			System.out.println("Vehículo validado y listo para guardar.");
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