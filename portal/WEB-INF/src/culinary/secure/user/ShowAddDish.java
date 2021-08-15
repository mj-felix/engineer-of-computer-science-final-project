package culinary.secure.user;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.Category;
import culinary.utils.Ingredient;
import culinary.utils.SQLQuery;


public class ShowAddDish extends ActionSupport implements
ServletContextAware,SessionAware {
	private Map session;

	public void setSession(Map session) {
		this.session = session;
	} 
	private static final long serialVersionUID = 1L;

	private List<Ingredient> ingr;
	private List<Category> categ;
	
	

	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	
	public String execute() throws Exception {
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM INGREDIENTS ORDER BY NAME ASC;");
		ingr = new ArrayList<Ingredient>();
		int i=0;
		while (rs.next()) {

	        long id = rs.getInt("ID");
	        String name = rs.getString("NAME");
	        String unit = rs.getString("UNIT");
	        String iname= name + " ("+unit+")";
	       	ingr.add(new Ingredient(id,iname));
	        
	       
	        
	        i++;
	    }
		
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM CATEGORIES ORDER BY NAME ASC;");
		categ = new ArrayList<Category>();
		i=0;
		
		while (rs.next()) {

	        long id = rs.getInt("ID");
	        String name = rs.getString("NAME");
	        categ.add(new Category(id,name));
	       
	        
	        i++;
	    }
		session.put("categories", categ);
		session.put("ingredients", ingr);
		return SUCCESS;
	}

}
