package src;

public class Elevator {
	private static int numElevator = 0;
	private int floorNum;
	//tracks the amount of ppl in the elevator with an array of employees and max space 6 (not useful in this specific code)
	private static Employee[] inside;
	//what's actually used in this code:
	
		
	public Elevator(int startingNum,  int elevNum) {
		floorNum = startingNum;
				numElevator = elevNum;
	}
	
	public void changeFloorNum(int num) {
		floorNum=num;
	}
	public void clearEmployeesInside() {
		inside=new Employee[6];
	}
	public static void setInsideLength(int len) {
		inside = new Employee[len];
	}
	public void enterElevator(Employee entering, int length) {
		//a person enters the array at the first empty space found
		
		int emptySpace=0;
		
		for(int i=0;i<length;i++) {
			if(i>length) {
				inside[i] = null;
			}else if(inside[i]==null){
				emptySpace = i;
			}
		}
		inside[emptySpace] = entering;
		
	}
	public void orderArray() {
		Employee[] newInside = new Employee[inside.length];
		Employee temp=inside[0];
		for(int i=0;i<inside.length;i++) {
			if(inside[i].getDepart()>temp.getDepart()) {
				
			}
		}
	}
	public int getFloorNum() {
		return this.floorNum;
	}
	public void changeElevNum(int num) {
		numElevator = num;
	}
	public Employee[] getEmployeesInside() {

		return inside;
	}
	public int getNumEmployeesInside() {
		int count=0;
		for(int i=0;i<inside.length;i++) {
			if(inside[i]!=null) {
				count++;
			}
		}
		return count;
	}
	
	public void exitElevator(Employee exiting,int length) {
		//finds where the employee is located in the array and removes the employee from that spot setting it back to null
		for(int i=0;i<inside.length;i++) {
			if(inside[i]!=null) {
				if((inside[i].getID())==exiting.getID()){
					inside[i]=null;
				}
			}
		}
	}
	public int getNumElevator() {
		return numElevator;
	}
	//returns a string of the floor number, whose got off, what department if up/main floor if down
	public String toString() {
			return "Elevator " +getNumElevator() +" is now at floor: " +this.getFloorNum();
	
	}
	
}

