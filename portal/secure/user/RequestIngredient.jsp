<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="requestingr.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="requestingr.h3" /></h4>

	<s:form action="RequestIngredient" namespace="/secure">
		<s:textfield key="requestingr.name" name="name" />
		<s:textfield key="requestingr.unit" name="unit" />
		<s:submit key="requestingr.submit" />
	</s:form>



</div>

