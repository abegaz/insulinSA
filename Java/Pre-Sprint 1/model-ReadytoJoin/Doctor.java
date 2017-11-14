package model;
import javafx.beans.property.SimpleStringProperty;

public class Doctor {

	private SimpleStringProperty firstName, middleInitial, lastName, phone;
	
	/*Constructor for Doctor*/
	public Doctor(String firstName, String middleInitial, String lastName, String phone) {
		this.firstName = new SimpleStringProperty(firstName);
		this.middleInitial = new SimpleStringProperty(middleInitial);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone = new SimpleStringProperty(phone);
	}
	
	/* Start of GETTERS AND SETTERS */
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	public String getMiddleInitial() {
		return middleInitial.get();
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = new SimpleStringProperty(middleInitial);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}
	/*End for GETTERS AND SETTERS*/
	@Override
	public String toString() {
		return "Doctor [firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName=" + lastName + "]";
	}

}
