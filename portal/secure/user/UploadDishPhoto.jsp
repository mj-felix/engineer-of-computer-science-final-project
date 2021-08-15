<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="uploaddishphoto.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="uploaddishphoto.h4" /></h4>

	<s:form action="UploadDishPhoto"  method="post" enctype="multipart/form-data">
	<s:hidden name='dishId' />
    			<s:file name="pic" key="uploaddishphoto.file" />
		<s:submit key="uploaddishphoto.submit"/>
		</s:form>
		<s:if test="hasActionErrors()">
		<div id="actionErrPhotoUpload" style="display:none">
			<s:actionerror />
		</div>
	</s:if>


</div>

<script type="text/javascript" language="JavaScript">
if ($('#actionErrPhotoUpload').length > 0) {
	$('input[name=pic]')
			.parent()
			.parent()
			.before(
					'<tr><td colspan="2"><span class="errorMessage">'
							+ $(
									'div#actionErrPhotoUpload span.errorMessage')
									.html() + '</span></td></tr>');
}
</script>