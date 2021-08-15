<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="editingr.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="editingr.h4" /></h4>

	<s:form action="EditIngredient" namespace="/secure">
		<s:textfield key="editingr.name" name="name" />
		<s:textfield key="editingr.unit" name="unit" />
		<s:hidden name="key" />
		<s:submit key="editingr.submit" />
	</s:form>
</div>



<script type="text/javascript" language="JavaScript">
$(function() {
	if ($('#EditIngredient_name').val() == '')
	$('#EditIngredient_name').val(new String(portal.utils.getParam('name')).replace(/\+/g," "));
	if ($('#EditIngredient_unit').val() == '')
	$('#EditIngredient_unit').val(new String(portal.utils.getParam('unit')).replace(/\+/g," "));
	if ($('#EditIngredient_key').val() == '')
	$('#EditIngredient_key').val(portal.utils.getParam('ing'));
});
</script>