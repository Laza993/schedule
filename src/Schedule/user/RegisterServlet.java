package Schedule.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Schedule.dao.UserDAO;
import Schedule.model.Role;
import Schedule.model.User;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username1");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String roleStr = request.getParameter("role");
		Role role = Role.valueOf(roleStr);
		System.out.println(userName);
		HashMap<String, Object> answer = new LinkedHashMap<>();
		
		try {
			if(password1 == null || password1 == "" || !password1.equals(password2) || userName == "" || role == null){
				answer.put("status", "failed");
				answer.put("explanation", "bad content");
				String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
				System.out.println(answer);
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonAnswer);
				return;
			}
			if(UserDAO.getUser(userName) != null) {
				answer.put("status", "failed");
				answer.put("explanation", "username already exists");
				String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonAnswer);
				System.out.println(answer);
				return;
			}
			User user = new User(userName, password1, role);
			UserDAO.register(user);
			answer.put("status", "success");
			String jsonAnswer = new ObjectMapper().writeValueAsString(answer);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonAnswer);
			System.out.println(answer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
