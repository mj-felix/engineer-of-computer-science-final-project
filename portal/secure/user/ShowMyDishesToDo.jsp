<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h3>
	Potrawy do zrobienia
</h3>

<s:if test="dishes.size()>0">
<div class="content_diff">
	<h4>Przegląd dań</h4>

		
	
		<table id="myDishes" class="width700">
			<tr>
				<td class="first">#</td>
				<td class="first">Nazwa potrawy</td>
				<td class="first">Kategoria</td>
				<td class="first">Akcja</td>
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
						class="actionLinkUploadPhoto">Dodaj zdjęcie</a>
					</td>
			
		

		</tr>
		<tr style="display:none;" id="trMyDish<s:property value="top[0]" />_uploadPhoto">
		<td class="first"></td>
		<td colspan="3">
		<s:form action="UploadDishPhoto" method="post"
		enctype="multipart/form-data">
		<s:hidden name='dishId' value='%{top[0]}' />
		<s:file name="pic" label="Picture" />
		<s:submit />
	</s:form>
</td>
		</tr>


	</s:iterator>
	</table>
</div>
</s:if>
<s:else>
<div class="content_diff">
Nie dodałeś jeszcze żadnych potraw.
</div>
</s:else>

<script type="text/javascript" language="JavaScript">
	
	
	$(function() {

		

		$('#myDishes').find(".actionLinkUploadPhoto").click(function(e) {
			var rel = $(this).attr('rel');
			$('#myDishes tr#trMyDish'+rel+'_uploadPhoto').toggle('slow');
			e.preventDefault();
			e.stopPropagation();
		});

	});

	
</script>