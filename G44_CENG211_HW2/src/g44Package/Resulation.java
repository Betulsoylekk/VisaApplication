package g44Package;

public class Resulation {
	private int height;
	private int width;
	
	//*******************************************//
	//				CONSTRUCTORS		  		 //
	//*******************************************//
	public Resulation() {
		height = 0;
		width = 0;
	}
	
	public Resulation(String resInString) {
		height = Integer.parseInt(resInString.split("x")[0]);
		width = Integer.parseInt(resInString.split("x")[1]);
	}
	
	//*******************************************//
	//			GETTERS AND SETTERS   			 //
	//*******************************************//
	
	public int getHeight() {	return height;}
	public void setHeight(int height) {	this.height = height;}	
	
	public int getWidth()  {	return width; }
	public void setWidth() {	this.width = width;}
	
	//*******************************************//
	//				PUBLIC METHODS			  	 //
	//*******************************************//
	
	public boolean isSizeValid() {
		return height >= 600 &&
			   height <=1200 &&
			   width >= 600 &&
			   width <= 1200;
	}
	
	public boolean isSquare() {
		return height == width;
	}

	public boolean isResulationValid() {
		return isSquare() && isSizeValid();
	}
	
	
	//*******************************************//
	//		METHODS INHERITED FROM OBJECT  		 //
	//*******************************************//
	
	public Resulation clone() {
		return new Resulation(height + "x" + width);
	}
	
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		} else if (otherObject.getClass() != this.getClass()){
			return false;
		} else {
			Resulation otherResulation = (Resulation) otherObject;
			return (height == otherResulation.getHeight()) && 
					(width == otherResulation.getWidth());
		}
	}
	public String toString() {
		return height + "x" + width;
	}
}

