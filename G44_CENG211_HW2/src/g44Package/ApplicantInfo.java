package g44Package;

public class ApplicantInfo {
	
	private String applicantID;
	private String applicantName;
	
	//*******************************************//
	//				CONSTRUCTORS		  		 //
	//*******************************************//
	public ApplicantInfo() {
		applicantID   = "empty";
		applicantName = "empty";
	}
	public ApplicantInfo(String applicantID, String applicantName) {
		this.applicantID = applicantID;
		this.applicantName = applicantName;
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
			ApplicantInfo otherApplicantInfo = (ApplicantInfo) otherObject;
			return applicantID.equals(otherApplicantInfo.getApplicantID()) &&
					applicantName.equals(otherApplicantInfo.getApplicantName());
		}
	}

	public ApplicantInfo clone() {
		return new ApplicantInfo(this.getApplicantID(),this.getApplicantName());
	}
	
	public String toString() {
		return applicantID + " | " + applicantName;
	}
	
	//*******************************************//
	//			GETTERS AND SETTERS   			 //
	//*******************************************//
	
	public String getApplicantID()	{	return applicantID;}
	public void setApplicantID(String applicantID) 	{	this.applicantID = applicantID;} 

	public String getApplicantName() {	return applicantName;}
	public void setApplicantName(String applicantName) {	this.applicantName = applicantName;}
}