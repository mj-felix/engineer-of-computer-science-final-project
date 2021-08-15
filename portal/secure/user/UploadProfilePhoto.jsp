<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="uploadprofilephoto.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="uploadprofilephoto.h4" /></h4>

	<s:form action="UploadProfilePhoto"  method="post" enctype="multipart/form-data">
    			<s:file name="pic" key="uploadprofilephoto.file" />
		<s:submit key="uploadprofilephoto.submit"/>
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