<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h3><s:text name="showmyachievements.h3" /></h3>

<s:if test="achievements.size()>0">
	<div class="content_diff">
		<h4><s:text name="showmyachievements.h4.achievements" /></h4>



		<table class="width700">
			<tr>
				<td class="first">#</td>
				<td class="first"><s:text name="showmyachievements.name" /></td>
				<td class="first"><s:text name="showmyachievements.desc" /></td>
				<td class="first"><s:text name="showmyachievements.date" /></td>
				<td class="first"><s:text name="showmyachievements.points" /></td>
			</tr>

			<s:iterator value="achievements" status="rowstatus">
				<tr>
					<td class="first"><s:property value="#rowstatus.count" /></td>
					<td><s:property value="top[0]" /></td>
					<td><s:property value="top[1]" /></td>
					<td><s:property value="top[3]" /></td>
					<td><s:property value="top[2]" /></td>
				</tr>



			</s:iterator>
		</table>
	</div>
</s:if>
<s:else>
	<div class="content_diff"><s:text name="showmyachievements.noachievements" /></div>
</s:else>

<s:if test="ranks.size()>0">
	<div class="content_diff">
		<h4><s:text name="showmyachievements.h4.ranks" /></h4>



		<table class="width700">
			<tr>
				<td class="first">#</td>
				<td class="first"><s:text name="showmyachievements.name" /></td>
				<td class="first"><s:text name="showmyachievements.desc" /></td>
				<td class="first"><s:text name="showmyachievements.date" /></td>
				<td class="first"><s:text name="showmyachievements.neededpoints" /></td>
			</tr>

			<s:iterator value="ranks" status="rowstatus">
				<tr>
					<td class="first"><s:property value="#rowstatus.count" /></td>
					<td><s:property value="top[0]" /></td>
					<td><s:property value="top[1]" /></td>
					<td><s:property value="top[3]" /></td>
					<td><s:property value="top[2]" /></td>
				</tr>



			</s:iterator>
		</table>
	</div>
</s:if>
<s:else>
	<div class="content_diff"><s:text name="showmyachievements.noranks" /></div>
</s:else>

