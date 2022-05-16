package g44Package;

import java.util.ArrayList;

public interface IApplication {
	
	/*
	 * accessor and mutator methods of applicantInfo
	 * getApplicantInfo returns a deep copy of ApplicantInfo
	 * setApplicantInfo assign applicantInfo to deep copy of given parameter
	 */
	public ApplicantInfo getApplicantInfo();
	public void setApplicantInfo(ApplicantInfo applicantInfo);
	
	/*
	 * accessor and mutator methods of passport
	 * getPassport returns a deep copy of passport
	 * setPassport assign passport to deep copy of given parameter
	 */
	public Passport getPassport();
	public void setPassport(Passport passport);
	
	/*
	 * accessor and mutator methods of photo
	 * getPhoto returns a deep copy of photo
	 * setPhoto assign photo to deep copy of given parameter
	 */
	public Photo getPhoto();
	public void setPhoto(Photo photo);
	
	/*
	 * accessor and mutator methods of applicantInfo
	 * getApplicantInfo returns a deep copy of ApplicantInfo
	 * setApplicantInfo assign applicantInfo to deep copy of given parameter
	 */
	public FinancialStatus getFinancialStatus();
	public void setFinancialStatus(FinancialStatus financialStatus);
	
	/*
	 * accessor and mutator methods of Documents
	 * getDocuments returns a deep copy of Documents
	 * setDocuments assign applicantInfo to deep copy of given parameter
	 */
	public ArrayList<Document> getDocuments();
	public void setDocuments(ArrayList<Document> documents);
	
	/*
	 * accessor and mutator methods of error
	 * getError returns the error
	 * setError assign error to given parameter
	 */
	public String getError();
	public void setError(String error);
	
	/*
	 * returns meaningful string that describes the object
	 */
	public String toString();
	
	/*
	 * compares object with respect to their contents
	 */
	public boolean equals(Object otherObject);
	
	/*
	 * returns deep copy of object
	 */
	public abstract Application clone();
	
	/*
	 * checks financial status and if it detects a problem updates error 
	 */
	public abstract void checkFinancialStatusValidation();
	/*
	 * checks document status and if it detects a problem updates error 
	 */
	public abstract void checkDocumentValidation();
	/*
	 * tries to calculate VisaDuration
	 */
	public abstract String calculateVisaDuration();
	
	/*
	 * after all it gives the final decision that are given for this application
	 */
	public String result();
	
	/*
	 * adds deep copy of given argument to the documents
	 */
	public void addDocument(Document document);
	
	/*
	 * checks whether applicant have given documentType
	 */
	public boolean isHaveDocumentType(String documentType);
	
	/*
	 * checks are income and savings are sufficient
	 */
	public boolean isFinancialStatusStable( int minIncome,
			int level1MaxIncome,
			int level2MaxIncome,
			int level1MinSavings,
			int level2MinSavings);
	
	/*
	 * checks Passport validation and updates error
	 */
	public void checkPassportValidation();
	
	/*
	 * checks Photo validation and updates error
	 */
	public void checkPhotoValidation();
	
}
