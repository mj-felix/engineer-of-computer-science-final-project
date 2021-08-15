<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="editeduser.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="editeduser.h4" /></h4>

	<p>
		<s:text name="editeduser.editeduser" /> <i><s:property value="username" />. <s:text name="editeduser.goto" /> <a href="<s:url action='ShowUsers' namespace='/secure' />"><s:text name="editeduser.userlist" /></a>.
	</p>


</div>

<script type="text/javascript" language="JavaScript">
</script>