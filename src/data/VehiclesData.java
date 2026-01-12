package data;

import java.util.ArrayList;

import domain.Vehicles;

public class VehiclesData {
	private static String filePath = "Vehiculos.json";

	private static JsonUtils<Vehicles> jsonUtils = new JsonUtils<Vehicles>(filePath);

	public VehiclesData() {
	}

	public static boolean save(Vehicles vehicles) {
		try {
			for(int i =0; i<getList().size(); i++) {
				if(getList().get(i).getPlate().equalsIgnoreCase(vehicles.getPlate())){
					return false;
				}
			}
			jsonUtils.saveElement(vehicles);
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al guardar clientsData.saveclient");
			e.printStackTrace();
		}
		return false;
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
	
	public static void delete(Vehicles vehicles, String id) {
		try {
			for(int i =0; i<getList().size(); i++) {
				//System.out.println(getList().get(i).toString());
				if(getList().get(i).getPlate().equalsIgnoreCase(id)){
					jsonUtils.deleteElement(vehicles, i);
				}
			}
		}catch(Exception e) {
			System.out.println("Error al borrar este elemento");
			e.printStackTrace();
		}
	}
	
}
