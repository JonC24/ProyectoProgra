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

import data.ServicesData;
import domain.Services;

public class EditServiceController {
	@FXML
	private Label lbCodeService;
	@FXML
	private TextField tfCodeService;
	@FXML
	private Label lbNameOfService;
	@FXML
	private Label lbPhone;
	@FXML
	private TextField tfPhone;
	@FXML
	private Label lbDescription;
	@FXML
	private TextField tfDescription;
	@FXML
	private Label lbCostoBase;
	@FXML
	private TextField tfCostoBase;
	@FXML
	private Button btnEditService;
	@FXML
	private Button btnCancel;
	@FXML
	private ComboBox<String> cbxNameOfService;
	@FXML
	private Label lbEstimatedTime;
	@FXML
	private TextField tfEstimatedTime;

	// store original service code so we can find the item to replace
	private String originalCode;

	public void initialize() {
		cbxNameOfService.getItems().addAll("", "mecánica general", "frenos", "electricidad", "motor");
	}

	public void setServiceToEdit(Services service) {
		if (service == null) return;
		this.originalCode = service.getCodeOfService();
		this.tfCodeService.setText(service.getCodeOfService());
		this.cbxNameOfService.setValue(service.getNameOfService());
		this.tfDescription.setText(service.getDescription());
		this.tfCostoBase.setText(String.valueOf(service.getBaseCost()));
		this.tfEstimatedTime.setText(String.valueOf((int) service.getEstimatedTime()));
	}

	private boolean validForm() {
		String message = "";

		if (this.tfCodeService.getText().trim().isEmpty()) {
			tfCodeService.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Código de servicio vacío \n";
		} else {
			tfCodeService.setStyle("");
		}

		if (cbxNameOfService.getValue() == null || cbxNameOfService.getValue().toString().trim().isEmpty()) {
			cbxNameOfService.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Nombre del servicio no seleccionado \n";
		} else {
			cbxNameOfService.setStyle("");
		}

		if (this.tfPhone.getText().trim().isEmpty()) {
			tfPhone.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Número de teléfono vacío \n";
		} else {
			try {
				Integer.parseInt(tfPhone.getText().trim());
				tfPhone.setStyle("");
			} catch (NumberFormatException e) {
				message += "El teléfono debe ser numérico \n";
				tfPhone.setStyle("-fx-border-color:red; -fx-border-width:2;");
			}
		}

		if (this.tfDescription.getText().trim().isEmpty()) {
			tfDescription.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Descripción vacía \n";
		} else {
			tfDescription.setStyle("");
		}

		if (this.tfCostoBase.getText().trim().isEmpty()) {
			tfCostoBase.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Costo base vacío \n";
		} else {
			try {
				Double.parseDouble(tfCostoBase.getText().trim());
				tfCostoBase.setStyle("");
			} catch (NumberFormatException e) {
				message += "El costo base debe ser un número válido \n";
				tfCostoBase.setStyle("-fx-border-color:red; -fx-border-width:2;");
			}
		}

		if (this.tfEstimatedTime.getText().trim().isEmpty()) {
			tfEstimatedTime.setStyle("-fx-border-color:red; -fx-border-width:2;");
			message += "Tiempo estimado vacío \n";
		} else {
			try {
				Integer.parseInt(tfEstimatedTime.getText().trim());
				tfEstimatedTime.setStyle("");
			} catch (NumberFormatException e) {
				message += "El tiempo estimado debe ser un número entero \n";
				tfEstimatedTime.setStyle("-fx-border-color:red; -fx-border-width:2;");
			}
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

	// Event Listener on Button[#btnEditService].onAction
	@FXML
	public void EditService(ActionEvent event) {
		if (validForm()) {
			// read values
			String code = tfCodeService.getText().trim();
			String name = cbxNameOfService.getValue() == null ? "" : cbxNameOfService.getValue().toString().trim();
			String description = tfDescription.getText().trim();
			double baseCost = Double.parseDouble(tfCostoBase.getText().trim());
			double estimatedTime = Double.parseDouble(tfEstimatedTime.getText().trim());

			Services service = new Services(code, name, description, baseCost, estimatedTime);

			String searchCode = (originalCode != null && !originalCode.trim().isEmpty()) ? originalCode : code;

			boolean edited = ServicesData.edit(service, searchCode);
			if (edited) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Éxito");
				alert.setHeaderText(null);
				alert.setContentText("Servicio editado correctamente.");
				alert.showAndWait();

				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GUIMantenimientoDeClientes.fxml"));
					Parent root = loader.load();
					Scene scene = btnEditService.getScene();
					scene.setRoot(root);
					scene.getWindow().sizeToScene();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error al editar");
				alert.setHeaderText("No se pudo editar el servicio");
				alert.setContentText("Verifique que el servicio exista o que no exista otro servicio con el mismo código.");
				alert.show();
			}
		}
	}

	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void Cancel(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GUIMantenimientoDeServicios.fxml"));
			Parent root = loader.load();
			Scene scene = btnCancel.getScene();
			scene.setRoot(root);
			scene.getWindow().sizeToScene();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Event Listener on ComboBox[#cbxNameOfService].onAction
	@FXML
	public void ChooseTheService(ActionEvent event) {
		// no action needed here for now
	}
}