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

public class UploadProfilePhoto extends ActionSupport implements
		ServletRequestAware, ServletContextAware, UserAware {
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

	public String execute() throws IOException {

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
		File fileToCreate = new File(filePath, user.getUsername()+this.picFileName);
		System.out.println("File to create:" + fileToCreate);

		FileUtils.copyFile(this.pic, fileToCreate);

		// db
		ResultSet rs;
		try {
			rs = new SQLQuery(context.getAttribute("dbConn"))
					.select("SELECT * FROM IMAGES WHERE ID='" + user.getImgId()
							+ "';");

			if (SQLQuery.getResultSetSize(rs) == 1) {
				int count = new SQLQuery(context.getAttribute("dbConn"))
						.update("UPDATE IMAGES SET IPATH='"
								+ fileSystemPath + '/' +user.getUsername()+ this.picFileName
								+ "', ADDITIONDATE=CURDATE(), ADDITIONTIME=CURTIME(), STATUS='0' WHERE ID='"
								+ user.getImgId() + "';");
				//user.setiPath("");
			} else {

				long imgKey = new SQLQuery(context.getAttribute("dbConn"))
						.insert("INSERT INTO IMAGES SET IPATH='"
								+ fileSystemPath + '/' +user.getUsername()+ this.picFileName
								+ "', ADDITIONDATE=CURDATE(), ADDITIONTIME=CURTIME();",
								true);

				if (imgKey > 0) {
					// user.setiPath(this.picFileName);
					user.setImgId(imgKey);
					int count = new SQLQuery(context.getAttribute("dbConn"))
							.update("UPDATE USERS SET IMAGE_ID='" + imgKey
									+ "' WHERE ID='" + user.getId() + "';");
				}
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
