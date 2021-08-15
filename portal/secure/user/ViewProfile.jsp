<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="viewprofile.h3" /></h3>

<div id="content_diff">

	<h4>
		<s:text name="viewprofile.uploadphoto" /><img class="plusMinus"
			src="/portal/images/plus.png" />
	</h4>
	<br>

	<s:form action="UploadProfilePhoto" method="post"
		enctype="multipart/form-data">
		<s:file name="pic" key="viewprofile.file" />
		<s:submit key="viewprofile.submit"/>
	</s:form>
</div>

<div id="content_diff">
	<h4><s:text name="viewprofile.h4" /></h4>
	<s:if test="picFileName != null">
		<s:text name="viewprofile.photouploaded" /> <i><s:property value='%{#session.user.username}' /><s:property value="picFileName" /></i> <s:text name="viewprofile.awaitapproval" />
	</s:if>
	
	<s:if test="msg != null">
		<s:property value="msg" />
	</s:if>


	<table class="width700">
		<s:if test='%{#session.user.iPath!=""}'>
			<tr>
				<td class="first"><s:text name="viewprofile.photo" /></td>
				<td><img
					src="/portal<s:property value='%{#session.user.iPath}' />"
					style="width: 150px;" /></td>
			</tr>
		</s:if>

		<tr>
			<td class="first"><s:text name="viewprofile.nick" /></td>
			<td><b><s:property value="%{#session.user.username}" /></b></td>
		</tr>
		<tr>
			<td class="first"><s:text name="viewprofile.email" /></td>
			<td><b><s:property value="%{#session.user.email}" /></b></td>
		</tr>
		<tr>
			<td class="first"><s:text name="viewprofile.date" /></td>
			<td><b><s:date name="%{#session.user.registrationDate}"
						format="yyyy-MM-dd" /></b></td>
		</tr>
		<tr>
			<td class="first"><s:text name="viewprofile.dob" /></td>
			<td><b><s:date name="%{#session.user.dob}"
						format="yyyy-MM-dd" /></b></td>
		</tr>
		<tr>
			<td class="first"><s:text name="viewprofile.xpoints" /></td>
			<td><b><s:property value="%{#session.user.xpoints}" /></b></td>
		</tr>
		<tr>
			<td class="first"><s:text name="viewprofile.xpointsre" />
			</td>
			<td><b><s:property value="%{#session.user.xpointsreedemed}" /></b></td>
		</tr>
		<tr>
			<td class="first"><s:text name="viewprofile.rank" />
			</td>
			<td><b><s:property value="%{#session.user.rank}" /></b></td>
		</tr>
		<tr>
			<td class="first"><s:text name="viewprofile.country" /></td>
			<td><b><s:property value="%{#session.user.country}" /></b></td>
		</tr>
		<tr>
			<td class="first"><s:text name="viewprofile.www" /></td>
			<td><b><s:property value="%{#session.user.www}" /></b></td>
		</tr>
		<tr>
			<td class="first"><s:text name="viewprofile.sex" /></td>
			<td><b> <s:if test='%{#session.user.sex=="1"}'><s:text name="viewprofile.female" />
		</s:if> <s:elseif test='%{#session.user.sex=="2"}'><s:text name="viewprofile.male" />
		</s:elseif>

			</b></td>
		</tr>
		<tr>
			<td class="first" colspan="2"><a
				href="<s:url action="ShowEditProfile" namespace="/secure">
							<s:param name="fresh" value="1" />
						</s:url>"><s:text name="viewprofile.edit" /></a></td>
		</tr>

	</table>

</div>



<script type="text/javascript" language="JavaScript">
	$(function() {

		$("#UploadProfilePhoto").hide();

		$('img.plusMinus').click(function () {
			$("#UploadProfilePhoto").toggle("slow");
			if($(this).attr('src')=='/portal/images/plus.png') $(this).attr('src','/portal/images/minus.png');
			else $(this).attr('src','/portal/images/plus.png');
		});  
		
		var picFileName = portal.utils.getParam('picFileName');
		if (picFileName!='' && picFileName!=null) {
			$('div#addedPhoto i').html("<s:property value="%{#session.user.username}" />"+picFileName);
			$('div#addedPhoto').show();
			
		}
		
		if (portal.utils.getParam('profileUpdated')=='1') {
			$('div#editedProfile').show();
			
		}
		
		
	});

	
</script>