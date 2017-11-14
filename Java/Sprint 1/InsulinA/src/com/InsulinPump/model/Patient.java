package com.InsulinPump.model;
import javafx.beans.property.SimpleStringProperty;

public class Patient {

	private SimpleStringProperty idPatient, firstName, lastName, address, bloodType, age, gender, height, weight, insulinType, idDoctor, phone;
	
	public Patient(String idPatient, String firstName, String lastName, String address, String bloodType, String age, String gender, String height, String weight, String insulinType, String phone, String idDoctor ) {
		this.idPatient  = new SimpleStringProperty(idPatient);
		this.firstName  = new SimpleStringProperty(firstName);
		this.lastName  = new SimpleStringProperty(lastName);
		this.address  = new SimpleStringProperty(address);
		this.bloodType  = new SimpleStringProperty(bloodType);
		this.age  = new SimpleStringProperty(age);
		this.gender  = new SimpleStringProperty(gender);
		this.height  = new SimpleStringProperty(height);
		this.weight  = new SimpleStringProperty(weight);
		this.insulinType  = new SimpleStringProperty(insulinType);
		this.phone  = new SimpleStringProperty(phone);
		this.idDoctor  = new SimpleStringProperty(idDoctor);
		
	}
	
	public Patient(String idPatient) {
		this.idPatient = new SimpleStringProperty(idPatient);
	}
	
	/* Start of GETTERS AND SETTERS */
	public String getidPatient() {
		return idPatient.get();
	}

	public void setidPatient(String idPatient) {
		this.idPatient = new SimpleStringProperty(idPatient);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
	
	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}
	
	public String getBloodType() {
		return bloodType.get();
	}

	public void setBloodType(String bloodType) {
		this.bloodType = new SimpleStringProperty(bloodType);
	}

	public String getAge() {
		return age.get();
	}

	public void setAge(String age) {
		this.age = new SimpleStringProperty(age);
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender = new SimpleStringProperty(gender);
	}

	public String getHeight() {
		return height.get();
	}

	public void setHeight(String height) {
		this.height = new SimpleStringProperty(height);
	}

	public String getWeight() {
		return weight.get();
	}

	public void setWeight(String weight) {
		this.weight = new SimpleStringProperty(weight);
	}

	public String getInsulinType() {
		return insulinType.get();
	}

	public void setInsulinType(String insulinType) {
		this.insulinType = new SimpleStringProperty(insulinType);
	}
	
	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}
	
	public String getidDoctor() {
		return idDoctor.get();
	}

	public void setidDoctor(String idDoctor) {
		this.idDoctor = new SimpleStringProperty(idDoctor);
	}


	@Override
	public String toString() {
		return  "Patient [firstName=" + idPatient + firstName + ", lastName=" + lastName + ", age=" + age + ", gender=" + gender
				+ ", height=" + height + ", weight=" + weight + ", insulinType=" + insulinType + "]";
	}
	
	
}
