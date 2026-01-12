package data;

import java.util.ArrayList;

import domain.Mechanics;

public class MechanicsData {
	private static String filePath = "Mecanicos.json";

	private static JsonUtils<Mechanics> jsonUtils = new JsonUtils<Mechanics>(filePath);

	public MechanicsData() {
	}

	public static boolean save(Mechanics mechanics) {
		try {
			for(int i =0; i<getList().size(); i++) {
				if(getList().get(i).getID().equalsIgnoreCase(mechanics.getID())){
					return false;
				}
			}
			jsonUtils.saveElement(mechanics);
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al guardar clientsData.saveclient");
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<Mechanics> getList() {
		try {
			return (ArrayList<Mechanics>) jsonUtils.getAll(Mechanics.class);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al recuperar lista de mecanico");
			return new ArrayList<Mechanics>();
		}
	}
	
	public static void delete(Mechanics mechanics, String id) {
		try {
			for(int i =0; i<getList().size(); i++) {
				//System.out.println(getList().get(i).toString());
				if(getList().get(i).getID().equalsIgnoreCase(id)){
					jsonUtils.deleteElement(mechanics, i);
				}
			}
		}catch(Exception e) {
			System.out.println("Error al borrar este elemento");
			e.printStackTrace();
		}
	}
}
