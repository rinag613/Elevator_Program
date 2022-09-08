package src;

public class ITEmployee extends Employee {
	private int floorNum;
	private static boolean ITinside = false;
	
	public ITEmployee(int id, int fN, String nam){
		super(id,fN, nam);
		floorNum = fN;
		setDepartmentName("IT -can go to any department");
		ITinside = true;
	}
	public void setITinside(boolean inside) {
		ITinside = inside;
	}
	public boolean getITinside() {
		return ITinside;
	}
	
}
