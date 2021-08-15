<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="user.username == ''">
	<h3><s:text name="showthisuser.h3.userunknown" /></h3>



	<div class="content_diff"><s:text name="showthisuser.userunknown" /></div>
</s:if>
<s:else>
	<h3>
		<s:property value="user.username" />
	</h3>



	<div class="content_diff">
		<h4><s:text name="showthisuser.h3.userdata" /></h4>

		<table style="width: 700px">
			<tr>
				<td class="first"><s:text name="showthisuser.photo" /></td>
				<td colspan="3"><s:if test='user.iPath!=""'>

						<img src="/portal<s:property value='user.iPath' />"
							style="width: 150px;" />

					</s:if></td>
			</tr>
			<tr>
				<td class="first"><s:text name="showthisuser.date" /></td>
				<td><s:date name="user.registrationDate" format="yyyy-MM-dd" />
					<s:property value="user.registrationTime" /></td>
				<td class="first"><s:text name="showthisuser.privilege" /></td>
				<td><s:if test='user.privilegeLevel==0'><s:text name="showthisuser.user" /></s:if> <s:else><s:text name="showthisuser.admin" /></s:else></td>
			</tr>
			<tr>
				<td class="first"><s:text name="showthisuser.xpoints" /></td>
				<td><s:property value="user.xpoints" /></td>
				<td class="first"><s:text name="showthisuser.rank" /></td>
				<td><s:property value="user.rank" /></td>
			</tr>
			<tr>
				<td class="first"><s:text name="showthisuser.xpointsre" /></td>
				<td><s:property value="user.xpointsreedemed" /></td>
				<td class="first"><s:text name="showthisuser.sex" /></td>
				<td><s:if test='user.sex==1'><s:text name="showthisuser.female" /></s:if> <s:if
						test='user.sex==2'><s:text name="showthisuser.male" /></s:if></td>
			</tr>
			<tr>
				<td class="first"><s:text name="showthisuser.country" /></td>
				<td id="AvgRateTd"><s:property value="user.country" /></td>
				<td class="first"><s:text name="showthisuser.www" /></td>

				<td><s:property value='user.www' /></td>

			</tr>

		</table>


	</div>
	<s:if test="dishes.size()>0">
		<div class="content_diff">
			<h4><s:text name="showthisuser.h4.dishes" /> <img class="plusMinus" id="dishes_togg" src="/portal/images/plus.png"/></h4><br>



			<table style="display:none" id="dishes_table" class="width700">
				<tr>
					<td class="first">#</td>
					<td class="first"><s:text name="showthisuser.dishname" /></td>
					<td class="first"><s:text name="showthisuser.cat" /></td>

				</tr>

				<s:iterator value="dishes" status="rowstatus">
					<tr>
						<td class="first"><s:property value="#rowstatus.count" /></td>
						<td><a
							href="<s:url action="ShowDish" namespace="/secure" includeParams="none">
							<s:param name="dishKey" value="top[0]"/>
						</s:url>"><s:property
									value="top[1]" /></a></td>
						<td><a
							href="<s:url action="ShowCategoryDishes" namespace="/secure" includeParams="none">
							<s:param name="catId" value="top[2]" />
						</s:url>"><s:property
									value="top[3]" /></a></td>

					</tr>



				</s:iterator>
			</table>
		</div>
	</s:if>
	<s:if test="dishesT.size()>0">
		<div class="content_diff">
			<h4><s:text name="showthisuser.h4.dishestodo" /> <img class="plusMinus" id="dishesT_togg" src="/portal/images/plus.png"/></h4><br>



			<table style="display:none" id="dishesT_table" class="width700">
				<tr>
					<td class="first">#</td>
					<td class="first"><s:text name="showthisuser.dishname" /></td>
					<td class="first"><s:text name="showthisuser.cat" /></td>

				</tr>

				<s:iterator value="dishesT" status="rowstatus">
					<tr id="trMyDish<s:property value="top[0]" />">
						<td class="first"><s:property value="#rowstatus.count" /></td>
						<td><a
							href="<s:url action="ShowDish" namespace="/secure" includeParams="none">
							<s:param name="dishKey" value="top[0]"/>
						</s:url>"><s:property
									value="top[1]" /></a></td>
						<td><a
							href="<s:url action="ShowCategoryDishes" namespace="/secure" includeParams="none">
							<s:param name="catId" value="top[2]" />
						</s:url>"><s:property
									value="top[3]" /></a></td>
				</s:iterator>
			</table>
		</div>
	</s:if>

</s:else>

<script type="text/javascript" language="JavaScript">
	$(function() {

		$('#dishes_togg').click(function () {
			$("#dishes_table").toggle();
			if($(this).attr('src')=='/portal/images/plus.png') $(this).attr('src','/portal/images/minus.png');
			else $(this).attr('src','/portal/images/plus.png');
		});  
		$('#dishesT_togg').click(function () {
			$("#dishesT_table").toggle();
			if($(this).attr('src')=='/portal/images/plus.png') $(this).attr('src','/portal/images/minus.png');
			else $(this).attr('src','/portal/images/plus.png');
		}); 
	});

	
</script>