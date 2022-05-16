package g44Package;

import java.util.ArrayList;

public interface IFileIO {
	/*
	 * reads file with given path
	 * creates ArrayList with base type : Application
	 * sorts this ArrayList w.r.t applicantID
	 * returns the ArrayList
	 */
	public ArrayList<Application>  readFile(String filePath);
}
