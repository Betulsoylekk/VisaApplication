package g44Package;

public class Photo {
	private String applicantID;
	private Resulation resulation;
	private String position;
	
	//*******************************************//
	//				CONSTRUCTORS		  		 //
	//*******************************************//
	public Photo() {
		applicantID = "empty";
		resulation = new Resulation();
		position = "empty";
	}
	
	public Photo(String applicantID, String resulation, String position) {
		this.applicantID = applicantID;
		this.resulation  = new Resulation(resulation);
		this.position = position;
	}
	
	
	//*******************************************//
	//		METHODS INHERITED FROM OBJECT  		 //
	//*******************************************//
	
	public Photo clone() {
		Photo temp = new Photo();
		temp.setApplicantID(getApplicantID());
		temp.setResulation(getResulation());
		temp.setPosition(getPosition());
		return temp;
	}
	
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		} else if (otherObject.getClass()!= this.getClass()){
			return false;
		} else {
			Photo otherPhoto = (Photo) otherObject;
			return applicantID.equals(otherPhoto.getApplicantID()) &&
				   resulation.equals(otherPhoto.getResulation()) &&
				   position.equals(otherPhoto.getPosition());
		}
	}
	
	public String toString() {
		return applicantID + " | " + resulation.toString() + " | " + position;
	}

	//*******************************************//
	//			GETTERS AND SETTERS   			 //
	//*******************************************//
	
	public String getApplicantID() {	return applicantID;}
	public void setApplicantID(String applicantID) {	this.applicantID = applicantID;}

	public String getPosition() {	return position;}
	public void setPosition(String position) {	this.position = position;}

	public Resulation getResulation() {return resulation.clone(); }
	public boolean setResulation(Resulation resulation) {
		if (resulation == null) {	return false;}
		this.resulation = resulation.clone();
		return true;
	}

}
