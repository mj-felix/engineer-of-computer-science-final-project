package culinary.secure.admin;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;


public class EditCategory extends ActionSupport implements
ServletContextAware {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
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

		int count = new SQLQuery(context.getAttribute("dbConn")).update("UPDATE CATEGORIES SET NAME='"+name+"' WHERE ID='"+key+"';");
		if (count > 0) {
			return SUCCESS;
		} else {
			addFieldError("name", getText("editcategory.err.generic"));
			return INPUT;
		}
	}

}
