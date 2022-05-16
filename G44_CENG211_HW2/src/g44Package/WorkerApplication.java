package g44Package;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class WorkerApplication extends Application{

	
	//*******************************************//
	//				CONSTRUCTORS		  		 //
	//*******************************************//
	public WorkerApplication() {	super();}
	
	public WorkerApplication(ApplicantInfo applicantInfo,Passport passport,Photo photo,
														FinancialStatus financialStatus,
														ArrayList<Document> documents) {
		super(applicantInfo, passport, photo, financialStatus,documents);}
	
	
	//*******************************************//
	//				METHODS OVERRIDED			 //
	//*******************************************//
	
	@Override
	public void checkFinancialStatusValidation() {
		if (getFinancialStatus().equals(new FinancialStatus())) {
			super.setError("Applicant doesn't have financial status report");
		}else {
			int tempSavings = getFinancialStatus().getSavings();
			if (!(tempSavings >= 2000)) {
				super.setError("Applicant doesn't have stable financial status");
			}
		}
	}

	@Override
	public void checkDocumentValidation() {
		if (!isHaveDocumentType("LA")) {
			super.setError("Applicant does not have a letter of acceptance");
		}
	}

	@Override
	public WorkerApplication clone() {
		WorkerApplication temp = new WorkerApplication();
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
			int ad = findAcceptanceDuration();
			int ed = Period.between(LocalDate.now(), getPassport().getExpirationDate()).getYears()*12 + 
										 Period.between(LocalDate.now(), getPassport().getExpirationDate()).getMonths();
			if (ad < 60 && 60 <ed) {
				return visaDurationIntToString(60);
			} else if (ad < 24 && 24 <ed) {
				return visaDurationIntToString(24);
			} else if ((ad < 12 && 12 <ed)) {
				return visaDurationIntToString(12);
			} else if (ad > 60 && ed > 60) {
				return visaDurationIntToString(60);
			} else if (ad > 24 && ed > 24) {
				return visaDurationIntToString(24);
			} else if (ad > 12 && ed > 12) {
				return visaDurationIntToString(12);
			} else {
				super.setError("Expiration date is not suitable");
				return "rejected";
			}
			
		} else {
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
			WorkerApplication otherApplication = (WorkerApplication) otherObject;
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
	
	private int findAcceptanceDuration() {
		if (getDocuments().size() == 0) {
			return -1;
		} else {
			for (Document i: getDocuments()) {
				if (i.getDocumentType().equals("LA")) {
					return i.getDurationInMonths();
				}
			}
			return -1;
		}
	}
	private String visaDurationIntToString(int visaDuration) {
		if (visaDuration == 12) {
			return "1 year";
		} else if (visaDuration == 24) {
			return "2 year";
		}else {
			return "5 year";
		}
	}
}
