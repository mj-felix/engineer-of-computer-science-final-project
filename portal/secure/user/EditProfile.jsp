<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="editprofile.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="editprofile.h4" /></h4>

	<s:form action="EditProfile" namespace="/secure">
		<s:textfield key="editprofile.email" name="email" />		
		<s:textfield key="editprofile.username" name="username" />
		<s:textfield key="editprofile.dateOfBirth" id="datepicker"
			name="dateOfBirth" />
		<s:textfield key="editprofile.country" name="country" />
		<s:textfield key="editprofile.www" name="www" />
		<s:radio key="editprofile.sex" name="sex" list="#{'1':'K','2':'M'}"/>
		<s:submit key="editprofile.submit" />
	</s:form>



</div>

<script type="text/javascript" language="JavaScript">
	$(function() {
		$('label[for=EditProfile_sex1]').text('<s:text name="editprofile.female" />');
		$('label[for=EditProfile_sex2]').text('<s:text name="editprofile.male" />');

		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : "-1D",
			minDate: "-100Y"
		});
		
		if(portal.utils.getParam('fresh') == '1'){
			$('#EditProfile_email').val('<s:property value="#session.user.email"/>');
			$('#EditProfile_username').val('<s:property value="#session.user.username"/>');
			$('#datepicker').val('<s:date name="%{#session.user.dob}" format="yyyy-MM-dd" />');
			$('#EditProfile_country').val('<s:property value="#session.user.country"/>');
			$('#EditProfile_www').val('<s:property value="#session.user.www"/>');
			var sex = '<s:property value="#session.user.sex"/>' 
			if(sex == '1') $('#EditProfile_sex1').attr('checked', true);
			else if (sex == '2') $('#EditProfile_sex2').attr('checked', true);
			
			
		}
	});

	
</script>