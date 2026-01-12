package data;

import java.util.ArrayList;

import domain.Clients;
import domain.Mechanics;
//main
public class ClientsData {
	
	private static String filePath = "Clients.json";

	private static JsonUtils<Clients> jsonUtils = new JsonUtils<Clients>(filePath);

	public ClientsData() {
	}

	public static boolean save(Clients clients) {
		try {
			for(int i =0; i<getList().size(); i++) {
				if(getList().get(i).getID().equalsIgnoreCase(clients.getID())){
					return false;
				}
			}
			jsonUtils.saveElement(clients);
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al guardar clientsData.saveclient");
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<Clients> getList() {
		try {
			return (ArrayList<Clients>) jsonUtils.getAll(Clients.class);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al recuperar lista de clientes");
			return new ArrayList<Clients>();
		}
	}
	
	public static void delete(Clients clients, String id) {
		try {
			for(int i =0; i<getList().size(); i++) {
				//System.out.println(getList().get(i).toString());
				if(getList().get(i).getID().equalsIgnoreCase(id)){
					jsonUtils.deleteElement(clients, i);
				}
			}
		}catch(Exception e) {
			System.out.println("Error al borrar este elemento");
			e.printStackTrace();
		}
	}
	
	public static Clients search(Clients clients, String id) {
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
	
	public static boolean edit(Clients client) {
		return true;
	}
	
	
}
