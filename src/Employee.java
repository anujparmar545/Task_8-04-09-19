
public class Employee{

	private int eno;
	private String ename;
	private double salary;
	private String designation;
	private String dept;
	
	
	public Employee() {}
	
	
	public Employee(int eno, String ename, double salary, String designation, String dept) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.salary = salary;
		this.designation = designation;
		this.dept = dept;
	}
	
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	@Override
	public String toString() {
		return "Employee [eno=" + eno + ", ename=" + ename + ", salary=" + salary + ", designation=" + designation
				+ ", dept=" + dept + "]";
	}
	
	
	
}
