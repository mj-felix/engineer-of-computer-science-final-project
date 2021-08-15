<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="edituser.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="edituser.h4" /></h4>

	<s:form action="EditUser" namespace="/secure">
		<s:hidden name="id" />
		<s:textfield key="edituser.email" name="email" />
		<s:textfield key="edituser.username" name="username" />
		<s:textfield key="edituser.dateOfBirth" id="datepicker"
			name="dateOfBirth" />
		<s:textfield key="edituser.country" name="country" />
		<s:textfield key="edituser.www" name="www" />
		<s:radio label="edituser.sex" name="sex"
			list="#{'1':'Kobieta','2':'Mężczyzna'}" />
		<s:radio label="edituser.privilegeLevel" name="privilegeLevel"
			list="#{'0':'Użytkownik','1':'Administrator'}" />
		<s:submit key="edituser.submit" />
	</s:form>



</div>

<script type="text/javascript" language="JavaScript">
	$(function() {
		$('label[for=EditUser_sex1]').text('<s:text name="edituser.female" />');
		$('label[for=EditUser_sex2]').text('<s:text name="edituser.male" />');
		$('label[for=EditUser_privilegeLevel0]').text('<s:text name="edituser.user" />');
		$('label[for=EditUser_privilegeLevel1]').text('<s:text name="edituser.admin" />');
	
				
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : "-1D",
			minDate : "-100Y"
		});

	});

	
</script>