package data;

import java.util.ArrayList;

import domain.Clients;
import domain.Vehicles;
import domain.WorkOrders;

public class WorkOrdersData {
	private static String filePath = "Ordenes de trabajo.json";

	private static JsonUtils<WorkOrders> jsonUtils = new JsonUtils<WorkOrders>(filePath);

	public WorkOrdersData() {
	}

	public static void save(WorkOrders workOrders) {
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
	public static void delete(WorkOrders workOrders, String id) {
		try {
			for(int i =0; i<getList().size(); i++) {
				if(getList().get(i).getNumOfOrder().equalsIgnoreCase(id)){
					jsonUtils.deleteElement(workOrders, i);
				}
			}
		}catch(Exception e) {
			System.out.println("Error al borrar este elemento");
			e.printStackTrace();
		}
	}
	
	public static void edit(WorkOrders workOrders, String id) {
		try {
			for(int i =0; i<getList().size(); i++) {
				//System.out.println(getList().get(i).toString());
				if(getList().get(i).getNumOfOrder().equalsIgnoreCase(id)){
					getList().set(i, workOrders);					
				}
			}
		}catch(Exception e) {
			System.out.println("Error al editar este elemento");
			e.printStackTrace();
		}
	}
}
