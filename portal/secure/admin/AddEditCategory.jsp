<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="addeditcat.h3" /></h3>

<div id="content_diff">
<s:if test="categories.length > 0">
	<h4><s:text name="addeditcat.h4.addcat" /> <img class="plusMinus" src="/portal/images/plus.png"/></h4><br>
</s:if>
<s:else>
	<h4><s:text name="addeditcat.h4.addcat" /></h4>
</s:else>
	<s:form action="AddCategory" namespace="/secure">
		<s:textfield key="addeditcat.name" name="name" />
		<s:submit key="addeditcat.submit" />
	</s:form>
</div>

<s:if test="categories.length > 0">
	<div id="content_diff">
		<h4><s:text name="addeditcat.h4.editcat" /></h4>

		<table>
			<s:iterator value="categories" status="rowstatus">
				<tr>
					<td class="first"><s:property value="#rowstatus.count" /></td>
					<td><s:property value="top[1]" /></td>
					<td><a
						href="<s:url action="ShowEditCategory" namespace="/secure">
							<s:param name="cat" value="top[0]" />
							<s:param name="name" value="top[1]" />
						</s:url>"><s:text name="addeditcat.edit" /></a></td>
				</tr>
			</s:iterator>
		</table>
	</div>
</s:if>

<script type="text/javascript" language="JavaScript">
	$(function() {
		$('#a_sidebar_left_addCategory').parent().remove();
	<s:if test="categories.length > 0">
		$("#AddCategory").hide();
	</s:if>
		$('img.plusMinus').click(function () {
			$("#AddCategory").toggle("slow");
			if($(this).attr('src')=='/portal/images/plus.png') $(this).attr('src','/portal/images/minus.png');
			else $(this).attr('src','/portal/images/plus.png');
		});  
	});

	
</script>