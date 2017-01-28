package publish;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {

	public static Connection connect() {
		Connection c = null;
		//System.out.println("This is in the db file");

		try {

			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://54.169.218.13/report","power_user", "password");
			c.setAutoCommit(false);
			// System.out.println("Connected to Database Successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		// System.out.println("Connection to Database successful!");
		return c;
	}

	public static void insertsheet1(DbConnect conn, String Name, String Status) throws SQLException 
	{
		conn = new DbConnect();
		Connection c = null;
		c = DbConnect.connect();
		Statement stmt = null;
		stmt = c.createStatement();
	
		String sql = "INSERT INTO mfa (Name, Status) SELECT '"+Name+"', '"+Status+"' WHERE NOT EXISTS (SELECT Name FROM mfa WHERE Name IN ('"+Name+"') AND Date IN (current_date))";
		
		stmt.executeUpdate(sql);
		
		c.commit();	
	}
	
	public static void insertsheet2(DbConnect conn, String Name, String Status) throws SQLException 
	{
		conn = new DbConnect();
		Connection c = null;
		c = DbConnect.connect();
		Statement stmt = null;
		stmt = c.createStatement();
		//String sql = "INSERT INTO Sheet2 (Name, EmpID) VALUES('"+Name+"','"+EmpID+"')";
		String sql = "INSERT INTO keyrotate (Name, Status) SELECT '"+Name+"', '"+Status+"' WHERE NOT EXISTS (SELECT Name FROM keyrotate WHERE Name IN ('"+Name+"') AND Date IN (current_date))";
		//String sqldel = "DELETE Name from Sheet2 WHERE Name NOT IN (SELECT Name FROM Sheet2)";
		stmt.executeUpdate(sql);
		//stmt.executeUpdate(sqldel);
		c.commit();	
	}

}