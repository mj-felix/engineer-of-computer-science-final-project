<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h3>
	<s:text name="showmydishes.h3" />
</h3>

<s:if test="dishes.size()>0">
<div class="content_diff">
	<h4><s:text name="showmydishes.h4" /></h4>

		
	
		<table id="myDishes" class="width700">
			<tr>
				<td class="first">#</td>
				<td class="first"><s:text name="showmydishes.name" /></td>
				<td class="first"><s:text name="showmydishes.cat" /></td>
				<td class="first"><s:text name="showmydishes.action" /></td>
			</tr>

	<s:iterator value="dishes" status="rowstatus">
	 <tr id="trMyDish<s:property value="top[0]" />">
	 		<td class="first"><s:property value="#rowstatus.count" /></td>
			<td><a href="<s:url action="ShowDish" namespace="/secure" includeParams="none">
							<s:param name="dishKey" value="top[0]"/>
						</s:url>"><s:property value="top[1]" /></a></td>
			<td><a href="<s:url action="ShowCategoryDishes" namespace="/secure" includeParams="none">
							<s:param name="catId" value="top[2]" />
						</s:url>"><s:property value="top[3]" /></a></td>
			<td><a href="#" rel="<s:property value="top[0]" />"
						class="actionLinkUploadPhoto"><s:text name="showmydishes.addphoto" /></a>
					</td>
			
		

		</tr>
		<tr style="display:none;" id="trMyDish<s:property value="top[0]" />_uploadPhoto">
		<td class="first"></td>
		<td colspan="3">
		<s:form action="UploadDishPhoto" method="post"
		enctype="multipart/form-data">
		<s:hidden name='dishId' value='%{top[0]}' />
		<s:file name="pic" key="showmydishes.file" />
		<s:submit key="showmydishes.submit"/>
	</s:form>
</td>
		</tr>


	</s:iterator>
	</table>
</div>
</s:if>
<s:else>
<div class="content_diff">
<s:text name="showmydishes.nodishes" />
</div>
</s:else>

<s:if test="dishesT.size()>0">
<div class="content_diff">
	<h4><s:text name="showmydishes.h4.todo" /></h4>

		
	
		<table id="myDishesT" class="width700">
			<tr>
				<td class="first">#</td>
				<td class="first"><s:text name="showmydishes.name" /></td>
				<td class="first"><s:text name="showmydishes.cat" /></td>
				<td class="first"><s:text name="showmydishes.action" /></td>
			</tr>

	<s:iterator value="dishesT" status="rowstatus">
	 <tr id="trMyDish<s:property value="top[0]" />">
	 		<td class="first"><s:property value="#rowstatus.count" /></td>
			<td><a href="<s:url action="ShowDish" namespace="/secure" includeParams="none">
							<s:param name="dishKey" value="top[0]"/>
						</s:url>"><s:property value="top[1]" /></a></td>
			<td><a href="<s:url action="ShowCategoryDishes" namespace="/secure" includeParams="none">
							<s:param name="catId" value="top[2]" />
						</s:url>"><s:property value="top[3]" /></a></td>
			<td><a href="#" rel="<s:property value="top[0]" />"
						class="actionLinkUploadPhoto"><s:text name="showmydishes.addphoto" /></a>
					</td>
			
		

		</tr>
		<tr style="display:none;" id="trMyDish<s:property value="top[0]" />_uploadPhoto">
		<td class="first"></td>
		<td colspan="3">
		<s:form action="UploadDishPhoto" method="post"
		enctype="multipart/form-data">
		<s:hidden name='dishId' value='%{top[0]}' />
		<s:file name="pic" key="showmydishes.file" />
		<s:submit key="showmydishes.submit"/>
	</s:form>
</td>
		</tr>


	</s:iterator>
	</table>
</div>
</s:if>
<s:else>
<div class="content_diff">
<s:text name="showmydishes.nodishes" />
</div>
</s:else>

<script type="text/javascript" language="JavaScript">
	
	
	$(function() {

		

		$(".actionLinkUploadPhoto").click(function(e) {
			var rel = $(this).attr('rel');
			$('tr#trMyDish'+rel+'_uploadPhoto').toggle('slow');
			e.preventDefault();
			e.stopPropagation();
		});

	});

	
</script>