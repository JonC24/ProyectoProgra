package domain;

public class Clients {
	private String Name, email, adress,ID,Phone;
	
	public Clients(String iD, String phone, String name, String email, String adress) {
		super();
		ID = iD;
		Phone = phone;
		Name = name;
		this.email = email;
		this.adress = adress;
	}

	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getPhone() {
		return Phone;
	}


	public void setPhone(String phone) {
		Phone = phone;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	@Override
	public String toString() {
		return "Clients [ID=" + ID + ", Phone=" + Phone + ", Name=" + Name + ", email=" + email + ", adress=" + adress
				+ "]";
	}
	
}
