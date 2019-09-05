
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	static Scanner sc=new Scanner(System.in);
	static ArrayList<Employee> list=new ArrayList<>();
	
	static Employee e4=new Employee(444,"DDD",4000,"hr","Consultant");
	static Employee e1=new Employee(111,"AAA",1000,"TL","Software");
	static Employee e5=new Employee(555,"EEE",5000,"accountant","Marketing");
	static Employee e3=new Employee(333,"CCC",3000,"manager","Operations");
	static Employee e2=new Employee(222,"BBB",2000,"TL","Sales");
	
	
	
	public static void main(String[] args) throws Exception
	{
		list.add(e1);
		list.add(e2);
		list.add(e3);
		list.add(e4);
		list.add(e5);
		System.out.println("Select choice");
		
		
		while(true)
		{	
			System.out.println("Select 1 to add Employee");
			System.out.println("Select 2 to view Employees");
			System.out.println("Select 3 to remove Employee");
			System.out.println("Select 4 to clear Data");
			System.out.println("Select 5 to change Salary");
			System.out.println("Select 6 to search Employee");
			System.out.println("Select 7 to search dept Wise Employee");
			System.out.println("Select 8 to Sort Employees");
			System.out.println("Select 9 to view salary of Employees");
			System.out.println("Select 10 to exit");
			int choice=sc.nextInt();
		switch(choice)
		{
		case 1:addEmployee();
			break;
		case 2:viewEmployees();
			break;
		case 3:removeEmployee();
			break;
		case 4:clearData();
			break;
		case 5:changeSalary();
			break;
		case 6:searchEmployee();
			break;
		case 7:deptWiseEmployee();
			break;
		case 8:sort();
			break;
		case 9:viewSalary();
			break;
		case 10: System.exit(0);
		break;
		default:
			break;
		}
		}
		
	}

	

	private static void viewSalary() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Enter Employee no. to get its salary");
		int eno=sc.nextInt();
		
		Connection con=Connect.getConn();
		CallableStatement cst=con.prepareCall("{?=call viewSal(?)}");
		cst.setInt(2, eno);
		
		cst.registerOutParameter(1, Types.DOUBLE);
		cst.executeQuery();
		double salary=cst.getDouble(1);
		
		System.out.println("Salary of Employee "+eno+" is: "+salary);
		con.close();
		}



	private static void sort() throws SQLException {
		
		
		
		System.out.println("Enter Sorting Basis : eno or ename or salary or designation or dept");
		String basis=sc.next();
		System.out.println("Enter Sorting Order,asc or dsc");
		String order=sc.next();
		 
		Connection con=Connect.getConn();
		String sql="select * from Employee where order by ? ?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1,basis);
		pst.setString(2,order);
		ResultSet rs=pst.executeQuery();
		
		while(rs.next())
		{
			
				System.out.println("[ Employee Id :"+rs.getInt(1)+", Employee Name :"+rs.getString(2)+", Employee Salary :"
						+ rs.getString(3)+", Employee Designation :"+rs.getString(4)+","
								+ " Employee Dept :"+rs.getString(5)+"]");
		
		}
		
		viewEmployees();
		con.close();
	}



	private static void deptWiseEmployee() throws SQLException {
		
		System.out.print("Enter dept name whom you want to search");
		String depart=sc.next();
		
		
		Connection con=Connect.getConn();
		String sql="select * from Employee where dept=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1,depart);
		ResultSet rs=pst.executeQuery();
		
		while(rs.next())
		{
			
				System.out.println("[ Employee Id :"+rs.getInt(1)+", Employee Name :"+rs.getString(2)+", Employee Salary :"
						+ rs.getString(3)+", Employee Designation :"+rs.getString(4)+","
								+ " Employee Dept :"+rs.getString(5)+"]");
		
		}
		
		
	
		System.out.println();
		con.close();
	}



	private static void searchEmployee() throws SQLException {
		
		System.out.print("Enter employee id whom you want to search");
		int eid=sc.nextInt();
		
		
		Connection con=Connect.getConn();
		String sql="select * from Employee eno=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1, eid);
		ResultSet rs=pst.executeQuery();
		

		while(rs.next())
		{
			
				System.out.println("[ Employee Id :"+rs.getInt(1)+", Employee Name :"+rs.getString(2)+", Employee Salary :"
						+ rs.getString(3)+", Employee Designation :"+rs.getString(4)+","
								+ " Employee Dept :"+rs.getString(5)+"]");
		
		}

		System.out.println();
		
		con.close();
		}
		
	



	private static void changeSalary() throws SQLException {
		
		System.out.print("Enter employee id for which you want to change salary");
		int eid=sc.nextInt();
		System.out.print("Enter new salary");
		double sal=sc.nextDouble();
		
		Connection con=Connect.getConn();
		String sql="update Employee set salary=? where eno=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setDouble(1, sal);
		pst.setInt(2, eid);
		pst.execute();
		
		System.out.println("Salary Updated for employee no: "+eid);
		System.out.println();
		
		con.close();
	}



	private static void clearData() throws SQLException {
		
		Connection con=Connect.getConn();
		String sql="delete from Employee";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.execute();
		viewEmployees();
		System.out.println("All Employees Deleted");
	
		con.close();
	}



	private static void removeEmployee() throws SQLException {
		
		viewEmployees();
		System.out.print("Enter employee id which you want to delete");
		int eid=sc.nextInt();
		Connection con=Connect.getConn();
		String sql="delete from Employee where eno=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1, eid);
		pst.executeUpdate();
		viewEmployees();
		System.out.println();
		
		con.close();
		
	}



	private static void addEmployee() throws Exception{
		
		Employee emp=new Employee();
		
		System.out.println("Enter Employee Id");
		int eno=sc.nextInt();
		emp.setEno(eno);
		
		System.out.println("Enter Employee Name");
		String ename=sc.next();
		emp.setEname(ename);
		
		System.out.println("Enter Employee Salary");
		double salary=sc.nextDouble();
		emp.setSalary(salary);
		
		sc.nextLine();
		System.out.println("Enter Employee Designation");
		String designation=sc.nextLine();
		emp.setDesignation(designation);
		
		
		
		System.out.println("Enter Employee Department");
		String dept=sc.nextLine();
		emp.setDept(dept);
		
		
		//list.add(emp);
		Connection con=Connect.getConn();
		CallableStatement cst=con.prepareCall("{call insertEmp(?,?,?,?,?)}");
		cst.setInt(1, eno);
		cst.setString(2,ename );
		cst.setDouble(3, salary);
		cst.setString(4,designation );
		cst.setString(5,dept );
		cst.execute();
		System.out.println("Employee Added Successfully");
		System.out.println();
		
		con.close();
	
	}
	
	private static void viewEmployees() throws SQLException {
		
		Connection con=Connect.getConn();
		String sql="select * from Employee order by eno";
		PreparedStatement pst=con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		
		if(rs.isBeforeFirst())
		{
			while(rs.next())
			{
				System.out.println("[ Employee Id :"+rs.getInt(1)+", Employee Name :"+rs.getString(2)+", Employee Salary :"
						+ rs.getString(3)+", Employee Designation :"+rs.getString(4)+","
								+ " Employee Dept :"+rs.getString(5)+"]");
			}
			
		}
		con.close();
		System.out.println();
		
	}

}
