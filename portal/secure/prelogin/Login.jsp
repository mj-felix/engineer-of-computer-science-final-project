<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="login.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="login.h4" /></h4>

	<s:form action="PerformLogin" namespace="/secure">
		<s:textfield key="login.email" name="email" />
		<s:password key="login.password" name="password" />
		<s:submit key="login.submit" />
	</s:form>

</div>

