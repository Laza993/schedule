package Schedule.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Schedule.model.Days;
import Schedule.model.Lecture;
import Schedule.model.Teaching;

public class LectureDAO {
	
	public static Lecture get(Long id) throws Exception {
		Lecture lecture = null;
		
		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM lecure WHERE id=" + id;
			
			stmt = ConnectionManager.getConnection().createStatement();
			
			rset = stmt.executeQuery(sql);
			if(rset.next()) {
				int index = 1;
				Long ID = (long) rset.getInt(index++);
				Days day =  Days.valueOf(rset.getString(index++));
				String group = rset.getString(index++);
				Time from = rset.getTime(index++);
				Time to = rset.getTime(index++);
				String classroom = rset.getString(index++);
				Teaching teaching = TeachingDAO.getTeachingById((rset.getInt(index++)));
				String subject = rset.getString(index++);
				String teacher = rset.getString(index++);
				
				lecture = new Lecture(ID, day, group, from, to, classroom, teaching, subject, teacher);
			}
		} finally {
			try {stmt.close();} catch (Exception e) {e.printStackTrace();}
			try {rset.close();} catch (Exception e) {e.printStackTrace();}
		}
		
		return lecture;
	}
	
	public static List<Lecture> getLectures() throws Exception {
		List<Lecture> lectures = new ArrayList<Lecture>();
		Lecture lecture = null;
		
		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM lecure";
			
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			while(rset.next()) {
				int index = 1;
				Long ID = (long) rset.getInt(index++);
				Days day =  Days.valueOf(rset.getString(index++));
				String group = rset.getString(index++);
				Time from = rset.getTime(index++);
				Time to = rset.getTime(index++);
				String classroom = rset.getString(index++);
				Teaching teaching = TeachingDAO.getTeachingById((rset.getInt(index++)));
				String subject = rset.getString(index++);
				String teacher = rset.getString(index++);
				
				lecture = new Lecture(ID, day, group, from, to, classroom, teaching, subject, teacher);
				if(lecture != null) {
					lectures.add(lecture);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {stmt.close();} catch (Exception e) {e.printStackTrace();}
			try {rset.close();} catch (Exception e) {e.printStackTrace();}
		}
		
		return lectures;
	}

	public static boolean addLecture(Lecture lecture) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO lecure (days, groupp, fromm, too, clasroom, teaching, Subjectt, teacher) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			
			int index = 1;
			stmt.setString(index++, lecture.getDay().name());
			stmt.setString(index++, lecture.getGroup());
			stmt.setTime(index++, lecture.getFrom());
			stmt.setTime(index++, lecture.getTo());
			stmt.setString(index++, lecture.getClassroom());
			stmt.setInt(index++, lecture.getTeaching().getId());
			stmt.setString(index++, lecture.getSubject());
			stmt.setString(index++, lecture.getTeacher());
			System.out.println(stmt);
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static boolean deleteLecture(int id) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM lecure WHERE id = ?";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception e) {e.printStackTrace();}
		}
	}

	
	public static boolean updateLecture(Lecture lecture) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE lecure SET days = ?, groupp = ?, fromm = ?, too = ?, clasroom = ?, teaching = ?, Subjectt = ?, teacher = ? WHERE id = ?";

			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			
			int index = 1;
			stmt.setString(index++, lecture.getDay().name());
			stmt.setString(index++, lecture.getGroup());
			stmt.setTime(index++, lecture.getFrom());
			stmt.setTime(index++, lecture.getTo());
			stmt.setString(index++, lecture.getClassroom());
			stmt.setInt(index++, lecture.getTeaching().getId());
			stmt.setString(index++, lecture.getSubject());
			stmt.setString(index++, lecture.getTeacher());
			long ID = (long) lecture.getId();
			stmt.setInt(index++, (int) ID);
			System.out.println(stmt);
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception e) {e.printStackTrace();}
		}
	}
}
