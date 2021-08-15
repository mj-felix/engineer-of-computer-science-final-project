<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="sessionexpired.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="sessionexpired.h4" /></h4>

	<s:text name="sessionexpired.goto" /> <a href="<s:url action='Login' namespace='/secure' />"><s:text name="sessionexpired.loginpage" /></a>.

</div>
