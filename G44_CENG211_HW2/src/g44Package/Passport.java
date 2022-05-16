package g44Package;

import java.time.LocalDate;

public class Passport {
	private String applicantID;
	private String passportNumber;
	private LocalDate expirationDate;
	
	//*******************************************//
	//				CONSTRUCTORS		  		 //
	//*******************************************//
	public Passport() {
		applicantID = "empty";
		passportNumber = "empty";
		expirationDate = LocalDate.of(1, 1, 1);
	}
	
	public Passport(String applicantID, String passportNumber, LocalDate expirationDate) {
		this.applicantID = applicantID;
		this.passportNumber = passportNumber;
		this.expirationDate = LocalDate.of(expirationDate.getYear(),
										   expirationDate.getMonth(),
										   expirationDate.getDayOfMonth());
	}


	
	//*******************************************//
	//		METHODS INHERITED FROM OBJECT  		 //
	//*******************************************//
	
	public Passport clone() {
		return new Passport(getApplicantID(),getPassportNumber(),getExpirationDate());
	}
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		} else if (otherObject.getClass()!= this.getClass()){
			return false;
		} else {
			Passport otherPassport = (Passport) otherObject;
			return applicantID.equals(otherPassport.getApplicantID()) &&
				   passportNumber.equals(otherPassport.getPassportNumber()) &&
				   expirationDate.equals(otherPassport.getExpirationDate());
		}
	}
	
	public String toString() {
		return  applicantID + " | " + passportNumber + " | " + 
			    expirationDate.getDayOfMonth()+"/" + 
				expirationDate.getMonthValue()+ "/" +
				expirationDate.getYear();
	}

	//*******************************************//
	//			GETTERS AND SETTERS   			 //
	//*******************************************//
	
	public String getApplicantID() {	return applicantID;}
	public void setApplicantID(String applicantID) {	this.applicantID = applicantID;}

	public String getPassportNumber() {		return passportNumber;}	
	public void setPassportNumber(String passportNumber) {		this.passportNumber = passportNumber;}
	 
	public LocalDate getExpirationDate() {
		return LocalDate.of(expirationDate.getYear(),
				      		expirationDate.getMonth(),
				      		expirationDate.getDayOfMonth());}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = LocalDate.of(expirationDate.getYear(),
				   expirationDate.getMonth(),
				   expirationDate.getDayOfMonth());}	
}
