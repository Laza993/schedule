package Schedule.dao;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ScheduleToolKit {
	
	public static Time StringToTime(String str) throws Exception {
		DateFormat dtf = new SimpleDateFormat("hh:mm");
		Time time = null;
		if(str != null) {
			Long longTime = (dtf.parse(str).getTime());
			time = new Time(longTime);
		}
		return time;
	}

}
