package culinary.secure.user;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;


import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;


import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;


public class ShowCatalogue extends ActionSupport implements
ServletContextAware {

	private static final long serialVersionUID = 1L;

	
	private List categories;


	public List getCategories() {
		return categories;
	}



	public void setCategories(List categories) {
		this.categories = categories;
	}



	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	
	public String execute() throws Exception {
		
		categories = new ArrayList();
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT CATEGORIES.*, COUNT(DISHES.ID) AS 'COUNT' FROM CATEGORIES LEFT JOIN DISHES ON CATEGORIES.ID=DISHES.CATEGORY_ID AND DISHES.STATUS='1' GROUP BY CATEGORIES.ID ORDER BY CATEGORIES.NAME ASC;");

		while (rs.next()) {
			long catId = rs.getLong("ID");
			String catName = rs.getString("NAME");
			int count = rs.getInt("COUNT");
			
			String[] cat = new String[3];
			cat[0]=catId+"";
			cat[1]=catName;
			cat[2]=count+"";
			
			categories.add(cat);
	        
	        
	    }
		
		return SUCCESS;
	}

}
