package culinary.secure.admin;

import java.sql.ResultSet;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;


public class AddIngredient extends ActionSupport implements
ServletContextAware {
	
	private static final long serialVersionUID = 1L;

	private String name;
	private String unit;
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

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
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM INGREDIENTS WHERE NAME='"+name+"';");
		if(SQLQuery.getResultSetSize(rs)>0) {
			addFieldError( "name", getText( "addingredient.err.name.exists" ));
			return INPUT;
		}

		int count = new SQLQuery(context.getAttribute("dbConn"))
				.insert("INSERT INTO INGREDIENTS SET NAME='" + name + "', UNIT='"+unit+"';");
		if (count > 0) {
			return SUCCESS;
		} else {
			addFieldError("name", getText("addingredient.err.generic"));
			return INPUT;
		}
	}

}
