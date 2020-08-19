package Schedule.model;


import java.sql.Time;

public class Lecture {
	private Long id;
	private Days day;
	private String group;
	private Time from;
	private Time to;
	private String classroom;
	private Teaching teaching;
	private String subject;
	private String teacher;
	public Lecture(Long id, Days day, String group, Time from, Time to, String classroom, Teaching teaching,
			String subject, String teacher) {
		super();
		this.id = id;
		this.day = day;
		this.group = group;
		this.from = from;
		this.to = to;
		this.classroom = classroom;
		this.teaching = teaching;
		this.subject = subject;
		this.teacher = teacher;
	}
	public Lecture(Days day, String group, Time from, Time to, String classroom, Teaching teaching, String subject,
			String teacher) {
		super();
		this.day = day;
		this.group = group;
		this.from = from;
		this.to = to;
		this.classroom = classroom;
		this.teaching = teaching;
		this.subject = subject;
		this.teacher = teacher;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Days getDay() {
		return day;
	}
	public void setDay(Days day) {
		this.day = day;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Time getFrom() {
		return from;
	}
	public void setFrom(Time from) {
		this.from = from;
	}
	public Time getTo() {
		return to;
	}
	public void setTo(Time to) {
		this.to = to;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public Teaching getTeaching() {
		return teaching;
	}
	public void setTeaching(Teaching teaching) {
		this.teaching = teaching;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "Lecture [day=" + day + ", group=" + group + ", from=" + from + ", to=" + to + ", classroom=" + classroom
				+ ", teaching=" + teaching.getName() + ", subject=" + subject + ", teacher=" + teacher + "]";
	}
	
	
	
}
	
	