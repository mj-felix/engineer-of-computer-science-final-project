package culinary.secure.admin;

import java.sql.ResultSet;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;


public class AddEditIngredient extends ActionSupport implements
ServletContextAware {
	
	private static final long serialVersionUID = 1L;

	private String[][] ingredients;
	
	public String[][] getIngredients() {
		return ingredients;
	}



	public void setIngredients(String[][] ingredients) {
		this.ingredients = ingredients;
	}



	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	
	public String execute() throws Exception {
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM INGREDIENTS ORDER BY NAME ASC;");
		ingredients = new String[SQLQuery.getResultSetSize(rs)][3];
		int i=0;
		while (rs.next()) {

	        long id = rs.getInt("ID");
	        String name = rs.getString("NAME");
	        String unit = rs.getString("UNIT");
	        ingredients[i][0] = "" + id;
	        ingredients[i][1] = name;
	        ingredients[i][2] = unit;
	        
	        
	        
	        i++;
	    }
		
		return SUCCESS;
	}

}
