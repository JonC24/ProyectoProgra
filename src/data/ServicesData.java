package data;

import java.util.ArrayList;

import domain.Services;

public class ServicesData {
	private static String filePath = "Servicios.json";

	private static JsonUtils<Services> jsonUtils = new JsonUtils<Services>(filePath);

	public ServicesData() {
	}

	public static boolean save(Services services) {
		try {
			for(int i =0; i<getList().size(); i++) {
				if(getList().get(i).getCodeOfService().equalsIgnoreCase(services.getCodeOfService())){
					return false;
				}
			}
			jsonUtils.saveElement(services);
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al guardar clientsData.saveclient");
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<Services> getList() {
		try {
			return (ArrayList<Services>) jsonUtils.getAll(Services.class);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al recuperar lista de Servicios");
			return new ArrayList<Services>();
		}
	}
	
	public static void delete(Services services, String id) {
		try {
			for(int i =0; i<getList().size(); i++) {
				if(getList().get(i).getCodeOfService().equalsIgnoreCase(id)){
					jsonUtils.deleteElement(services, i);
				}
			}
		}catch(Exception e) {
			System.out.println("Error al borrar este elemento");
			e.printStackTrace();
		}
	}
}
