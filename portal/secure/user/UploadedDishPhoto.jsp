<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="uploadeddishphoto.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="uploadeddishphoto.h4" /></h4>
<s:text name="uploadeddishphoto.added" /> <i><s:property value='%{#session.user.username}' /><s:property value="dishId" /><s:property value="picFileName" /></i> <s:text name="uploadeddishphoto.awaitapproval" /><br />
<s:text name="uploadeddishphoto.goto" /> <a href="<s:url action='ShowMyDishes' namespace='/secure' includeParams='none' />"><s:text name="uploadeddishphoto.mydishes" /></a>.




</div>
