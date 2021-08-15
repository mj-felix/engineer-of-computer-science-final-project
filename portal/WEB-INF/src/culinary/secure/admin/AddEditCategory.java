package culinary.secure.admin;

import java.sql.ResultSet;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;


public class AddEditCategory extends ActionSupport implements
ServletContextAware {
	
	private static final long serialVersionUID = 1L;

	private String[][] categories;
	
	public String[][] getCategories() {
		return categories;
	}

	public void setCategories(String[][] categories) {
		this.categories = categories;
	}



	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	
	public String execute() throws Exception {
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM CATEGORIES ORDER BY NAME ASC;");
		categories = new String[SQLQuery.getResultSetSize(rs)][2];
		int i=0;
		while (rs.next()) {
	        // Get the data from the row using the column index
	        long id = rs.getInt("ID");
	        String name = rs.getString("NAME");
	        categories[i][0] = "" + id;
	        categories[i][1] = name;
	        
	        
	        
	        i++;
	    }
		
		return SUCCESS;
	}

}
