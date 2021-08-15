<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="registration.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="registration.h4" /></h4>

	<s:form action="Registration" namespace="/secure">
		<s:textfield key="registration.email" name="email" />
		<s:password key="registration.password1" name="password1" />
		<s:password key="registration.password2" name="password2" />
		<s:textfield key="registration.dateOfBirth" id="datepicker"
			name="dateOfBirth" />
		<s:textfield key="registration.username" name="username" />
		<s:submit key="registration.submit" />
	</s:form>
	<s:if test="hasActionErrors()">
		<div id="registrationPasswordExpressionErr">
			<s:actionerror />
		</div>
	</s:if>


</div>

<script type="text/javascript" language="JavaScript">
	$(function() {


		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : "-1D",
			minDate: "-100Y"
		});
		if ($('#registrationPasswordExpressionErr').length > 0) {
			$('input#Registration_password1')
					.parent()
					.parent()
					.before(
							'<tr><td colspan="2"><span class="errorMessage">'
									+ $(
											'div#registrationPasswordExpressionErr span.errorMessage')
											.html() + '</span></td></tr>');
		}
	});

	
</script>