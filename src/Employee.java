package src;

public class Employee {
	private int id;
	private int depart;
	private String name;
	private String departmentName;
	
	public Employee( int iD, int dep, String nam) {
		int splitIndex=0;
		int space=0;
		//function splits up the name and the department name which is currently one big string
		for(int i=0;i<nam.length();i++) {
			//goes thru every letter of the string
			String letter = nam.substring(i,i+1);
			//counts the number of 'spaces'
			if(letter.equals(" ")) {
				space++;
			}
			//if there are 2 spaces it means that the index is at the place where the name and department name split
			if(space==2) {
				//hold that index as a variable
				splitIndex=i;
			}
		}
		//get the new names based off of the newly found splitting index
		String justName = nam.substring(0,splitIndex+1);
		String department = nam.substring(splitIndex+2,nam.length());
		
		//declare instance variables for every employee
		depart=dep;
		id=iD;
		departmentName = department;
		name=justName;
		
	}
	public int getID() {
		return id;
	}
	public int getDepart() {
		return depart;
	}
	public void setDepartmentName(String name) {
		departmentName = name;
	}
	public String getDepartmentName(){
		return departmentName;
	}
	public String getName() {
		
		return name;
	}
	//returns a string of the id, name, and department name
	public String toString() {
		return "id: " +this.getID() +" name: "+this.getName()+" dep: "+this.getDepartmentName();
	}
}
