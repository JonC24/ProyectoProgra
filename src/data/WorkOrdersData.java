package data;

import java.util.ArrayList;

import domain.WorkOrders;

public class WorkOrdersData {
	private static String filePath = "Ordenes de trabajo.json";

	private static JsonUtils<WorkOrders> jsonUtils = new JsonUtils<WorkOrders>(filePath);

	public WorkOrdersData() {
	}

	public static void saveStudent(WorkOrders workOrders) {
		

		try {
			jsonUtils.saveElement(workOrders);
		} catch (Exception e) {
			System.out.println("Error al guardar WorkOrdersData.saveWorkOrder");
			e.printStackTrace();
		}
	}

	public static ArrayList<WorkOrders> getList() {
		try {
			return (ArrayList<WorkOrders>) jsonUtils.getAll(WorkOrders.class);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al recuperar lista de Ordenes de trabajo");
			return new ArrayList<WorkOrders>();
		}
	}
}
