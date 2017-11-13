package com.InsulinPump.model;
import javafx.beans.property.SimpleStringProperty;

public class Records {

	private SimpleStringProperty idRecords, dateTime, glucoseReading, insulinAmount, status, idPatient, idDoctor;
	
	public Records(String idRecords, String dateTime, String glucoseReading, String insulinAmount, String status, String idPatient, String idDoctor) {
		this.idRecords  = new SimpleStringProperty(idRecords);
		this.dateTime  = new SimpleStringProperty(dateTime);
		this.glucoseReading  = new SimpleStringProperty(glucoseReading);
		this.insulinAmount  = new SimpleStringProperty(insulinAmount);
		this.status  = new SimpleStringProperty(status);
		this.idPatient  = new SimpleStringProperty(idPatient);		
		this.idDoctor  = new SimpleStringProperty(idDoctor);		
	}
	
	/* Start of GETTERS AND SETTERS */
	public String getIdRecords() {
		return idRecords.get();
	}

	public void setIdRecords(String idRecords) {
		this.idRecords = new SimpleStringProperty(idRecords);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime) {
		this.dateTime = new SimpleStringProperty(dateTime);
	}

	public String getGlucoseReading() {
		return glucoseReading.get();
	}

	public void setglucoseReading(String glucoseReading) {
		this.glucoseReading = new SimpleStringProperty(glucoseReading);
	}
	
	public String getInsulinAmount() {
		return insulinAmount.get();
	}

	public void setInsulinAmount(String insulinAmount) {
		this.insulinAmount = new SimpleStringProperty(insulinAmount);
	}
	
	public String getStatus() {
		return status.get();
	}

	public void setStatus(String status) {
		this.status = new SimpleStringProperty(status);
	}

	public String getIdPatient() {
		return idPatient.get();
	}

	public void setIdPatient(String idPatient) {
		this.idPatient = new SimpleStringProperty(idPatient);
	}

	public String getIdDoctor() {
		return idDoctor.get();
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = new SimpleStringProperty(idDoctor);
	}

	@Override
	public String toString() {
		return "Patient [RecordsID=" + idRecords + ", DateTime=" + dateTime + ", GlucoseReading=" + glucoseReading + ", InsulinAmount=" + insulinAmount
				+ ", Status=" + status + ", PatientID=" + idPatient + ", DoctorID=" + idDoctor + "]";
	}
	
	
}
