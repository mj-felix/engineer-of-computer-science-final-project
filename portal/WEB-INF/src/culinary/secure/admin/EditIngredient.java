package culinary.secure.admin;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;


public class EditIngredient extends ActionSupport implements
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

	private long key;
	
	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
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

		int count = new SQLQuery(context.getAttribute("dbConn")).update("UPDATE INGREDIENTS SET NAME='"+name+"', UNIT='"+unit+"' WHERE ID='"+key+"';");
		if (count > 0) {
			return SUCCESS;
		} else {
			addFieldError("name", getText("editingredient.err.generic"));
			return INPUT;
		}
	}

}
