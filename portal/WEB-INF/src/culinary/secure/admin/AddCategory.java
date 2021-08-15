package culinary.secure.admin;

import java.sql.ResultSet;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;


public class AddCategory extends ActionSupport implements
ServletContextAware {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String execute() throws Exception {
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM CATEGORIES WHERE NAME='"+name+"';");
		if(SQLQuery.getResultSetSize(rs)>0) {
			addFieldError( "name", getText( "addcategory.err.name.exists" ));
			return INPUT;
		}
System.out.println(name);
		int count = new SQLQuery(context.getAttribute("dbConn"))
				.insert("INSERT INTO CATEGORIES SET NAME='" + name + "';");
		if (count > 0) {
			return SUCCESS;
		} else {
			addFieldError("name", getText("addcategory.err.generic"));
			return INPUT;
		}
	}

}
