package business; // 1. Se mantiene en business

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label; // Importante para el Label
import java.io.IOException;

public class MenuGeneralController {

    // 2. Agregado el Label que pediste
    @FXML
    private Label lbStarUpMenu;

    @FXML
    private ComboBox<String> cbxChooseMenu;

    @FXML
    public void initialize() {
        cbxChooseMenu.getItems().addAll("", "Clientes","Vehiculos", "Mecanicos", "Servicios", "Ordenes de trabajo");
    }

    @FXML
    public void ChooseMenu(ActionEvent event) {
        if (!validForm()) return;

        String opcion = cbxChooseMenu.getValue();
        String fxmlDestino = "";

        switch (opcion) {
            case "Clientes":
                fxmlDestino = "/gui/GUIMantenimientoDeClientes.fxml";
                break;
            case "Vehiculos":
                fxmlDestino = "/gui/GUIMantenimientoDeVehiculos.fxml";
                break;
            case "Mecanicos":
                fxmlDestino = "/gui/GUIMantenimientoDeMecanicos.fxml";
                break;
            case "Servicios":
                fxmlDestino = "/gui/GUIMantenimientoDeServicios.fxml";
                break;
            case "Ordenes de trabajo":
            	fxmlDestino = "/gui/GUIMantenimientoDeOrdenes.fxml";
            	break;
        }

        if (!fxmlDestino.isEmpty()) {
            changeView(fxmlDestino);
        }
    }

    private void changeView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent nuevaVista = loader.load();

            
            Scene escenaActual = cbxChooseMenu.getScene();
            
            
            escenaActual.setRoot(nuevaVista);
            
            escenaActual.getWindow().sizeToScene();


        } catch (IOException e) {
            System.err.println("Error fatal: No se encuentra el archivo FXML en: " + fxml);
            e.printStackTrace();
        }
    }

    public boolean validForm() {
        if (cbxChooseMenu.getValue() == null || cbxChooseMenu.getValue().isEmpty()) {
            cbxChooseMenu.setStyle("-fx-border-color:red; -fx-border-width:2;");
            return false;
        }
        cbxChooseMenu.setStyle("");
        return true;
    }
}