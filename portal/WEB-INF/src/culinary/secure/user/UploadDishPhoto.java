package culinary.secure.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;

public class UploadDishPhoto extends ActionSupport implements
		ServletRequestAware, ServletContextAware, UserAware {
	
	private long dishId;
	
	public long getDishId() {
		return dishId;
	}

	public void setDishId(long dishId) {
		this.dishId = dishId;
	}

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	private static final long serialVersionUID = 1L;
	private HttpServletRequest servletRequest;

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public String execute() throws IOException, SQLException {
		
		ResultSet rs1 = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM DISHES WHERE USER_ID='"+user.getId()+"' AND ID='"+dishId+"';");
		ResultSet rs2 = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM USERDISHTODO WHERE USER_ID='"+user.getId()+"' AND DISH_ID='"+dishId+"';");
		
		if(SQLQuery.getResultSetSize(rs1)==0 && SQLQuery.getResultSetSize(rs2)==0) {
		System.out.println(SQLQuery.getResultSetSize(rs1));
		System.out.println(SQLQuery.getResultSetSize(rs2));
			return "sessionExpired";
			
		}

		System.out.println("File: " + pic);
		System.out.println("File name: " + picFileName);
		System.out.println("Content type: " + picContentType);
		System.out.println("File system path: " + fileSystemPath);
		
		if(pic==null) {
			addFieldError( "pic", getText( "upload.foto.err.nofile" ));
			return INPUT;
		}

		String filePath = servletRequest.getRealPath(fileSystemPath);
		System.out.println("Server path:" + filePath);
		File fileToCreate = new File(filePath, user.getUsername()+dishId+this.picFileName);
		System.out.println("File to create:" + fileToCreate);

		FileUtils.copyFile(this.pic, fileToCreate);

		// db
		
		try {
			

				long imgKey = new SQLQuery(context.getAttribute("dbConn"))
						.insert("INSERT INTO IMAGES SET IPATH='"
								+ fileSystemPath + '/' +user.getUsername()+dishId+ this.picFileName
								+ "', ADDITIONDATE=CURDATE(), ADDITIONTIME=CURTIME();",
								true);

				if (imgKey > 0) {

					int count = new SQLQuery(context.getAttribute("dbConn"))
							.insert("INSERT INTO DISHIMAGEUSER SET IMAGE_ID='" + imgKey
									+ "', USER_ID='"+user.getId()+"', DISH_ID='"+dishId+"';");
				}
		

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return SUCCESS;

	}

	File pic;
	String picContentType;
	String picFileName;
	String fileSystemPath;

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setFileSystemPath(String fileSystemPath) {
		this.fileSystemPath = fileSystemPath;
	}

}
