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

import data.ClientsData;
import domain.Clients;

public class GUIMantenimientoDeClientesController {

    @FXML private Button btnNewClient;
    @FXML private Button btnEditClient;
    @FXML private Button btnDeleteClient;
    @FXML private TextField tfSearch;
    @FXML private Button btnCancelar;

    @FXML private TableView<Clients> tvClients;
    @FXML private TableColumn<Clients, String> colID;
    @FXML private TableColumn<Clients, String> colName;
    @FXML private TableColumn<Clients, String> colPhone;
    @FXML private TableColumn<Clients, String> colEmail;
    @FXML private TableColumn<Clients, String> colAddress;

    private ObservableList<Clients> clientList;
    private FilteredList<Clients> filteredData;

    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("adress")); 

        refreshTable();
    }

    public void refreshTable() {
        clientList = FXCollections.observableArrayList(ClientsData.getList());
        filteredData = new FilteredList<>(clientList, p -> true);
        
        tvClients.setItems(clientList);
        tvClients.refresh();
    }

    @FXML
    public void CreateClient(ActionEvent event) {
        loadView("/gui/CreateClient.fxml", btnNewClient);
    }

    @FXML
    public void EditClient(ActionEvent event) {
        Clients selected = tvClients.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(AlertType.WARNING, "Selección requerida", "Selecciona un cliente para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EditClient.fxml"));
            Parent root = loader.load();

            EditClientController controller = loader.getController();
            controller.initData(selected);

            Scene scene = btnEditClient.getScene();
            scene.setRoot(root);
            scene.getWindow().sizeToScene();
            scene.getWindow().centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void DeleteClient(ActionEvent event) {
        Clients selected = tvClients.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(AlertType.WARNING, "Selección requerida", "Selecciona un cliente para eliminar.");
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Eliminación");
        alert.setHeaderText("¿Eliminar a " + selected.getName() + "?");
        alert.setContentText("Esta acción es irreversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ClientsData.delete(selected, selected.getID());
            refreshTable(); 
        }
    }

    @FXML
    public void ViewTheOptions(KeyEvent event) {
        String filter = tfSearch.getText().toLowerCase();
        
        filteredData.setPredicate(client -> {
            if (filter == null || filter.isEmpty()) {
                return true;
            }
            if (client.getID().toLowerCase().contains(filter)) {
                return true; 
            } else if (client.getName().toLowerCase().contains(filter)) {
                return true;
            }
            return false; 
        });

        SortedList<Clients> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvClients.comparatorProperty());
        tvClients.setItems(sortedData);
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