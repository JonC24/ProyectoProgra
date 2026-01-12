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
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.Optional;

import data.ServicesData;
import domain.Services;

public class GUIMantenimientoDeServiciosController {
	@FXML
	private Button btnNewService;
	@FXML
	private Button btnEditService;
	@FXML
	private Button btnDeleteService;
	@FXML
	private TextField tfSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnCancelar;

	@FXML private TableView<Services> tvServices;
	@FXML private TableColumn<Services, String> colCode;
	@FXML private TableColumn<Services, String> colName;
	@FXML private TableColumn<Services, String> colDescription;
	@FXML private TableColumn<Services, Double> colBaseCost;
	@FXML private TableColumn<Services, Double> colEstimatedTime;

	private ObservableList<Services> serviceList;
	private FilteredList<Services> filteredData;

	@FXML
	public void initialize() {
		colCode.setCellValueFactory(new PropertyValueFactory<>("codeOfService"));
		colName.setCellValueFactory(new PropertyValueFactory<>("nameOfService"));
		colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		colBaseCost.setCellValueFactory(new PropertyValueFactory<>("baseCost"));
		colEstimatedTime.setCellValueFactory(new PropertyValueFactory<>("estimatedTime"));

		refreshTable();
	}

	public void refreshTable() {
		serviceList = FXCollections.observableArrayList(ServicesData.getList());
		filteredData = new FilteredList<>(serviceList, p -> true);

		tvServices.setItems(serviceList);
		tvServices.refresh();
	}

	// Event Listener on Button[#btnNewService].onAction
	@FXML
	public void CreateService(ActionEvent event) {
		loadView("/gui/CreateService.fxml", btnNewService);
	}

	// Event Listener on Button[#btnEditService].onAction
	@FXML
	public void EditService(ActionEvent event) {
		Services selected = tvServices.getSelectionModel().getSelectedItem();

		if (selected == null) {
			showAlert(AlertType.WARNING, "Selección requerida", "Selecciona un servicio para editar.");
			return;
		}

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EditService.fxml"));
			Parent root = loader.load();

			EditServiceController controller = loader.getController();
			controller.setServiceToEdit(selected);

			Scene scene = btnEditService.getScene();
			scene.setRoot(root);
			scene.getWindow().sizeToScene();
			scene.getWindow().centerOnScreen();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Event Listener on Button[#btnDeleteService].onAction
	@FXML
	public void DeleteService(ActionEvent event) {
		Services selected = tvServices.getSelectionModel().getSelectedItem();

		if (selected == null) {
			showAlert(AlertType.WARNING, "Selección requerida", "Selecciona un servicio para eliminar.");
			return;
		}

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmar Eliminación");
		alert.setHeaderText("¿Eliminar el servicio con código " + selected.getCodeOfService() + "?");
		alert.setContentText("Esta acción es irreversible.");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			ServicesData.delete(selected, selected.getCodeOfService());
			refreshTable();
		}
	}

	// Event Listener on TableView.onMouseClicked
	@FXML
	public void Delete(MouseEvent event) {
	}

	// Event Listener on TextField[#tfSearch].onKeyReleased
	@FXML
	public void ViewTheOptions(KeyEvent event) {
		String filter = tfSearch.getText();
		if (filter != null) filter = filter.toLowerCase();
		else filter = "";

		final String f = filter;

		filteredData.setPredicate(service -> {
			if (f == null || f.isEmpty()) {
				return true;
			}

			if (service.getCodeOfService() != null && service.getCodeOfService().toLowerCase().contains(f)) {
				return true;
			} else if (service.getNameOfService() != null && service.getNameOfService().toLowerCase().contains(f)) {
				return true;
			} else if (service.getDescription() != null && service.getDescription().toLowerCase().contains(f)) {
				return true;
			}
			try {
				if (String.valueOf(service.getBaseCost()).toLowerCase().contains(f)) return true;
				if (String.valueOf(service.getEstimatedTime()).toLowerCase().contains(f)) return true;
			} catch (Exception ex) {  }

			return false;
		});

		SortedList<Services> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tvServices.comparatorProperty());
		tvServices.setItems(sortedData);
	}

	// Event Listener on Button[#btnSearch].onAction
	@FXML
	public void Search(ActionEvent event) {
		// optional: keep to trigger same filter as typing
		ViewTheOptions(null);
	}

	// Event Listener on Button[#btnCancelar].onAction
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