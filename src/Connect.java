import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {           //Singleton Class

	private static Connection con;       
	
	
	 public static Connection getConn() 
	 {
			
		 
		 try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			if(con==null)
			{
				con=DriverManager.getConnection(url, "java", "java");
				return con;
			}
		 }
		 catch (Exception e) {
			
			 e.printStackTrace();
		}
			
			return con;
		}

}
