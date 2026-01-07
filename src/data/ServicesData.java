package data;

import java.util.ArrayList;

import domain.Services;

public class ServicesData {
	private static String filePath = "Servicios.json";

	private static JsonUtils<Services> jsonUtils = new JsonUtils<Services>(filePath);

	public ServicesData() {
	}

	public static void saveStudent(Services services) {
		

		try {
			jsonUtils.saveElement(services);
		} catch (Exception e) {
			System.out.println("Error al guardar ServicesData.Services");
			e.printStackTrace();
		}
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
}
