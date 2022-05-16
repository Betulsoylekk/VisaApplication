package g44Package;

public class Document {
	private String applicantID;
	private String documentType;
	private int durationInMonths;
	
	
	//*******************************************//
	//				CONSTRUCTORS		  		 //
	//*******************************************//
	
	public Document() {
		applicantID = "empty";
		documentType = "empty";
		durationInMonths = -1;
	}
	
	public Document(String applicantID, String documentType, int durationInMonths) {
		this.applicantID = applicantID;
		this.documentType = documentType;
		this.durationInMonths = durationInMonths;
	}

	
	//*******************************************//
	//		METHODS INHERITED FROM OBJECT  		 //
	//*******************************************//
	
	public Document clone() {
		return new Document(this.getApplicantID(),this.getDocumentType(),this.getDurationInMonths());
	}
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		} else if (otherObject.getClass() != this.getClass()){
			return false;
		} else {
			Document otherDocument = (Document) otherObject;
			return applicantID.equals(otherDocument.getApplicantID()) &&
				   documentType.equals(otherDocument.getDocumentType()) &&
				   (durationInMonths == otherDocument.getDurationInMonths());
		}
	}
	
	public String toString() {
		return applicantID + " | " + documentType + " | " + durationInMonths;
	}
	
	//*******************************************//
	//			GETTERS AND SETTERS   			 //
	//*******************************************//
	
	public String getApplicantID() {	return applicantID;}
	public void setApplicantID(String applicantID) {	this.applicantID = applicantID;}

	public String getDocumentType() {	return documentType;}
	public void setDocumentType(String documentType) {	this.documentType = documentType;}

	public int getDurationInMonths() {	return durationInMonths;}
	public void setDurationInMonths(int durationInMonths) {	this.durationInMonths = durationInMonths;}
}
