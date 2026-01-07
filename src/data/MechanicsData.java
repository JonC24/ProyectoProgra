package data;

import java.util.ArrayList;

import domain.Mechanics;

public class MechanicsData {
	private static String filePath = "Mecanicos.json";

	private static JsonUtils<Mechanics> jsonUtils = new JsonUtils<Mechanics>(filePath);

	public MechanicsData() {
	}

	public static void saveStudent(Mechanics mechanics) {
		

		try {
			jsonUtils.saveElement(mechanics);
		} catch (Exception e) {
			System.out.println("Error al guardar MechanicsData.saveMechanic");
			e.printStackTrace();
		}
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
}
