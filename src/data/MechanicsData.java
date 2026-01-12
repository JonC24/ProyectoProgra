package data;

import java.util.ArrayList;

import domain.Clients;
import domain.Mechanics;

public class MechanicsData {
	private static String filePath = "Mecanicos.json";

	private static JsonUtils<Mechanics> jsonUtils = new JsonUtils<Mechanics>(filePath);

	public MechanicsData() {
	}

	public static boolean save(Mechanics mechanics) {
		try {
			mechanics.setID(automaticId());
			jsonUtils.saveElement(mechanics);
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al guardar clientsData.saveclient");
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean edit(Mechanics newClientData, String originalId) {
        try {
            ArrayList<Mechanics> currentList = getList();
            for(int i = 0; i < currentList.size(); i++) {
                if(currentList.get(i).getID().equalsIgnoreCase(originalId)){
                    
                    
                    jsonUtils.deleteElement(currentList.get(i), i);
                    jsonUtils.saveElement(newClientData);
                    
                    return true;
                }
            }
        } catch(Exception e) {
            System.out.println("Error al editar este elemento");
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
	
	public static Mechanics search(Mechanics mechanics, String id) {
		try {
			for(int i =0; i<getList().size(); i++) {
				if(getList().get(i).getID().equalsIgnoreCase(id)){
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
	
	public static String automaticId() {

	    if (getList() == null || getList().isEmpty()) {
	        return Integer.toString(1);
	    }
	    int maxId = 0;
	    for (Mechanics m : getList()) {
	        if (Integer.parseInt(m.getID())> maxId) {
	            maxId = Integer.parseInt(m.getID());
	        }
	    }

	    return Integer.toString(maxId + 1);
	}
	
}
