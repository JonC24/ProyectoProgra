module ProyectoProgra {
	requires javafx.controls;
	
	opens business to javafx.graphics, javafx.fxml;
	
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
    opens domain;

}
