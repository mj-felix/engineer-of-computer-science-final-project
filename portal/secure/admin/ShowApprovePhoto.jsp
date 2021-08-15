<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="approvephoto.h3" /></h3>



<s:if test="avatarPhotos.length > 0">
	<div id="content_diff">
		<h4><s:text name="approvephoto.h4.avatar" /></h4>

		<table style="width: 600px" id="avatarPhotos">
			<s:iterator value="avatarPhotos" status="rowstatus">
				<tr id="trPhoto<s:property value="top[0]" />">
					<td class="first"><s:property value="#rowstatus.count" /></td>
					<td><img src="/portal<s:property value="top[1]" />"
						style="width: 100px;" /></td>
					<td><a
						href="#"
						rel="<s:url action="ApprovePhoto" namespace="/secure" />|1|<s:property value="top[0]" />" class="actionLink"><s:text name="approvephoto.approve" /></a></td>
					<td><a
						href="#"
						rel="<s:url action="ApprovePhoto" namespace="/secure" />|-1|<s:property value="top[0]" />" class="actionLink"><s:text name="approvephoto.ban" /></a></td>
				</tr>
			</s:iterator>
		</table>
	</div>
</s:if>
<s:else>
<div id="content_diff">
		<s:text name="approvephoto.noavatars" />
</div>
</s:else>

<s:if test="dishPhotos.length > 0">
	<div id="content_diff">
		<h4><s:text name="approvephoto.h4.dish" /></h4>

		<table style="width: 600px" id="dishPhotos">
			<s:iterator value="dishPhotos" status="rowstatus">
				<tr id="trPhoto<s:property value="top[0]" />">
					<td class="first"><s:property value="#rowstatus.count" /></td>
					<td><img src="/portal<s:property value="top[1]" />"
						style="width: 100px;" /></td>
					<td><a
						href="#"
						rel="<s:url action="ApprovePhoto" namespace="/secure" />|1|<s:property value="top[0]" />" class="actionLink"><s:text name="approvephoto.approve" /></a></td>
					<td><a
						href="#"
						rel="<s:url action="ApprovePhoto" namespace="/secure" />|-1|<s:property value="top[0]" />" class="actionLink"><s:text name="approvephoto.ban" /></a></td>
				</tr>
			</s:iterator>
		</table>
	</div>
</s:if>
<s:else>
<div id="content_diff">
		<s:text name="approvephoto.nodishes" />
</div>
</s:else>


<script type="text/javascript" language="JavaScript">
	$('#avatarPhotos,#dishPhotos').find(".actionLink").click(function(e){
		var rel = $(this).attr('rel').split('|');
		portal.admin.model.sendApprovePhoto(rel[0],rel[1],rel[2]);
		e.preventDefault();
		e.stopPropagation();
	});

	
</script>