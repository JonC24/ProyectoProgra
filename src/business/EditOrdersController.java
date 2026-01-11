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

public class EditOrdersController {
	@FXML
	private Label lbNumOfOrder;
	@FXML
	private TextField tfNumOfOrder;
	@FXML
	private Label lbNameOfService;
	@FXML
	private Label lbStateOfOrder;
	@FXML
	private Label lbVehicle;
	@FXML
	private Label lbClient;
	@FXML
	private Button btnEditOrders;
	@FXML
	private Button btnCancel;
	@FXML
	private Label lbAsignatedMechanic;
	@FXML
	private ComboBox cbxStateOfOrder;
	@FXML
	private ComboBox cbxVehicle;
	@FXML
	private ComboBox cbxClient;
	@FXML
	private ComboBox cbxAsignatedMech;
	@FXML
	private ComboBox cbxAppliedServ;
	@FXML
	private Label lbAppliedServices;
	@FXML
	private Label lbObservations;
	@FXML
	private TextField tfObservations;
	@FXML
	private Label lbTotalCost;
	@FXML
	private TextField tfTotalCost;

	// Método de validación siguiendo el patrón establecido
		private boolean validForm() {
			String message = "";

			// Validación Número de Orden (Numérico Entero)
			if (this.tfNumOfOrder.getText().trim().isEmpty()) {
				tfNumOfOrder.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Número de orden vacío \n";
			} else {
				try {
					Integer.parseInt(tfNumOfOrder.getText().trim());
					tfNumOfOrder.setStyle("");
				} catch (NumberFormatException e) {
					message += "El número de orden debe ser un entero válido \n";
					tfNumOfOrder.setStyle("-fx-border-color:red; -fx-border-width:2;");
				}
			}

			// Validación Estado de la Orden (ComboBox)
			if (cbxStateOfOrder.getValue() == null) {
				cbxStateOfOrder.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Estado de la orden no seleccionado \n";
			} else {
				cbxStateOfOrder.setStyle("");
			}

			// Validación Vehículo (ComboBox)
			if (cbxVehicle.getValue() == null) {
				cbxVehicle.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Vehículo no seleccionado \n";
			} else {
				cbxVehicle.setStyle("");
			}

			// Validación Cliente (ComboBox)
			if (cbxClient.getValue() == null) {
				cbxClient.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Cliente no seleccionado \n";
			} else {
				cbxClient.setStyle("");
			}

			// Validación Mecánico Asignado (ComboBox)
			if (cbxAsignatedMech.getValue() == null) {
				cbxAsignatedMech.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Mecánico no seleccionado \n";
			} else {
				cbxAsignatedMech.setStyle("");
			}

			// Validación Servicio Aplicado (ComboBox)
			if (cbxAppliedServ.getValue() == null) {
				cbxAppliedServ.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Servicio no seleccionado \n";
			} else {
				cbxAppliedServ.setStyle("");
			}

			// Validación Observaciones
			if (this.tfObservations.getText().trim().isEmpty()) {
				tfObservations.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Observaciones vacías \n";
			} else {
				tfObservations.setStyle("");
			}

			// Validación Costo Total (Numérico Double)
			if (this.tfTotalCost.getText().trim().isEmpty()) {
				tfTotalCost.setStyle("-fx-border-color:red; -fx-border-width:2;");
				message += "Costo total vacío \n";
			} else {
				try {
					Double.parseDouble(tfTotalCost.getText().trim());
					tfTotalCost.setStyle("");
				} catch (NumberFormatException e) {
					message += "El costo total debe ser un número válido \n";
					tfTotalCost.setStyle("-fx-border-color:red; -fx-border-width:2;");
				}
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

		// Event Listener on Button[#btnSaveOrder].onAction
		@FXML
		public void SaveOrder(ActionEvent event) {
			if (validForm()) {
				// Lógica para guardar la orden de trabajo
				// WorkOrder order = new WorkOrder(...);
				// orderDAO.save(order);
				System.out.println("Orden validada correctamente y lista para guardar.");
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
	// Event Listener on ComboBox[#cbxStateOfOrder].onAction
	@FXML
	public void ChooseStateOfOrder(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on ComboBox[#cbxVehicle].onAction
	@FXML
	public void ChooseVehicle(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on ComboBox[#cbxClient].onAction
	@FXML
	public void ChooseClient(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on ComboBox[#cbxAsignatedMech].onAction
	@FXML
	public void ChooseAsigMech(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on ComboBox[#cbxAppliedServ].onAction
	@FXML
	public void ChooseAppliedserv(ActionEvent event) {
		// TODO Autogenerated
	}
}
