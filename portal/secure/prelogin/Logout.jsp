<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<% session.invalidate(); %>

<h3><s:text name="logout.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="logout.h4" /></h4>

	<s:text name="logout.goto" /> <a href="<s:url action='Login' namespace='/secure' />"><s:text name="logout.loginpage" /></a>.

</div>