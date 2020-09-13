package Schedule.lectures;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Schedule.model.Role;
import Schedule.model.User;

@SuppressWarnings("serial")
public class ViewAddLectureServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			response.sendRedirect("Login.html");
			return;
		}
		if(!loggedUser.getRole().equals(Role.teacher)) {
			response.sendRedirect("ScheduleServlet");
			return;
		}
		try {
			
			Map<String, Object> answer = new LinkedHashMap<>();
			answer.put("status", "success");
			answer.put("logged", loggedUser != null);
			answer.put("user", loggedUser);
			
			String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
			
			System.out.println(jsonAnswer);
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonAnswer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
