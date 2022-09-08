package src;
import java.util.Arrays;

import java.util.InputMismatchException;
import java.io.*;
import java.util.Scanner;

public class ManyMain {

	public static void main(String[] args) throws IOException {
		Employee[] employees=new Employee[51];
		String fileName="C:\\Users\\User\\Downloads\\employeesOrig.txt";
		try {
			 //stores the data from the file in an array of Employee objects
			File myFile = new File(fileName);
			Scanner input = new Scanner(myFile);
	
			for(int i=0;i<51;i++) {
				int id;
				int depart;
				String depStr;
				String name;
				//turns strings into ints
				String info = input.nextLine();
				String idStr =info.substring(0,3);
				if(info.substring(info.length()-2,info.length()-1).equals("1")) {
				 depStr = info.substring(info.length()-2,info.length());
				}else {
					depStr = info.substring(info.length()-1,info.length());
				}
				id=Integer.parseInt(idStr);
				depart = Integer.parseInt(depStr);
				name = info.substring(3,info.length()-2);
				//takes the info and makes it an object
				Employee employed = new Employee(id,depart,name);
				//puts that object into an array before it gets override
				employees[i]=employed;
				
			}
		}catch(FileNotFoundException e){
			System.out.println("The file you entered is incorrect. Please enter a new name");
			Scanner fixName = new Scanner(System.in);
			fileName = fixName.nextLine();
			File myFile = new File(fileName);
			Scanner input = new Scanner(myFile);
	
			for(int i=0;i<51;i++) {
				int id;
				int depart;
				String name;
				//turns strings into ints
				String info = input.nextLine();
				String idStr =info.substring(0,3);
				String depStr = info.substring(info.length()-1,info.length());
				id=Integer.parseInt(idStr);
				depart = Integer.parseInt(depStr);
				name = info.substring(3,info.length()-2);
				//takes the info and makes it an object
				Employee employed = new Employee(id,depart,name);
				//puts that object into an array before it gets override
				employees[i]=employed;
			}
		}
		System.out.println("Type 911 at any time to push the emergency button and stop the elevator\nIT employees must enter 111 before all other IDs");
			
		
		//where all of the elevators start off:
		Elevator elevator1 = new Elevator(0, 1);
		Elevator elevator2 = new Elevator(5, 2);
		Elevator elevator3 = new Elevator(10, 3);
		
		String floorN="0";
		Scanner move = new Scanner(System.in);
		int floorNum = 0;
		String idstr="";
		int fN=0;
		while(floorNum!=911&&!idstr.equals("911")&&fN!=911) {
			
			elevator1.changeElevNum(1);
			System.out.println("\n" +elevator1);
			elevator2.changeElevNum(2);
			System.out.println(elevator2);
			elevator3.changeElevNum(3);
			System.out.println(elevator3);
			
			try {
			System.out.println("Someone has pushed the button. What floor are you on?");
			 floorNum = move.nextInt();
			}catch(java.util.InputMismatchException e) {
				System.out.println("Please enter only numbers");
				String errorNum = move.nextLine();
				floorNum=move.nextInt();
			}
			boolean goingToMain;
			if(floorNum>0) {
				goingToMain=true;
			}else {
				goingToMain=false;
			}
			if(floorNum!=911&&fN!=911) {
			 
			int elev1;
			int elev2;
			int elev3;
			if(floorNum>elevator1.getFloorNum()) {
				elev1 =floorNum-elevator1.getFloorNum();
			}else {
				elev1=elevator1.getFloorNum()-floorNum;
			}
			if(floorNum>elevator2.getFloorNum()) {
				elev2=floorNum-elevator2.getFloorNum();
			}else {
				elev2=elevator2.getFloorNum()-floorNum;
			}
			if(floorNum>elevator3.getFloorNum()) {
				elev3=floorNum-elevator3.getFloorNum();
			}else {
				elev3=elevator3.getFloorNum()-floorNum;
			}
			
			String lowest;
			int check;
			if(elev1<elev2) {
				lowest = "Elev1";
				check=1;
			}else{
				lowest = "Elev2";
				check=2;
			}
			if(check>=elev3) {
				lowest = "Elev3";
			}
			
			//code for getting the people in the elevator
				int idNum=0;
				int counter = 0;
				System.out.println("Enter your ids");
				move.nextLine();
				idstr = move.nextLine();
				if(!idstr.equals("911")){
					int[] idNums = new int[idstr.length()/3];
					boolean ITinside = false;
					counter=idstr.length()/3-1;
					for( int i=0; i<idstr.length()-2;i++) {
						idNum = Integer.parseInt(idstr.substring(i,i+3));
						
						if(idNum==111) {
							ITinside = true;
							idNums[counter]=12;
							i+=2;
							counter--;
						}else {
						idNums[counter]=idNum;
						i+=2;
						counter--;
						}
					}
					if(ITinside) {
						System.out.println("An IT person has entered. Please enter the floor number you would like to go to");
						 fN = move.nextInt();
						if(fN!=911){
							ITEmployee  ITguy = new ITEmployee( 111, fN, "IT GUY");
							System.out.println("Elevator 1 is automatically called to bring IT guy to floor "+fN);
							elevator1.changeFloorNum(fN);
							elevator1.changeElevNum(1);
							System.out.println(elevator1 +" After dropping off the IT guy at floor "+fN);
							
						}else {
							break;
						}
					}
				int[] peopleInside;
			
				if(idNums.length>6) {
					peopleInside =new int[6];
					System.out.println("Only 6 people can enter at a time. The first 6 Ids may enter");
				}else if(ITinside){
					peopleInside = new int[idNums.length-1];
				}else {
					peopleInside=new int[idNums.length];
				}
				
				for(int t=0;t<peopleInside.length;t++) {
					if(idNums[t]!=12) {		
						peopleInside[t]=idNums[t];
					}
					
				}
				try {
				Employee[] employeesInside;
				
				if(lowest.equals("Elev1" )) {
					elevator1.setInsideLength(peopleInside.length);
					System.out.println("Those waiting may enter Elevator 1");
					elevator1.changeElevNum(1);
					for(int j=0;j<peopleInside.length;j++) {
						for(int i=0;i<51;i++) {
							if(employees[i].getID()==peopleInside[j]) {
								elevator1.enterElevator(employees[i],peopleInside.length);
							}
						}
					}
				
					employeesInside = elevator1.getEmployeesInside();
					
					if(goingToMain) {
						elevator1.changeFloorNum(0);
						System.out.println(elevator1 +" After dropping off " );
						for(int i=0;i<employeesInside.length;i++) {
							System.out.print(employeesInside[i].getName()+", ");
						}
						System.out.println(" on the main floor");
					}else {
						for(int i=0;i<peopleInside.length;i++) {
							

								elevator1.changeFloorNum(employeesInside[i].getDepart());
								System.out.println(elevator1 +" After dropping off " +employeesInside[i].getName() + " in the " +employeesInside[i].getDepartmentName() +" department");
								elevator1.exitElevator(employeesInside[i],peopleInside.length);
						}
					}

				}else if(lowest.equals("Elev2")) {
					elevator2.setInsideLength(peopleInside.length);
					System.out.println("Those waiting may enter Elevator 2");
					elevator2.changeElevNum(2);
					for(int j=0;j<peopleInside.length;j++) {
						for(int i=0;i<51;i++) {
							if(employees[i].getID()==peopleInside[j]) {
								elevator2.enterElevator(employees[i],peopleInside.length);
							}
						}
					}
				
					employeesInside = elevator2.getEmployeesInside();
					
					if(goingToMain) {
						elevator2.changeFloorNum(0);
						System.out.println(elevator2 +" After dropping off " );
						for(int i=0;i<employeesInside.length;i++) {
							System.out.print(employeesInside[i].getName()+", ");
						}
						System.out.println(" on the main floor");
					}else {
						for(int i=0;i<peopleInside.length;i++) {
							

								elevator2.changeFloorNum(employeesInside[i].getDepart());
								System.out.println(elevator2 +" After dropping off " +employeesInside[i].getName() + " in the " +employeesInside[i].getDepartmentName() +" department");
								elevator2.exitElevator(employeesInside[i],peopleInside.length);
						}
					}

				}else{
					elevator3.setInsideLength(peopleInside.length);
					System.out.println("Those waiting may enter Elevator 3");
					elevator3.changeElevNum(3);
					for(int j=0;j<peopleInside.length;j++) {
						for(int i=0;i<51;i++) {
							if(employees[i].getID()==peopleInside[j]) {
								elevator3.enterElevator(employees[i],peopleInside.length);
							}
						}
					}
				
					employeesInside = elevator3.getEmployeesInside();
					
					if(goingToMain) {
						elevator3.changeFloorNum(0);
						System.out.println(elevator3 +" After dropping off " );
						for(int i=0;i<employeesInside.length;i++) {
							System.out.print(employeesInside[i].getName()+", ");
						}
						System.out.println(" on the main floor");
					}else {
						for(int i=0;i<peopleInside.length;i++) {
							

								elevator3.changeFloorNum(employeesInside[i].getDepart());
								System.out.println(elevator3 +" After dropping off " +employeesInside[i].getName() + " in the " +employeesInside[i].getDepartmentName() +" department");
								elevator3.exitElevator(employeesInside[i],peopleInside.length);
						}
					}
				}
				}catch(java.lang.NullPointerException e) {
					System.out.println("\nCAUTION!!! AN IT PERSON TRIED TO ENTER THAT WAS NOT FIRST ON LINE. THAT IS A BREACH OF SECURITY.\nEVERYONE MUST ENTER THEIR IDS AGAIN");
					
				}
			}

		//end if	
		}
		//end while
		}
		System.out.println("THE ELEVATOR HAS SHUT OFF");
	
	}

}
