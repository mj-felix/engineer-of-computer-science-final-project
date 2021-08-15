<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="editcat.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="editcat.h4" /></h4>

	<s:form action="EditCategory" namespace="/secure">
		<s:textfield key="editcat.name" name="name" />
		<s:hidden name="key" />
		<s:submit key="editcat.submit" />
	</s:form>
</div>



<script type="text/javascript" language="JavaScript">
$(function() {
	if ($('#EditCategory_name').val() == '')
	$('#EditCategory_name').val(new String(portal.utils.getParam('name')).replace(/\+/g," "));
	if ($('#EditCategory_key').val() == '')
	$('#EditCategory_key').val(portal.utils.getParam('cat'));
});
</script>