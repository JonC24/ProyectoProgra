package domain;

public class Clients {
	private int ID,Phone;
	private String Name, email, adress;
	
	public Clients(int iD, int phone, String name, String email, String adress) {
		super();
		ID = iD;
		Phone = phone;
		Name = name;
		this.email = email;
		this.adress = adress;
	}

	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public int getPhone() {
		return Phone;
	}


	public void setPhone(int phone) {
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
