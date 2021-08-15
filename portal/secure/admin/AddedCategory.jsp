<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="addedcat.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="addedcat.h4" /></h4>

	<p>
		<s:text name="addedcat.newcat" /> <i><s:property value="name" /></i> <s:text name="addedcat.added" /> <s:text name="addedcat.goto" /> <a href="<s:url action='ShowAddCategory' namespace='/secure' />"><s:text name="addedcat.catlist" /></a>.
	</p>


</div>

