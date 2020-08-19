package Schedule;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import Schedule.dao.ConnectionManager;

public class InitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent event)  { 
         try {
        	 System.out.println("closing connection with database...");
        	 ConnectionManager.close();
        	 System.out.println("success!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }


    public void contextInitialized(ServletContextEvent event)  { 
         try {
        	System.out.println("opening connection with database...");
			ConnectionManager.open();
       	 	System.out.println("success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
