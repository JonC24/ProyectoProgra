package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Optional;

import data.VehiclesData;
import domain.Vehicles;

public class GUIMantenimientoDeVehiculosController {

    @FXML private Button btnNewVehicle;
    @FXML private Button btnEditVehicle; 
    @FXML private Button btnDeleteVehicle;
    @FXML private TextField tfSearch;
    @FXML private Button btnCancelar;

    @FXML private TableView<Vehicles> tvVehicles;
    @FXML private TableColumn<Vehicles, String> colPlate;
    @FXML private TableColumn<Vehicles, String> colBrand;
    @FXML private TableColumn<Vehicles, String> colModel;
    @FXML private TableColumn<Vehicles, String> colOwner; 
    @FXML private TableColumn<Vehicles, String> colYear;
    @FXML private TableColumn<Vehicles, String> colFuel;

    private ObservableList<Vehicles> vehicleList;
    private FilteredList<Vehicles> filteredData;

    @FXML
    public void initialize() {
        colPlate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colOwner.setCellValueFactory(new PropertyValueFactory<>("propertyOwner"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colFuel.setCellValueFactory(new PropertyValueFactory<>("fuelType"));

        refreshTable();
    }

    public void refreshTable() {
        vehicleList = FXCollections.observableArrayList(VehiclesData.getList());
        filteredData = new FilteredList<>(vehicleList, p -> true);
        
        tvVehicles.setItems(vehicleList);
        tvVehicles.refresh();
    }

    @FXML
    public void CreateVehicle(ActionEvent event) {
        loadView("/gui/CreateVehicle.fxml", btnNewVehicle);
    }

    @FXML
    public void EditVehicle(ActionEvent event) {
        Vehicles selected = tvVehicles.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(AlertType.WARNING, "Selección requerida", "Selecciona un vehículo para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EditVehicle.fxml"));
            Parent root = loader.load();

            EditVehicleController controller = loader.getController();
            controller.initData(selected);

            Scene scene = btnEditVehicle.getScene(); 
            scene.setRoot(root);
            scene.getWindow().sizeToScene();
            scene.getWindow().centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void DeleteVehicle(ActionEvent event) {
        Vehicles selected = tvVehicles.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(AlertType.WARNING, "Selección requerida", "Selecciona un vehículo para eliminar.");
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Eliminación");
        alert.setHeaderText("¿Eliminar el vehículo placa " + selected.getPlate() + "?");
        alert.setContentText("Esta acción es irreversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            VehiclesData.delete(selected, selected.getPlate());
            refreshTable(); 
        }
    }

    @FXML
    public void ViewTheOptions(KeyEvent event) {
        String filter = tfSearch.getText().toLowerCase();
        
        filteredData.setPredicate(vehicle -> {
            if (filter == null || filter.isEmpty()) {
                return true;
            }

            if (vehicle.getPlate().toLowerCase().contains(filter)) {
                return true; 
            } else if (vehicle.getBrand().toLowerCase().contains(filter)) {
                return true;
            } else if (vehicle.getModel().toLowerCase().contains(filter)) {
                return true;
            }
            return false; 
        });

        SortedList<Vehicles> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvVehicles.comparatorProperty());
        tvVehicles.setItems(sortedData);
    }

    @FXML
    public void GoToIndex(ActionEvent event) {
        loadView("/gui/MenuGeneral.fxml", btnCancelar);
    }
    
    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }
    
    private void loadView(String fxmlPath, Button triggerButton) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = triggerButton.getScene();
            scene.setRoot(root);
            scene.getWindow().sizeToScene();
            scene.getWindow().centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
}
