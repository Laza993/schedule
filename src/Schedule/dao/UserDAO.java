package Schedule.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Schedule.model.Role;
import Schedule.model.User;

public class UserDAO {

	public static User getUser(String username, String password) throws Exception {
		User user = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM users WHERE user_name = ? AND passwords = ?";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, username);
			stmt.setString(index++, password);
			rset = stmt.executeQuery();
			if(rset.next()) {
				int ind = 1;
				String usernames = rset.getString(ind++);
				ind++;
				String roles = rset.getString(ind++);
				Role role = Role.valueOf(roles);
				user = new User(usernames, null, role);	
			}
		} finally {
			try {stmt.close();} catch (Exception e) {e.printStackTrace();}
			try {rset.close();} catch (Exception e) {e.printStackTrace();}
		}
		return user;
	}
	
	public static User getUser(String username) throws Exception {
		User user = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM users WHERE user_name = ?";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, username);
			rset = stmt.executeQuery();
			if(rset.next()) {
				int ind = 1;
				String usernames = rset.getString(ind++);
				ind++;
				String roles = rset.getString(ind++);
				Role role = Role.valueOf(roles);
				user = new User(usernames, null, role);	
			}
		} finally {
			try {stmt.close();} catch (Exception e) {e.printStackTrace();}
			try {rset.close();} catch (Exception e) {e.printStackTrace();}
		}
		return user;
	}

	public static boolean register(User user) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO users (user_name, passwords, roles) VALUES (?, ?, ?)";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, user.getUserName());
			stmt.setString(index++, user.getPassword());
			stmt.setString(index++, user.getRole().toString());
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception e) {e.printStackTrace();}
		}
	}
}
