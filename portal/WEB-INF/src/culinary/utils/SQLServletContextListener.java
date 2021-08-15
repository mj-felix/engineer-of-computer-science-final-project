package culinary.utils;

import javax.servlet.*;
import java.sql.*;

public final class SQLServletContextListener
implements ServletContextListener {

    private ServletContext context = null;
	private Connection conn = null;
	

    public void contextInitialized(ServletContextEvent event) {
		try{
		context = event.getServletContext();
		
		String driverName = context.getInitParameter("driverName");
		String url = "jdbc:mysql://localhost/portal?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
		String uid = context.getInitParameter("uid");
		String pwd = context.getInitParameter("pwd");

		
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, uid, pwd);
        
      
        context.setAttribute("dbConn", conn);
		
		
		
	} catch (ClassNotFoundException  exc)  {  // the driver class not found
       System.out.println("Driver class not found");
       System.out.println(exc);
       
    } catch(SQLException exc) {  // connection failed
       System.out.println("Connection to " + context.getInitParameter("url") + " failed");
       System.out.println(exc);
       
	}
}

    public void contextDestroyed(ServletContextEvent event) {

        this.context = null;
		this.conn = null; 
    }
}
