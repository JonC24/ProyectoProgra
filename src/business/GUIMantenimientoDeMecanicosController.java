package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

import data.ClientsData;
import data.MechanicsData;
import domain.Clients;
import domain.Mechanics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import javafx.scene.input.KeyEvent;

public class GUIMantenimientoDeMecanicosController {
	@FXML
	private Button btnNewMechanic;
	@FXML
	private Button btnEditMechanic;
	@FXML
	private Button btnDeleteMechanic;
	@FXML
	private TextField tfSearch;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableView<Mechanics> tvMechanics;

    @FXML private TableColumn<Mechanics, String> colID;
    @FXML private TableColumn<Mechanics, String> colName;
    @FXML private TableColumn<Mechanics, String> colPhone;
    @FXML private TableColumn<Mechanics, String> colEmail;
    @FXML private TableColumn<Mechanics, String> colSpeciality;
    
    private ObservableList<Mechanics> mechanicsList;
    private FilteredList<Mechanics> filteredData;



    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("FullName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality")); 

        refreshTable();
    }

    public void refreshTable() {
        mechanicsList = FXCollections.observableArrayList(MechanicsData.getList());
        filteredData = new FilteredList<>(mechanicsList, p -> true);
        
        tvMechanics.setItems(mechanicsList);
        tvMechanics.refresh();
    }
 // Event Listener on TextField[#tfSearch].onKeyReleased
 	@FXML
    public void ViewTheOptions(KeyEvent event) {
        String filter = tfSearch.getText().toLowerCase();
        
        filteredData.setPredicate(mechanic -> {
            if (filter == null || filter.isEmpty()) {
                return true;
            }
            if (mechanic.getID().toLowerCase().contains(filter)) {
                return true; 
            } else if (mechanic.getFullName().toLowerCase().contains(filter)) {
                return true;
            }
            return false; 
        });

        SortedList<Mechanics> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvMechanics.comparatorProperty());
        tvMechanics.setItems(sortedData);
    }

    
	// Event Listener on Button[#btnNewMechanic].onAction
	@FXML
	public void CreateMechanic(ActionEvent event) {
		loadView("/gui/CreateMechanic.fxml", btnNewMechanic);
	}
	
	private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }
	// Event Listener on Button[#btnEditMechanic].onAction
	@FXML
	public void EditMechanic(ActionEvent event) {
        Mechanics selected = tvMechanics.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(AlertType.WARNING, "Selección requerida", "Selecciona un mecanico para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EditMechanic.fxml"));
            Parent root = loader.load();

            EditMechanicController controller = loader.getController();
            controller.initData(selected);

            Scene scene = btnEditMechanic.getScene();
            scene.setRoot(root);
            scene.getWindow().sizeToScene();
            scene.getWindow().centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	// Event Listener on Button[#btnDeleteMechanic].onAction
	@FXML
	public void DeleteMechanic(ActionEvent event) {
		Mechanics selected = tvMechanics.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(AlertType.WARNING, "Selección requerida", "Selecciona un mecanico para eliminar.");
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Eliminación");
        alert.setHeaderText("¿Eliminar a " + selected.getFullName() + "?");
        alert.setContentText("Esta acción es irreversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            MechanicsData.delete(selected, selected.getID());
            refreshTable(); 
        }
	}
	// Event Listener on TableView.onMouseClicked
	
	
	
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void GoToIndex(ActionEvent event) {
		loadView("/gui/MenuGeneral.fxml", btnCancelar);
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
