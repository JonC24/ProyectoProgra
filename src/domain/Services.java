package domain;

public class Services {
	
	private String CodeOfService, NameOfService, Description;
	private double baseCost, estimatedTime;
	
	public Services(String codeOfService, String nameOfService, String description, double baseCost,
			double estimatedTime) {
		super();
		CodeOfService = codeOfService;
		NameOfService = nameOfService;
		Description = description;
		this.baseCost = baseCost;
		this.estimatedTime = estimatedTime;
	}

	public String getCodeOfService() {
		return CodeOfService;
	}

	public void setCodeOfService(String codeOfService) {
		CodeOfService = codeOfService;
	}

	public String getNameOfService() {
		return NameOfService;
	}

	public void setNameOfService(String nameOfService) {
		NameOfService = nameOfService;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}

	public double getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	@Override
	public String toString() {
		return "Services [CodeOfService=" + CodeOfService + ", NameOfService=" + NameOfService + ", Description="
				+ Description + ", baseCost=" + baseCost + ", estimatedTime=" + estimatedTime + "]";
	}
	
}
