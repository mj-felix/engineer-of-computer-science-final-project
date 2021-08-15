<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="activation.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="activation.h4" /></h4>

	<s:form action="Activation" namespace="/secure">
		<s:textfield key="activation.email" name="email" />
		<s:textfield key="activation.key" name="key" />
		<s:submit key="activation.submit" />
	</s:form>

</div>

<script type="text/javascript" language="JavaScript">
	$(function() {
	
		if ($('#Activation_email').val()=='') $('#Activation_email').val(portal.utils.getParam('email')||'');
		if ($('#Activation_key').val()=='') $('#Activation_key').val(portal.utils.getParam('key')||'');
	});
</script>