import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class dbClass {
	Connection c = null;//for connection
	Statement s = null;//for query execution
	ResultSet r = null;//to get result from DB
	
	void dbSuperConnector(){
		
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		JOptionPane.showMessageDialog(null,"Driver Loaded.");
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","HossainKabir");
		JOptionPane.showMessageDialog(null,"connection Done.");
		//s = c.createStatement();//create statement
		}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
	}
	
	void ExecuteQuery(String query){
		try{
		s = c.createStatement();
		s.executeUpdate(query);//getting result
		}catch(Exception e){JOptionPane.showMessageDialog(null,e);}
	}
	
	ResultSet ExecuteQuerySentResult(String query){
		try{
		s = c.createStatement();
		r=s.executeQuery(query);
		}catch(Exception e){JOptionPane.showMessageDialog(null,e);}
		return r;
	}
	
	void closeDBConnection(){
		try
		{
			if(r!=null)
				r.close();

			if(s!=null)
				s.close();

			if(c!=null)
				c.close();
		}
		catch(Exception ex){}
		JOptionPane.showMessageDialog(null,"DB Connecton Closed.");
	}

}
