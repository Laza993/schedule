package Schedule.lectures;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Schedule.dao.LectureDAO;
import Schedule.dao.ScheduleToolKit;
import Schedule.dao.TeachingDAO;
import Schedule.model.Days;
import Schedule.model.Lecture;
import Schedule.model.Teaching;

@SuppressWarnings("serial")
public class EditLectureServlet extends HttpServlet {
       

    public EditLectureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idS = request.getParameter("ediTid");
			String day = request.getParameter("day");
			String group = request.getParameter("group");
			String sTime1 = request.getParameter("from");
			String sTime2 = request.getParameter("to");
			String clasroom = request.getParameter("clasroom");
			String teaching = request.getParameter("teaching");
			String subject = request.getParameter("subject");
			String teacher = request.getParameter("teacher");
			
			if(idS == "" || sTime1 == "" || sTime2 == "" || day == "" || group == "" || clasroom == "" || teaching == "" || subject == "" || teacher == ""){
				response.sendRedirect("ViewAddLectureServlet");
				return;
			}
			Long id = (long) Integer.parseInt(idS);
			Time from = ScheduleToolKit.StringToTime(sTime1);
			Time to = ScheduleToolKit.StringToTime(sTime2);
			Teaching teach = TeachingDAO.getTeachingByName(teaching);
			Days days = Days.valueOf(day);
			
			
			Lecture lecture = new Lecture(id, days, group, from, to, clasroom, teach, subject, teacher);
			System.out.println(lecture.toString());
			
			if(!LectureDAO.updateLecture(lecture)) {
				System.out.println("adding failed");
				return;
			};
			
			System.out.println("adding successful");
			response.sendRedirect("AllLecturesServlet");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
