package Schedule.lectures;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Schedule.dao.LectureDAO;

@SuppressWarnings("serial")
public class DeleteLectureServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idS = request.getParameter("delId");
			if(idS == null || idS == "") {
				response.sendRedirect("AllLecturesServlet");
				System.out.println("bad parameter");
				return;
			}
			int id = Integer.parseInt(idS);
			if(!LectureDAO.deleteLecture(id)) {
				response.sendRedirect("AllLecturesServlet");
				System.out.println("removal failed");
				return;
			};
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("AllLecturesServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
