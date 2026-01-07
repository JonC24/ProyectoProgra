package data;

import java.util.ArrayList;

import domain.Clients;

public class ClientsData {
	
	private static String filePath = "Clients.json";

	private static JsonUtils<Clients> jsonUtils = new JsonUtils<Clients>(filePath);

	public ClientsData() {
	}

	public static void saveStudent(Clients clients) {
		

		try {
			jsonUtils.saveElement(clients);
		} catch (Exception e) {
			System.out.println("Error al guardar clientsData.saveclient");
			e.printStackTrace();
		}
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
}
