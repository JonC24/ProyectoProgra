package domain;

import java.time.LocalDate;
import java.util.List;

public class WorkOrders {
	private String numOfOrder;
	private LocalDate orderDate;
	private String OrderStatus, observations;
	private double totalCost;
	
	private Vehicles vehicle;
	private Mechanics mechanic;
	private List<Services> appliedServices;
	
	public WorkOrders(String numOfOrder, LocalDate orderDate, String orderStatus, String observations, double totalCost,
			Vehicles vehicle, Mechanics mechanic, List<Services> appliedServices) {
		super();
		this.numOfOrder = numOfOrder;
		this.orderDate = orderDate;
		OrderStatus = orderStatus;
		this.observations = observations;
		this.totalCost = totalCost;
		this.vehicle = vehicle;
		this.mechanic = mechanic;
		this.appliedServices = appliedServices;
	}

	public String getNumOfOrder() {
		return numOfOrder;
	}

	public void setNumOfOrder(String numOfOrder) {
		this.numOfOrder = numOfOrder;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return OrderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Vehicles getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicles vehicle) {
		this.vehicle = vehicle;
	}

	public Mechanics getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanics mechanic) {
		this.mechanic = mechanic;
	}

	public List<Services> getAppliedServices() {
		return appliedServices;
	}

	public void setAppliedServices(List<Services> appliedServices) {
		this.appliedServices = appliedServices;
	}

	@Override
	public String toString() {
		return "WorkOrders [numOfOrder=" + numOfOrder + ", orderDate=" + orderDate + ", OrderStatus=" + OrderStatus
				+ ", observations=" + observations + ", totalCost=" + totalCost + ", vehicle=" + vehicle + ", mechanic="
				+ mechanic + ", appliedServices=" + appliedServices + "]";
	}
	
}
