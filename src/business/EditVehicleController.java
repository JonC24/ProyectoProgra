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
import data.VehiclesData;
import domain.Clients;
import domain.Vehicles;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

public class EditVehicleController {
    @FXML private Label lbPlate;
    @FXML private TextField tfPlate;
    @FXML private Label lbBrand;
    @FXML private TextField tfBrand;
    @FXML private Label lbModel;
    @FXML private TextField tfModel;
    @FXML private Label lbYear;
    @FXML private TextField tfYear;
    @FXML private Label lbFuelType;
    
    @FXML private Button btnEditVehicle; 
    @FXML private Button btnCancel;
    
    @FXML private ComboBox<String> cbxFuelType;
    @FXML private Label lbOwner;
    @FXML private ComboBox<Clients> cbxOwner;

    private Vehicles originalVehicle;

    @FXML
    public void initialize() {
        initFuelComboBox();
        initClientsComboBox();
    }

    private void initFuelComboBox() {
        cbxFuelType.getItems().addAll("", "Gasolina", "Diesel", "Electrico", "Hibrido");
    }

    private void initClientsComboBox() {
        cbxOwner.getItems().setAll(ClientsData.getList());

        cbxOwner.setConverter(new StringConverter<Clients>() {
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

    public void initData(Vehicles vehicle) {
        this.originalVehicle = vehicle;

        tfPlate.setText(vehicle.getPlate());
        tfBrand.setText(vehicle.getBrand());
        tfModel.setText(vehicle.getModel());
        tfYear.setText(vehicle.getYear());

        cbxFuelType.setValue(vehicle.getFuelType());

        String ownerId = vehicle.getPropertyOwner();
        
        for (Clients client : cbxOwner.getItems()) {
            if (client.getID().equalsIgnoreCase(ownerId)) {
                cbxOwner.setValue(client);
                break; 
            }
        }

        tfPlate.setDisable(true);
    }

    @FXML
    public void SaveVehicle(ActionEvent event) {
        if (validForm()) {
            String plate = tfPlate.getText().trim(); 
            String brand = tfBrand.getText().trim();
            String model = tfModel.getText().trim();
            String year = tfYear.getText().trim();
            String fuelType = cbxFuelType.getValue();
            
            String ownerID = cbxOwner.getValue().getID();

            Vehicles updatedVehicle = new Vehicles(plate, brand, model, ownerID, year, fuelType);

            if (VehiclesData.edit(updatedVehicle, originalVehicle.getPlate())) {
                showAlert(AlertType.INFORMATION, "Actualización Exitosa", "La información del vehículo ha sido actualizada.");
                Cancel(event);
            } else {
                showAlert(AlertType.ERROR, "Error al Actualizar", "No se pudo actualizar el vehículo.");
            }
        }
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
            message += "Año vacío \n";
        } else {
            try {
                Integer.parseInt(tfYear.getText().trim());
                tfYear.setStyle("");
            } catch (NumberFormatException e) {
                message += "El año debe ser un número válido \n";
                tfYear.setStyle("-fx-border-color:red; -fx-border-width:2;");
            }
        }

        if (cbxFuelType.getValue() == null || cbxFuelType.getValue().isEmpty()) {
            cbxFuelType.setStyle("-fx-border-color:red; -fx-border-width:2;");
            message += "Combustible no seleccionado \n";
        } else {
            cbxFuelType.setStyle("");
        }

        if (cbxOwner.getValue() == null) {
            cbxOwner.setStyle("-fx-border-color:red; -fx-border-width:2;");
            message += "Dueño no seleccionado \n";
        } else {
            cbxOwner.setStyle("");
        }

        if (!message.isEmpty()) {
            showAlert(AlertType.WARNING, "Campos Inválidos", message);
        }

        return message.isEmpty();
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    public void Cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GUIMantenimientoDeVehiculos.fxml"));
            Parent root = loader.load();
            Scene scene = btnCancel.getScene();
            scene.setRoot(root);
            scene.getWindow().sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ChooseOwner(ActionEvent event) {
        // Auto-generated
    }

    // ESTE ES EL MÉTODO QUE FALTABA PARA SOLUCIONAR EL ERROR
    @FXML
    public void ChooseFuelType(ActionEvent event) {
        // Auto-generated
    }
}