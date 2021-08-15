<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="catId == ''">
<h3>
	<s:text name="showdish.h3.dishunknown" />
</h3>



<div class="content_diff">
	<s:text name="showdish.dishunknown" />
</div>
</s:if>
<s:else>
<h3>
	<s:property value="name" />
</h3>



<div class="content_diff">
	<h4><s:text name="showdish.dishdetails" /></h4>

	<table style="width: 700px">
		<tr>
			<td class="first"><s:text name="showdish.catdish" /></td>
			<td colspan="3"><a href="<s:url action="ShowCategoryDishes" namespace="/unsecure" includeParams="none">
							<s:param name="catId" value="catId" />
						</s:url>"><s:property value="catName" /></a></td>
		</tr>
		<tr>
			<td class="first"><s:text name="showdish.additiondate" /></td>
			<td><s:date name="additiondate" format="yyyy-MM-dd" /> <s:property
					value="additiontime" /></td>
			<td class="first"><s:text name="showdish.autor" /></td>
			<td><s:property value="userName" /></td>
		</tr>
		<tr>
			<td class="first"><s:text name="showdish.amountofcal" /></td>
			<td><s:property value="amountofcalories" /></td>
			<td class="first"><s:text name="showdish.preptime" /></td>
			<td><s:property value="preptime" /></td>
		</tr>
		<tr>
			<td class="first"><s:text name="showdish.difflevel" /></td>
			<td><s:if test="difficultylevel==0"><s:text name="showdish.easy" /></s:if> <s:if
					test="difficultylevel==1"><s:text name="showdish.soso" /></s:if> <s:if
					test="difficultylevel==2"><s:text name="showdish.difficult" /></s:if></td>
			<td class="first"><s:text name="showdish.size" /></td>
			<td><s:property value="dsize" /></td>
		</tr>
		<tr>
			<td class="first"><s:text name="showdish.avgrate" /></td>
			<td id="AvgRateTd"><s:property value="averagerate" /></td>
			<td class="first"><s:text name="showdish.yourrate" /></td>
			
			<td id="SendRateTd">
				<a href="<s:url action='Login' namespace='/secure' />" id="a_sidebar_left_login">Logowanie</a> / 
				<a href="<s:url action='Register' namespace='/secure' />" id="a_sidebar_left_register">Rejestracja</a>
			</td>
		

		</tr>

	</table>


</div>
<s:if test="photos.size()>0">
<div class="content_diff">
	<h4><s:text name="showdish.h4.photos" /></h4>
	<table class="width700">
	<s:iterator value="photos" status="rowstatus">
	 	<tr>
	 		<td rowspan='4'><img src="/portal<s:property value='top[0]' />" style="width:150px;"/></td>
	 		<td class="first"><s:text name="showdish.addedby" /></td>

		</tr>
		<tr>
			<td><s:property value="top[4]" /></td>
		</tr>
		<tr>
			<td class="first"><s:text name="showdish.photodate" /></td>
		</tr>
		<tr>
			<td><s:property value="top[1]" /> <s:property value="top[2]" /></td>
		</tr>


	</s:iterator>
	</table>
</div>
</s:if>

<div id="content_diff">
	<h4><s:text name="showdish.h4.ingr" /></h4>
	<table>
	<s:iterator value="ingredients" status="rowstatus">
	 <tr>
			<td class="first"><s:property value="top[0]" /></td>
			<td><s:property value="top[2]" /></td>
			<td><s:property value="top[1]" /></td>
		</tr>


	</s:iterator>
	</table>
</div>

<div id="content_diff">
	<h4><s:text name="showdish.h4.desc" /></h4>

	<table style="width: 700px">
		<tr>
			<td class="first" style="text-align:left;"><s:property value="description" escape="false" /></td>
		</tr>
	</table>


</div>
<s:if test="comments.size()>0">
<div class="content_diff">
	<h4><s:text name="showdish.h4.comments" /></h4>
	<table style="width: 700px" id="table_comments">
	<s:iterator value="comments" status="rowstatus">
	 <tr id="trComment<s:property value="top[5]" />">
			<td class="first"  style="width:200px"><s:property value="top[0]" /> @ <s:property value="top[3]" /> <s:property value="top[4]" /></td>
			<td><s:property value="top[2]" escape="false"/></td>
			<s:if test="%{#session.user.privilegeLevel==1}">
			<td class="first"><a href="#" class="actionLinkBanComment" rel="<s:url action="BanComment" namespace="/secure" includeParams="none" />|<s:property value="top[5]" />"><s:text name="showdish.block" /></a></td>
			</s:if>

		</tr>


	</s:iterator>
	</table>
</div>
</s:if>



</s:else>