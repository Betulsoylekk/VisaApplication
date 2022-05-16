package g44Package;

import java.util.ArrayList;
import java.time.Period;
import java.time.LocalDate;

public class TouristApplication extends Application{

	
	//*******************************************//
	//				CONSTRUCTORS		  		 //
	//*******************************************//
	public TouristApplication() {	super();}
	
	public TouristApplication(ApplicantInfo applicantInfo,Passport passport,Photo photo,
		     											FinancialStatus financialStatus,
		     											ArrayList<Document> documents) {
		super(applicantInfo, passport, photo, financialStatus, documents);}
	
	//*******************************************//
	//				METHODS OVERRIDED			 //
	//*******************************************//
	@Override
	public void checkFinancialStatusValidation() {
		if (getFinancialStatus().equals(new FinancialStatus())) {
			super.setError("Applicant does not have a financial status report");
		} else {
			if (!isHaveDocumentType("IL")) {
				boolean fs = isFinancialStatusStable(2000,3000,4000,12000,6000);
				if (fs == false) {
					super.setError("Applicant doesn't have a stable financial status");
				}
			} else {
				boolean fs = isFinancialStatusStable(1000,1500,2000,6000,3000);
				if (fs == false) {
					super.setError("Applicant doesn't have a stable financial status");
				}
			}
		}
		
	}

	@Override
	public void checkDocumentValidation() {
		// There is no limitations for TouristApplication
		
	}
	
	@Override
	public TouristApplication clone() {
		TouristApplication temp = new TouristApplication();
		temp.setApplicantInfo(getApplicantInfo());
		temp.setPassport(getPassport());
		temp.setPhoto(getPhoto());
		temp.setFinancialStatus(getFinancialStatus());
		temp.setDocuments(getDocuments());
		return temp;
	}

	@Override
	public String calculateVisaDuration() {
		checkDocumentValidation();
		checkFinancialStatusValidation();
		super.checkPhotoValidation();
		super.checkPassportValidation();
		if (super.getError().equals("empty")) {
			int visaDuration;
			double DC;
			int tempIncome = getFinancialStatus().getIncome();
			int tempSavings = getFinancialStatus().getSavings();
			int expirationDateInMonths = Period.between(LocalDate.now(), getPassport().getExpirationDate()).getYears()*12 +
										 Period.between(LocalDate.now(), getPassport().getExpirationDate()).getMonths();
			if (isHaveDocumentType("IL")) {
				DC = ((tempIncome - 2000)*6 + tempSavings)/12000;
			}else {
				DC = ((tempIncome - 2000)*6 + tempSavings)/6000;
			}
			if (DC>=1 && DC <2) { // no limitation
				visaDuration = 6;
				return visaDurationIntToString(visaDuration);
			} else if (DC>= 2 && DC <4) {
				visaDuration = 12;
				if (expirationDateInMonths < 12) {
					visaDuration = 6;
				}
				return visaDurationIntToString(visaDuration);
			} else if (DC >= 4) {
				visaDuration = 60;
				if (expirationDateInMonths < 12) {
					visaDuration = 6;
				} else if (expirationDateInMonths < 60) {
					visaDuration = 12;
				}
				return visaDurationIntToString(visaDuration);
			} else {
				return "DC IS SMALLER THAN 1";
			}
		}else {
			return super.getError();
		}
	}
	
	//*******************************************//
	//		METHODS INHERITED FROM OBJECT  		 //
	//*******************************************//
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		} else if (otherObject.getClass() != getClass()){
			return false;
		} else {
			TouristApplication otherApplication = (TouristApplication) otherObject;
			return (getApplicantInfo().equals(otherApplication.getApplicantInfo()) &&
					getPassport().equals(otherApplication.getPassport()) &&
					getPhoto().equals(otherApplication.getPhoto()) &&
					getFinancialStatus().equals(otherApplication.getFinancialStatus())&&
					getDocuments().equals(otherApplication.getDocuments()));
		}
	}
	
	//*******************************************//
	//			PRIVATE METHODS 	   			 //
	//*******************************************//
	private String visaDurationIntToString(int visaDuration) {
		if (visaDuration == 6) {
			return "6 month";
		} else if (visaDuration == 12) {
			return "1 year";
		}else {
			return "5 year";
		}
	}
}
