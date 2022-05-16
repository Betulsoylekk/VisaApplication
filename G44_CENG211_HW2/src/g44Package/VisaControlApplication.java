/*
 * This homework done by Group44
 * Cihat Gelir
 * Betül Söylek
 * Hakan Çoban
 */
package g44Package;
import java.util.ArrayList;
/*
 * System goes like that:
 * Application class is base class and :
 * 		EducationalApplication						 
 * 		ImmigrantApplication						 **These classes uses some methods as in base class
 * 		TouristApplication							 and overrides some methods
 * 		WorkerApplication are it's derived classes
 * 
 * Application class is have:
 * 		ApplicantInfo
 * 		Passport
 * 		Photo -> has attributes Resulation
 * 		FinancialStatus
 * 		ArrayList<Document> 
 * 		error 					as it's attributes
 * 
 * FileIO-> readFiles methods reads .csv files and returns an ArrayList<Application> list;
 * structure of this list will be like:
 * 		Application = new EducationalApplication()
 * 		Application = new ImmigrantApplication()					 
 * 		Application = new TouristApplication()
 * 		Application = new WorkerApplication()
 * 					.
 * 					.
 * 					.
 * 	
 * **thanks to polymorphism concept it is possible to cycle this ArrayList**	
 *
 * ----> error concept <----
 * as you can see sometimes methods updates the "error" attribute, 
 * this update mechanism IS IN THE ORDER(given in the homework pdf)
 * 
 * 
 * ----> Important Notes About differences in the output <----
 * the instruction in the homework pdf caused some misunderstanding 
 * made us think like: "should we use exclusive or inclusive ..." 
 * this caused some differences in output
 * 
 * ----> Importance Notes About Time <----
 * program understands which day you run the program "There is no starting date" thanks to java.time.LocalDate
 * months are NOT fixed to 30 
 * these  differences also can cause differences in output 
 * 
 */

public class VisaControlApplication {
	public static void main(String[] args) {
		FileIO fileIO = new FileIO();
		ArrayList<Application> applicationList = fileIO.readFile("HW2_ApplicantsInfo.csv");
		for (Application i : applicationList) {
			System.out.println(i.result());
		}
	}
}