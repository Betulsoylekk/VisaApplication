package g44Package;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
public abstract class Application implements IApplication {
	private ApplicantInfo applicantInfo;
	private Passport passport;
	private Photo photo;
	private FinancialStatus financialStatus;
	private ArrayList<Document> documents;
	private String error;
	
	//*******************************************//
	//				CONSTRUCTORS		  		 //
	//*******************************************//
	
	public Application() {
		applicantInfo = new ApplicantInfo();
		passport = new Passport();
		photo = new Photo();
		financialStatus = new FinancialStatus();
		documents = new ArrayList<Document>();
		error = "empty";
	}
	
	@SuppressWarnings("unchecked")
	public Application(ApplicantInfo applicantInfo,
				     Passport passport,
				     Photo photo,
				     FinancialStatus financialStatus,
				     ArrayList<Document> documents) {
		this.applicantInfo = applicantInfo.clone();
		this.passport = passport.clone();
		this.photo = photo.clone();
		this.financialStatus = financialStatus.clone();
		this.documents = (ArrayList<Document>) documents.clone();
	}
	
	//*******************************************//
	//				PUBLIC METHODS			  	 //
	//*******************************************//
	
	public void checkPhotoValidation() {
		if (!photo.equals(new Photo())) {
			if (!(photo.getPosition().equals("Neutral Face") ||
					photo.getPosition().equals("Natural Smile"))) {
				error = "Position in the photo is not valid";
			} else if (!photo.getResulation().isResulationValid()) {
				error = "Resulation of photo is not valid";
			}
		} else {
			error = "Applicant doesn't have a photo";
		}
	}
	
	public void checkPassportValidation() {
		if (passport.equals(new Passport())) {
			error = "Applicant doesn't have a passport.";
		}else {
			String PN = passport.getPassportNumber();
			String last3CharOfPN = PN.substring(PN.length() - 3, PN.length());
			int monthDifference = Period.between(LocalDate.now(),passport.getExpirationDate()).getYears()*12 + 
							      Period.between(LocalDate.now(),passport.getExpirationDate()).getMonths();
			if (monthDifference < 6) {
				error = "Passport expiration date is not valid!";
			}
			if ( !(PN.length() == 10 && PN.startsWith("P") && isNumeric(last3CharOfPN))) {
				error = "Passport is not valid!";
			} 
		}
	}
	
	public boolean isFinancialStatusStable( int minIncome,
											int level1MaxIncome,
											int level2MaxIncome,
											int level1MinSavings,
											int level2MinSavings) {
		int tempIncome = financialStatus.getIncome();
		int tempSavings = financialStatus.getSavings();
		if (tempIncome >= minIncome && tempIncome < level1MaxIncome) {
			return tempSavings >=level1MinSavings;
		} else if (tempIncome >= level1MaxIncome && tempIncome <=level2MaxIncome) {
			return tempSavings >= level2MinSavings;
		} else if (tempIncome > level2MaxIncome){
			return true; // not necessary
		} else {
			return false;
		}
	}
	
	public boolean isHaveDocumentType(String documentType) {
		ArrayList<Document> tempDocuments = documents;
		if (tempDocuments.size() == 0) {
			return false;
		} else {
			for (Document i: tempDocuments){
				if (i.getDocumentType().equals(documentType)) {
					return true;
				}
			}
			return false;
		}
	}
	
	public void addDocument(Document document) {	
		documents.add(document.clone());
	}
	public String result() {
		String visa = calculateVisaDuration();
		if (error.equals("empty") && !visa.equals("rejected")) {
			return "Applicant ID: " + getApplicantInfo().getApplicantID() + ", " +
					"Name: " + getApplicantInfo().getApplicantName() + ", " + 
					"Visa Type: " +  findApplicationType(getApplicantInfo().getApplicantID()) + ", " + 
					"Status: Accepted" + ", " + "Visa Duration: " + visa; 
		}else {
			return "Applicant ID: " + getApplicantInfo().getApplicantID() + ", " +
					"Name: " + getApplicantInfo().getApplicantName() + ", " + 
					"Visa Type: " +  findApplicationType(getApplicantInfo().getApplicantID()) + ", " + 
					"Status: Rejected" + ", " + "Reason: " + getError(); 
		}
	}
	
	//*******************************************//
	//		METHODS INHERITED FROM OBJECT  		 //
	//*******************************************//
	
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		} else if (otherObject.getClass() != this.getClass()){
			return false;
		} else {
			Application otherApplication = (Application) otherObject;
			return (applicantInfo.equals(otherApplication.getApplicantInfo()) &&
					passport.equals(otherApplication.getPassport()) &&
					photo.equals(otherApplication.getPhoto()) &&
					financialStatus.equals(otherApplication.getFinancialStatus())&&
					documents.equals(otherApplication.getDocuments()));
		}
	}
	public String toString() {
		return applicantInfo.toString() + " --- " +
			   passport.toString() + " --- " + 
			   photo.toString() + " --- " + 
			   financialStatus.toString() + " --- " +
			   documents.toString();
		}
	//*******************************************//
	//			ABSTRACT METHODS  	 			 //
	//*******************************************//
	
	public abstract Application clone();
	public abstract void checkFinancialStatusValidation();
	public abstract void checkDocumentValidation();
	public abstract String calculateVisaDuration();

	
	//*******************************************//
	//			GETTERS AND SETTERS   			 //
	//*******************************************//
	public ApplicantInfo getApplicantInfo() {	return applicantInfo.clone();}
	public void setApplicantInfo(ApplicantInfo applicantInfo) {	this.applicantInfo = applicantInfo.clone();}
	
	public Passport getPassport() {	return passport.clone();}
	public void setPassport(Passport passport) {	this.passport = passport.clone();}

	public Photo getPhoto() {	return photo.clone();}
	public void setPhoto(Photo photo) {	this.photo = photo.clone();}

	public FinancialStatus getFinancialStatus() {	return financialStatus.clone();}
	public void setFinancialStatus(FinancialStatus financialStatus) {	this.financialStatus = financialStatus.clone();}

	@SuppressWarnings("unchecked")
	public ArrayList<Document> getDocuments() {	return (ArrayList<Document>) documents.clone();}
	@SuppressWarnings("unchecked")
	public void setDocuments(ArrayList<Document> documents) {	this.documents = (ArrayList<Document>) documents.clone();}
	
	public String getError() {	return error;}
	public void setError(String error) {	this.error = error;}
	
	
	//*******************************************//
	//			PRIVATE METHODS 	   			 //
	//*******************************************//
	
	/*
	 * returns true when the given string is convertable to Integer
	 * returns false when the given string is not convertable to Integer
	 */
	private boolean isNumeric(String strNum) {
	    if (strNum == null) {	return false;}
	    
	    try {	int d = Integer.parseInt(strNum);} 
	    catch (NumberFormatException e) {   return false;}
	    return true;
	}
	
	/*
	 * finds and returns application types by using prefixes
	 */
	public String findApplicationType(String applicantID) {
		String prefix = applicantID.substring(0, 2);
		switch(prefix) {
		case "11": return "Tourist";
		case "23": return "Worker";
		case "25": return "Educational";
		case "30": return "Immigrant";
		default :  return "empty";
		}
	}
	
	
}