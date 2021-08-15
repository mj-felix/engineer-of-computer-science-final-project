<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="changepassword.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="changepassword.h4" /></h4>

	<s:form action="ChangePassword" namespace="/secure">
		<s:password key="changepassword.password1" name="password1" />
		<s:password key="changepassword.password2" name="password2" />
		<s:submit key="changepassword.submit" />
	</s:form>
	<s:if test="hasActionErrors()">
		<div id="changePasswordExpressionErr">
			<s:actionerror />
		</div>
	</s:if>


</div>

<script type="text/javascript" language="JavaScript">
	$(function() {
		$('#a_sidebar_left_register').parent().remove();

		if ($('#changePasswordExpressionErr').length > 0) {
			$('input#Registration_password1')
					.parent()
					.parent()
					.before(
							'<tr><td colspan="2"><span class="errorMessage">'
									+ $(
											'div#changePasswordExpressionErr span.errorMessage')
											.html() + '</span></td></tr>');
		}
	});

	
</script>