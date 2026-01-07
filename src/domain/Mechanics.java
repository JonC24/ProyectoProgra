package domain;

public class Mechanics {

	private String ID, FullName, Speciality, email;
	private int phone;
	
	public Mechanics(String iD, String fullName, String speciality, String email, int phone) {
		super();
		ID = iD;
		FullName = fullName;
		Speciality = speciality;
		this.email = email;
		this.phone = phone;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getSpeciality() {
		return Speciality;
	}

	public void setSpeciality(String speciality) {
		Speciality = speciality;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Mechanics [ID=" + ID + ", FullName=" + FullName + ", Speciality=" + Speciality + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
}
