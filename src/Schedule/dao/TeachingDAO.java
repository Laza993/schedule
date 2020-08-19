package Schedule.dao;

import java.sql.ResultSet;
import java.sql.Statement;

import Schedule.model.Teaching;

public class TeachingDAO {

	public static Teaching getTeachingById(int id) throws Exception {
		Teaching teaching = null;
		
		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM teaching WHERE id =" + id;
			stmt = ConnectionManager.getConnection().createStatement();
			
			rset = stmt.executeQuery(sql);
			if(rset.next()) {
				int index = 1;
				int ID =  rset.getInt(index++);
				String name = rset.getString(index++);
				
				teaching = new Teaching(ID, name);
			}
		} finally {
			try {stmt.close();} catch (Exception e) {e.printStackTrace();}
			try {rset.close();} catch (Exception e) {e.printStackTrace();}
		}
		
		return teaching;
	}

	public static Teaching getTeachingByName(String name) throws Exception {
		Teaching teaching = null;
		
		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM teaching WHERE namee = \"" + name + "\"";
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			if(rset.next()) {
				
				int index = 1;
				int ID =  rset.getInt(index++);
				String name1 = rset.getString(index++);
				
				teaching = new Teaching(ID, name1);
			}
		} finally {
			try {stmt.close();} catch (Exception e) {e.printStackTrace();}
			try {rset.close();} catch (Exception e) {e.printStackTrace();}
		}	
		return teaching;
	}

}
