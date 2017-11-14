package model;
import javafx.beans.property.SimpleStringProperty;

public class Patient {

	private SimpleStringProperty firstName, lastName, age, gender, height, weight, insulinType;
	
	public Patient(String firstName, String lastName, String age, String gender, String height, String weight, String insulinType) {
		this.firstName  = new SimpleStringProperty(firstName);
		this.lastName  = new SimpleStringProperty(lastName);
		this.age  = new SimpleStringProperty(age);
		this.gender  = new SimpleStringProperty(gender);
		this.height  = new SimpleStringProperty(height);
		this.weight  = new SimpleStringProperty(weight);
		this.insulinType  = new SimpleStringProperty(insulinType);
		
	}
	
	/* Start of GETTERS AND SETTERS */
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

	@Override
	public String toString() {
		return "Patient [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", gender=" + gender
				+ ", height=" + height + ", weight=" + weight + ", insulinType=" + insulinType + "]";
	}
	
	
}
