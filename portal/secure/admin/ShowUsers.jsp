<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="showusers.h3" /></h3>



<s:if test="users.length > 0">
	<div id="content_diff">
		<h4><s:text name="showusers.h4" /></h4>

	
		<table id="users">
			<tr>
				<td class="first">#</td>
				<td class="first"><s:text name="showusers.nick" /></td>
				<td class="first"><s:text name="showusers.email" /></td>
				<td class="first"><s:text name="showusers.date" /></td>
				<td class="first"><s:text name="showusers.privilege" /></td>
				<td class="first"><s:text name="showusers.action" /></td>
			</tr>

			<s:iterator value="users" status="rowstatus">
				<tr id="trUser<s:property value="top.id" />">
					<td class="first"><s:property value="#rowstatus.count" /></td>
					<td><a href="<s:url action="ShowThisUser" namespace="/secure" includeParams="none">
							<s:param name="userId" value="top.id"/>
						</s:url>"><s:property value="top.username" /></a>
					
					</td>
					<td><s:property value="top.email" /></td>
					<td><s:date name="top.registrationDate" format="yyyy-MM-dd" /></td>
					<td><s:if test='top.privilegeLevel=="0"'>
					<s:text name="showusers.user" />
					</s:if> <s:elseif test='top.privilegeLevel=="1"'>
					<s:text name="showusers.admin" />
					</s:elseif></td>
					<s:if test="top.status == 1">
						<td><a href="#"
							rel="<s:url action="BanUser" namespace="/secure" />|-1|<s:property value="top.id" />"
							class="actionLinkBan"><s:text name="showusers.block" /></a>
					</s:if>
					<s:else>
						<td><a href="#"
							rel="<s:url action="BanUser" namespace="/secure" />|1|<s:property value="top.id" />"
							class="actionLinkBan"><s:text name="showusers.unblock" /></a>
					</s:else>
					/
					<a href="#" rel="<s:property value="top.id" />"
						class="actionLinkEdit"><s:text name="showusers.edit" /></a>
					</td>


				</tr>
				<tr style="display: none"
					id="trUser<s:property value="top.id" />_edit">
					<td class="first"></td>
					<td colspan="5"><s:form action="EditUser"
							namespace="/secure">
							<s:hidden name="id"/>
							<s:textfield key="showusers.edit.email" name="email" />
							<s:textfield key="showusers.edit.username" name="username" />
							<s:date name="dob" id="dobId" format="yyyy-MM-dd"/>
							<s:textfield key="showusers.edit.dateOfBirth" 
								name="dateOfBirth" value="%{dobId}" />
							<s:textfield key="showusers.edit.country" name="country" />
							<s:textfield key="showusers.edit.www" name="www" />
							<s:radio key="showusers.edit.sex" name="sex"
								list="#{'1':'Kobieta','2':'Mężczyzna'}" />
							<s:radio key="showusers.edit.privilegeLevel" name="privilegeLevel"
								list="#{'0':'Użytkownik','1':'Administrator'}" />
							<s:submit key="showusers.edit.submit" />
						</s:form></td>
				</tr>
			</s:iterator>
		</table>
	</div>
</s:if>
<s:else>
	<div id="content_diff"><s:text name="showusers.nousers" /></div>
</s:else>


<script type="text/javascript" language="JavaScript">
	
	
	$(function() {
		$('label[for=EditUser_sex1]').text('<s:text name="showusers.edit.female" />');
		$('label[for=EditUser_sex2]').text('<s:text name="showusers.edit.male" />');
		$('label[for=EditUser_privilegeLevel0]').text('<s:text name="showusers.edit.user" />');
		$('label[for=EditUser_privilegeLevel1]').text('<s:text name="showusers.edit.admin" />');

		$("input[name=dateOfBirth]").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : "-1D",
			minDate: "-100Y"
		});
		$('#users').find(".actionLinkBan").click(function(e) {
			var rel = $(this).attr('rel').split('|');
			portal.admin.model.sendBanUser(rel[0], rel[1], rel[2]);
			e.preventDefault();
			e.stopPropagation();
		});

		$('#users').find(".actionLinkEdit").click(function(e) {
			var rel = $(this).attr('rel');
			$('#users tr#trUser' + rel + "_edit").toggle("slow");
			e.preventDefault();
			e.stopPropagation();
		});

	});

	
</script>