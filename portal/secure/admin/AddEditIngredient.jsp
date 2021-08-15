<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="addeditingr.h3" /></h3>

<div id="content_diff">
<s:if test="ingredients.length > 0">
	<h4><s:text name="addeditingr.h4.addingr" /> <img class="plusMinus" src="/portal/images/plus.png"/></h4><br>
</s:if>
<s:else>
	<h4><s:text name="addeditingr.h4.addingr" /></h4>
</s:else>
	<s:form action="AddIngredient" namespace="/secure">
		<s:textfield key="addeditingr.name" name="name" />
		<s:textfield key="addeditingr.unit" name="unit" />
		<s:submit key="addeditingr.submit" />
	</s:form>
</div>

<s:if test="ingredients.length > 0">
	<div id="content_diff">
		<h4><s:text name="addeditingr.h4.editingr" /></h4>

		<table>
			<s:iterator value="ingredients" status="rowstatus">
				<tr>
					<td class="first"><s:property value="#rowstatus.count" /></td>
					<td><s:property value="top[1]" /></td>
					<td><s:property value="top[2]" /></td>
					<td><a
						href="<s:url action="ShowEditIngredient" namespace="/secure">
							<s:param name="ing" value="top[0]" />
							<s:param name="name" value="top[1]" />
							<s:param name="unit" value="top[2]" />
						</s:url>"><s:text name="addeditingr.edit" /></a></td>
				</tr>
			</s:iterator>
		</table>
	</div>
</s:if>

<script type="text/javascript" language="JavaScript">
	$(function() {
		$('#a_sidebar_left_addIngredient').parent().remove();
	});
	<s:if test="ingredients.length > 0">
	$("#AddIngredient").hide();
	</s:if>
	$('img.plusMinus').click(function () {
		$("#AddIngredient").toggle("slow");
		if($(this).attr('src')=='/portal/images/plus.png') $(this).attr('src','/portal/images/minus.png');
		else $(this).attr('src','/portal/images/plus.png');
	});  
	
</script>