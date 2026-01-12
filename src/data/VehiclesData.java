package data;

import java.util.ArrayList;

import domain.Clients;
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
	
    public static boolean edit(Vehicles vehicles, String originalPlate) {
        try {
            ArrayList<Vehicles> list = getList();
            for(int i = 0; i < list.size(); i++) {
                if(list.get(i).getPlate().equalsIgnoreCase(originalPlate)){
                    
                    jsonUtils.deleteElement(list.get(i), i);
                    jsonUtils.saveElement(vehicles);
                    
                    return true; 
                }
            }
        } catch(Exception e) {
            System.out.println("Error al editar este elemento");
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
	
	public static Vehicles search(Vehicles vehicles, String id) {
		try {
			for(int i =0; i<getList().size(); i++) {
				if(getList().get(i).getPlate().equalsIgnoreCase(id)){
					System.out.println(getList().get(i).toString());
					return getList().get(i);
				}
			}
		}catch(Exception e) {
			System.out.println("Error al buscar este elemento");
			e.printStackTrace();
		}
		return null;
	}
	
}
