package culinary.unsecure;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.Ingredient;
import culinary.utils.SQLQuery;



 
public class ShowSearch
    extends ActionSupport implements ServletContextAware{
 
	private static final long serialVersionUID = 4L;

	private List<Ingredient> ingr;

	
	
	
	public List<Ingredient> getIngr() {
		return ingr;
	}

	public void setIngr(List<Ingredient> ingr) {
		this.ingr = ingr;
	}

	public String execute() throws Exception {
		
			ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM INGREDIENTS ORDER BY NAME ASC;");
			ingr = new ArrayList<Ingredient>();
			int i=0;
			while (rs.next()) {

		        long id = rs.getLong("ID");
		        String name = rs.getString("NAME");
		
		       	ingr.add(new Ingredient(id,name));
		        
		       
		        
		        i++;
		    }
			
			
			return SUCCESS;
		

	}
 
	private ServletContext context;  
	public void setServletContext(ServletContext context) {  
	    this.context = context;  
	}
	

}
