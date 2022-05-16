package g44Package;

public class FinancialStatus {
	private String applicantID;
	private int income;
	private int savings;
	
	//*******************************************//
	//				CONSTRUCTORS		  		 //
	//*******************************************//
	public FinancialStatus() {
		applicantID = "empty";
		income = -1;
		savings = -1;
	}
	public FinancialStatus(String applicantID, int income, int savings) {
		this.applicantID = applicantID;
		this.income = income;
		this.savings = savings;
	}
	
	
	//*******************************************//
	//		METHODS INHERITED FROM OBJECT  		 //
	//*******************************************//
	
	public FinancialStatus clone() {
		return new FinancialStatus(getApplicantID(),getIncome(),getSavings());
	}
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		} else if (otherObject.getClass() != getClass()){
			return false;
		} else {
			FinancialStatus otherFinancialStatus = (FinancialStatus) otherObject;
			return applicantID.equals(otherFinancialStatus.getApplicantID()) &&
				   (income == otherFinancialStatus.getIncome()) &&
				   (savings == otherFinancialStatus.getSavings());
			
		}
	}
	public String toString() {
		return applicantID + " | " + income + " | " + savings;
	}
	
	//*******************************************//
	//			GETTERS AND SETTERS   			 //
	//*******************************************//
	
	public String getApplicantID() {	return applicantID;}
	public void setApplicantID(String applicantID) {	this.applicantID = applicantID;}
	
	public int getIncome() {	return income;}
	public void setIncome(int income) {	this.income = income;}
	
	public int getSavings() {	return savings;}
	public void setSavings(int savings) {	this.savings = savings;}
}
