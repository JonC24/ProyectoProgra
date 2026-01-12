package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import data.ClientsData;
import data.ServicesData;
import data.WorkOrdersData;
import domain.Clients;
import domain.Mechanics;
import domain.Services;
import domain.Vehicles;
import domain.WorkOrders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;

public class CreateOrdersController {
	@FXML
	private Label lbNumOfOrder;
	@FXML
	private TextField tfNumOfOrder;
	@FXML
	private Label lbNameOfService;
	@FXML
	private TextField tfNameOrder;
	@FXML
	private Label lbStateOfOrder;
	@FXML
	private Label lbVehicle;
	@FXML
	private Label lbClient;
	@FXML
	private Button btnSaveOrder;
	@FXML
	private Button btnCancel;
	@FXML
	private Label lbAsignatedMechanic;
	@FXML
	private ComboBox cbxStateOfOrder;
	@FXML
	private ComboBox<Vehicles> cbxVehicle;
	@FXML
	private ComboBox<Clients> cbxClient;
	@FXML
	private ComboBox<Mechanics> cbxAsignatedMech;
	@FXML
	private ComboBox<Services> cbxAppliedServ;
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
	
	private ObservableList<Services> selectedServicesList;
	private ObservableList<Services> obserServices;
	private ObservableList<Vehicles> observ;
	private ObservableList<Clients> obserc;
	private ObservableList<Mechanics> obserMechanics;


	@FXML
	private ListView<Services> lvSelectedServices;

	// 2. En el initialize, vincula la lista con el ListView
	@FXML
	public void initialize() {
		cbxStateOfOrder.getItems().addAll("Registrada", "En proceso", "Completada", "Entregada", "Cancelada");
		cbxVehicle.setItems(observ);
		cbxClient.setItems(obserc);
		cbxAsignatedMech.setItems(obserMechanics);
		
		obserServices= FXCollections.observableArrayList(ServicesData.getList());
		selectedServicesList= FXCollections.observableArrayList(ServicesData.getList());
	    lvSelectedServices.setItems(selectedServicesList);
	    cbxAppliedServ.setItems(obserServices);
	    
	    
	    
	    
	    
	}
	
	@FXML
	public void AddServiceToList(ActionEvent event) {

	    Services selected = cbxAppliedServ.getValue();
	    if (selected != null) {
	        // Añadir a la lista
	        selectedServicesList.add(selected);
	        
	        // REGLA DE NEGOCIO: Actualizar el costo total automáticamente
	        updateTotalCost();
	    } else {
	        // Mostrar alerta de que debe seleccionar un servicio primero
	    }
	}
	
	private void updateTotalCost() {
	    double total = 0;
	    for (Services s : selectedServicesList) {
	        total += s.getBaseCost(); // Asumiendo que tu clase Services tiene getBaseCost()
	    }
	    tfTotalCost.setText(String.valueOf(total));
	}

	
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
		private void initClientsComboBox() {
			cbxClient.getItems().setAll(ClientsData.getList());

	        cbxClient.setConverter(new StringConverter<Clients>() {
	            @Override
	            public String toString(Clients client) {
	                if (client == null) return "";
	                return client.getName() + " (" + client.getID() + ")";
	            }

	            @Override
	            public Clients fromString(String string) {
	                return null; 
	            }
	        });
	    }

		// Event Listener on Button[#btnSaveOrder].onAction
		@FXML
		public void SaveOrder(ActionEvent event) { if (validForm()) {
	        try {
	            // 1. Extraer datos de los campos simples
	            String numOrder = tfNumOfOrder.getText().trim();
	            LocalDate date = LocalDate.now(); // Fecha actual
	            String state = cbxStateOfOrder.getValue().toString();
	            String observations = tfObservations.getText().trim();
	            
	            // Convertir el costo total a double
	            double totalCost = 0.0;
	            if (!tfTotalCost.getText().isEmpty()) {
	                totalCost = Double.parseDouble(tfTotalCost.getText());
	            }

	            // 2. Extraer objetos de los ComboBox
	            // Asumimos que los ComboBox están cargados con los objetos correspondientes
	            Vehicles selectedVehicle = cbxVehicle.getValue();
	            Mechanics selectedMechanic = cbxAsignatedMech.getValue();
	            
	            // 3. Manejo de la lista de servicios
	            // Como el FXML solo permite elegir uno en el combo, creamos una lista con ese servicio
	            List<Services> servicesList = new ArrayList<>();
	            if (cbxAppliedServ.getValue() != null) {
	                servicesList.add(cbxAppliedServ.getValue());
	            }

	            // 4. Crear la instancia de WorkOrders
	            WorkOrders newOrder = new WorkOrders(
	                numOrder, 
	                date, 
	                state, 
	                observations, 
	                totalCost, 
	                selectedVehicle, 
	                selectedMechanic, 
	                servicesList
	            );

	            // 5. Guardar usando la clase de persistencia (siguiendo tu lógica de ClientsData)
	            // Asumo que tienes una clase WorkOrdersData con un método save
	            if (WorkOrdersData.save(newOrder)) {
	                Alert alert = new Alert(AlertType.INFORMATION);
	                alert.setHeaderText("Registro Exitoso");
	                alert.setTitle("Validación");
	                alert.setContentText("La orden de trabajo #" + numOrder + " se guardó correctamente.");
	                alert.show();
	                
	                
	            } else {
	                Alert alert = new Alert(AlertType.ERROR);
	                alert.setHeaderText("Error al Guardar");
	                alert.setTitle("Validación");
	                alert.setContentText("No se pudo guardar la orden. \nVerifique si el número de orden ya existe.");
	                alert.show();
	            }

	        } catch (NumberFormatException e) {
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setHeaderText("Error de Formato");
	            alert.setContentText("El costo total debe ser un número válido.");
	            alert.show();
	        } catch (Exception e) {
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setHeaderText("Error Inesperado");
	            alert.setContentText("Ocurrió un error: " + e.getMessage());
	            alert.show();
	        }
	    }
	    // restartWindow(); // Opcional: si quieres cerrar o reiniciar la ventana
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
