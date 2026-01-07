package data;

import java.util.ArrayList;

import domain.Vehicles;

public class VehiclesData {
	private static String filePath = "Vehiculos.json";

	private static JsonUtils<Vehicles> jsonUtils = new JsonUtils<Vehicles>(filePath);

	public VehiclesData() {
	}

	public static void saveStudent(Vehicles vehicles) {
		

		try {
			jsonUtils.saveElement(vehicles);
		} catch (Exception e) {
			System.out.println("Error al guardar VehiclesData.saveVehicles");
			e.printStackTrace();
		}
	}

	public static ArrayList<Vehicles> getList() {
		try {
			return (ArrayList<Vehicles>) jsonUtils.getAll(Vehicles.class);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al recuperar lista de vehiculos");
			return new ArrayList<Vehicles>();
		}
	}
}
