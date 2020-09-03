package Schedule;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Schedule.model.User;



@SuppressWarnings("serial")
public class ScheduleServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedUser = (User) session.getAttribute("loggedUser");
		
		Map<String, Object> answer = new LinkedHashMap<>();
		answer.put("status", "success");
		answer.put("logged", loggedUser != null);
		answer.put("user", loggedUser);
		
		String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
		
		System.out.println(jsonAnswer);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(jsonAnswer);
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
