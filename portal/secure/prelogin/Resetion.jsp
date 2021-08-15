<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="resetion.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="resetion.h4" /></h4>

	<s:form action="Resetion" namespace="/secure">
		<s:textfield key="resetion.email" name="email" />
		<s:textfield key="resetion.username" name="username" />
		<s:submit key="resetion.submit" />
	</s:form>

</div>
