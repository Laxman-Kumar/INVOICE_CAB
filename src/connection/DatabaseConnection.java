package connection;

import java.io.PrintWriter;
import java.sql.*;

public class DatabaseConnection {
	
	public Connection createConnection() throws Exception{
		Statement stmt = null;
		Connection conn = null; 
		 final String USER = "db2admin";
         final String PASS = "Pikachu007";	
         final String JDBC_DRIVER = "com.ibm.db2.jcc.DB2Driver";  
         final String DB_URL = "jdbc:db2://localhost:50000/project";
         
		      //STEP 2: Register JDBC driver
		      Class.forName("com.ibm.db2.jcc.DB2Driver");
		      //STEP 3: Open a connection
		      
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected to Datatabase.. successfully");
		     
		   
		return conn;
	}
	
	public void closeConnection(Connection con) throws SQLException{
		con.close();
	}
	
	
	public ResultSet selectStatement(Connection con,String sqlStatement) throws SQLException{
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sqlStatement);
		return rs;		
	}	
}
