package culinary.secure.admin;



import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;




public class BanComment extends ActionSupport implements
 ServletContextAware {
	
	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	private static final long serialVersionUID = 1L;

	private long id;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	private String result;

	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	private Object jsonModel;

	public Object getJsonModel() {
		return jsonModel;
	}

	public void setJsonModel(Object jsonModel) {
		this.jsonModel = jsonModel;
	}

	
	public String execute() throws Exception {
		result = "false";
		
	
			int count = new SQLQuery(context.getAttribute("dbConn"))
			.update("UPDATE COMMENTS SET STATUS='0' WHERE ID='"
					+ id + "';");
			
			result="true";


		
		
		
		setJsonModel(result);
		
		return SUCCESS;
	}

}
