package Schedule.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Schedule.dao.UserDAO;
import Schedule.model.User;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			User user = UserDAO.getUser(username, password);
			if(user == null) {
				response.sendRedirect("Login.html");
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("loggedUser", user);
			response.sendRedirect("ScheduleServlet");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
