package g44Package;

import java.util.ArrayList;

public class ImmigrantApplication extends Application{

	
	//*******************************************//
	//				CONSTRUCTORS		  		 //
	//*******************************************//
	
	public ImmigrantApplication() {	super();}
	
	public ImmigrantApplication(ApplicantInfo applicantInfo,Passport passport,Photo photo,
		     												FinancialStatus financialStatus,
		     												ArrayList<Document> documents) {
		super(applicantInfo, passport, photo, financialStatus,documents);}
	
	
	//*******************************************//
	//				METHODS OVERRIDED			 //
	//*******************************************//
	
	@Override
	public void checkFinancialStatusValidation() {
		if (getFinancialStatus().equals(new FinancialStatus())) {
			super.setError("Applicant doesn't have a financial status report");
		} else {
			int tempSavings = getFinancialStatus().getSavings();
			if (isHaveDocumentType("GC")) {
				if (!(tempSavings >= 4000)) {
					super.setError("Applicant doesn't have a stable financial status");
				}
			}
			else if (isHaveDocumentType("IL")) {
				if (!(tempSavings >= 25000)) {
					super.setError("Applicant doesn't have a stable financial status");
				}
			}
			else {
				if (!(tempSavings >= 50000)) {
					super.setError("Applicant doesn't have a stable financial status");
				}
			}
		}
		
	}

	@Override
	public void checkDocumentValidation() {
		// There is no limitations for immigrantApplication
		return;
	}

	@Override
	public EducationalApplication clone() {
		EducationalApplication temp = new EducationalApplication();
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
			return "Permanent Visa";
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
			ImmigrantApplication otherApplication = (ImmigrantApplication) otherObject;
			return (getApplicantInfo().equals(otherApplication.getApplicantInfo()) &&
					getPassport().equals(otherApplication.getPassport()) &&
					getPhoto().equals(otherApplication.getPhoto()) &&
					getFinancialStatus().equals(otherApplication.getFinancialStatus())&&
					getDocuments().equals(otherApplication.getDocuments()));
		}
	}
}
