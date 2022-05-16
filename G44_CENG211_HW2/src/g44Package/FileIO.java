package g44Package;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
	ArrayList<Application> applicationList = new ArrayList<Application>();
	public Scanner sc = null;
	public ArrayList<Application>  readFile(String filePath) {
		try 
		{
			sc = new Scanner(new File(filePath));
			while(sc.hasNextLine()) {
				Application tempApplication = null;
				String[] splittedLine = sc.nextLine().split(",");
				String documentPrefix = splittedLine[0];
				
				if (documentPrefix.equals("A")) {
					ApplicantInfo applicantInfo = readApplicantInfo(splittedLine);
					tempApplication = findApplication(applicantInfo.getApplicantID(),
													  findApplicationType(applicantInfo.getApplicantID()));
					applicationList.remove(tempApplication);
					tempApplication.setApplicantInfo(applicantInfo);
				} else if (documentPrefix.equals("S")) {
					Passport passport = readPassport(splittedLine);
					tempApplication = findApplication(passport.getApplicantID(),
							  findApplicationType(passport.getApplicantID()));
					applicationList.remove(tempApplication);
					tempApplication.setPassport(passport);
				} else if (documentPrefix.equals("P")) {
					Photo photo = readPhoto(splittedLine);
					tempApplication = findApplication(photo.getApplicantID(),
							  findApplicationType(photo.getApplicantID()));
					applicationList.remove(tempApplication);
					tempApplication.setPhoto(photo);
				} else if (documentPrefix.equals("D")) {
					Document document = readDocument(splittedLine);
					tempApplication = findApplication(document.getApplicantID(),
							  findApplicationType(document.getApplicantID()));
					applicationList.remove(tempApplication);
					tempApplication.addDocument(document);
				} else if (documentPrefix.equals("F")) {
					FinancialStatus financialStatus = readFinancialStatus(splittedLine);
					tempApplication = findApplication(financialStatus.getApplicantID(),
							  findApplicationType(financialStatus.getApplicantID()));
					applicationList.remove(tempApplication);
					tempApplication.setFinancialStatus(financialStatus);
				}
				applicationList.add(tempApplication);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File could not found!");
			System.exit(0);
		}
		sc.close();
		return sortList(applicationList);
	}
	
	
	/*
	 * sorts the list in ascending order w.r.t applicantID
	 */
	private ArrayList<Application> sortList(ArrayList<Application> applicationList){
		for (int i = 0; i < applicationList.size();i++) {
			for (int j = 1; j < applicationList.size()-i;j++) {
				int tempID1 = Integer.parseInt(applicationList.get(j-1).getApplicantInfo().getApplicantID());
				int tempID0 = Integer.parseInt(applicationList.get(j).getApplicantInfo().getApplicantID());
				if (tempID1 > tempID0) {
					Application y = applicationList.get(j-1);
					applicationList.set(j-1, applicationList.get(j));
					applicationList.set(j, y);}
			}
		}
		return applicationList;
	}
	
	/*
	 * tries to find ApplicationType from given applicantID
	 */
	private String findApplicationType(String applicantID) {
		String prefix = applicantID.substring(0, 2);
		switch(prefix) {
		case "11": return "TouristApplication";
		case "23": return "WorkerApplication";
		case "25": return "EducationalApplication";
		case "30": return "ImmigrantApplication";
		default :  return "empty";
		}
	}
	
	/*
	 * read the splitted line as asuming it is belongs to applicantInfo 
	 */
	private ApplicantInfo readApplicantInfo(String[] splittedLine) {
		String applicantID = splittedLine[1];
		String applicantName = splittedLine[2];
		return new ApplicantInfo(applicantID,applicantName);
	}
	
	/*
	 * read the splitted line as asuming it is belongs to Passport 
	 */
	private Passport readPassport(String[] splittedLine) {
		String applicantID = splittedLine[1];
		String passportNumber = splittedLine[2];
		int yearOfED = -1 ,monthOfED = -1,dayOfED = -1;
		String[] tempED;
		if (splittedLine[3].contains("-")) {
			tempED = splittedLine[3].split("-");
		} else {
			tempED = splittedLine[3].split("/");
		}
		
		yearOfED = Integer.parseInt(tempED[0]);
		monthOfED = Integer.parseInt(tempED[1]);
		dayOfED = Integer.parseInt(tempED[2]);
		
		LocalDate expirationDate = LocalDate.of(yearOfED,monthOfED,dayOfED);
		return new Passport(applicantID,passportNumber,expirationDate);
	}
	
	/*
	 * read the splitted line as asuming it is belongs to Photo 
	 */
	private Photo readPhoto(String[] splittedLine) {
		String applicantID = splittedLine[1];
		String resulation = splittedLine[2];
		String position = splittedLine[3];
		return new Photo(applicantID,resulation,position);
	}
	
	/*
	 * read the splitted line as asuming it is belongs to FinancialStatus 
	 */
	private FinancialStatus readFinancialStatus(String[] splittedLine) {
		String applicantID = splittedLine[1];
		int income = Integer.parseInt(splittedLine[2]);
		int savings = Integer.parseInt(splittedLine[3]);
		return new FinancialStatus(applicantID,income,savings);
	}
	
	/*
	 * read the splitted line as asuming it is belongs to Document 
	 */
	private Document readDocument(String[] splittedLine) {
		String applicantID = splittedLine[1];
		String documentType = splittedLine[2];
		int durationInMonths = -1;
		try {
			durationInMonths = Integer.parseInt(splittedLine[3]);
		} catch(ArrayIndexOutOfBoundsException e) {
		}
		
		return new Document(applicantID,documentType,durationInMonths);
	}
	
	/*
	 * tries to find application with given applicantID, 
	 * if there is such a application, then returns it
	 * else it creates an application with given applicantType
	 */
	private Application findApplication(String applicantID, String applicantType) {
		if (applicationList.size() == 0) {
			return createApplication(applicantType);
		} else {
			for(Application i : applicationList) {
				if (isApplicantIDsMatching(i,applicantID)) {	return i;}
			}
			return createApplication(applicantType);
		}
	}
	
	/*
	 *	returns true if applicantsIDs matches
	 *  returns false if applicantsIDs don't matches 
	 */
	private boolean isApplicantIDsMatching(Application app, String ID) {
		if (app.getApplicantInfo().getApplicantID().equals(ID) || 
		    app.getPassport().getApplicantID().equals(ID) || 
		    app.getPhoto().getApplicantID().equals(ID) ||
		    app.getFinancialStatus().getApplicantID().equals(ID)) {
			return true;
		} 
		if (app.getDocuments().size() > 0) {
			if (app.getDocuments().get(0).getApplicantID().equals(ID)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * creates new Application with given type
	 */
	private Application createApplication(String applicantType) {
		switch(applicantType) {
		case "TouristApplication": return new TouristApplication();
		case "WorkerApplication": return new WorkerApplication();
		case "EducationalApplication" : return new EducationalApplication();
		case "ImmigrantApplication" : return new ImmigrantApplication();
		default : return null;
		}
	}

	
}
