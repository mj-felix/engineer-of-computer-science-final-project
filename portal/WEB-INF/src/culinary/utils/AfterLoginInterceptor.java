package culinary.utils;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AfterLoginInterceptor implements Interceptor {
	
	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map session = actionInvocation.getInvocationContext().getSession();
		purgeStaleTokens(session);
		User user = (User) session.get("user");
		if (user != null) {
			String actionName = actionInvocation.getInvocationContext().getName();
			System.out.println(actionName);
			if(actionName.equals("ShowSearch")) return "showSearch";
			else if(actionName.equals("ShowSearchTitle")) return "showSearchTitle";
			else if(actionName.equals("ShowCatalogue")) return "showCatalogue";
			
			
			else return "showSearch";
		}
		return actionInvocation.invoke();
		
	}
	
	private void purgeStaleTokens (Map session ){

		Object userToken = session.get( "user" );
		if ( !( userToken instanceof User ) ) session.remove ( "user" ) ;
		
	}
}
