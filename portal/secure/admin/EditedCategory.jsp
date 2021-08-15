<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="editedcat.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="editedcat.h4" /></h4>

	<p>
		<s:text name="editedcat.newcat" /> <i><s:property value="name" /></i>. <s:text name="editedcat.goto" /> <a href="<s:url action='ShowAddCategory' namespace='/secure' />"><s:text name="editedcat.catlist" /></a>.
	</p>


</div>

<script type="text/javascript" language="JavaScript">
</script>