package Schedule.lectures;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Schedule.dao.LectureDAO;
import Schedule.model.Lecture;
import Schedule.model.Role;
import Schedule.model.User;

@SuppressWarnings("serial")
public class ViewLectureServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedUser = (User) session.getAttribute("loggedUser");

		try {
			if(loggedUser == null) {
				HashMap<String, Object> answer = new LinkedHashMap<>();
				answer.put("status", "failed");
				String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
				
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonAnswer);
				return;
			}
			if(!loggedUser.getRole().equals(Role.teacher)) {
				HashMap<String, Object> answer = new LinkedHashMap<>();
				answer.put("status", "failed");
				answer.put("explanation", "notAllowed");
				String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
				
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonAnswer);
				return;
			}
			
			String idS = request.getParameter("viewId");
			if(idS == null || idS == "") {
				HashMap<String, Object> answer = new LinkedHashMap<>();
				answer.put("status", "failed");
				answer.put("explanation", "emptyParam");
				String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
				
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonAnswer);
				return;
			}
			int id = Integer.parseInt(idS);
			Lecture lecture = LectureDAO.get((long)id);


			HashMap<String, Object> answer = new LinkedHashMap<>();
			answer.put("status", "success");
			answer.put("loggedUser", loggedUser);
			answer.put("lecture", lecture);
			
			String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonAnswer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
