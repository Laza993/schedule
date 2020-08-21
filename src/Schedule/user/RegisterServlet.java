package Schedule.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if(password1 != null && password1 != "" && password1.equals(password2) && userName != "" && role != null) {
			User user = new User(userName, password1, role);
			try {
				if(UserDAO.register(user)){
					response.sendRedirect("Login.html");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			response.sendRedirect("Registration.html");
			return;
		}	
	}

}
