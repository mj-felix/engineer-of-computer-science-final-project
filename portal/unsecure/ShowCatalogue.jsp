<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h3><s:text name="showcatalogue.h3" /></h3>

<s:if test="categories.size()>0">
	<div class="content_diff">
		<h4><s:text name="showcatalogue.h4" /></h4>



		<table id="categories" class="width700">
			<tr>
				<td class="first" style="width:40px">#</td>
				<td class="first"><s:text name="showcatalogue.catname" /></td>
			</tr>

			<s:iterator value="categories" status="rowstatus">
				<tr id="trCat<s:property value='top[0]' />">
					<td class="first"><s:property value="#rowstatus.count" /></td>
					<td style="text-align:left;padding-left:50px"><a href="#" class="actionLinkGetDishesPerCategory"
						rel='<s:url action="GetDishesPerCategory" namespace="/unsecure" includeParams="none" />|<s:property value="top[0]" />'><s:property
								value="top[1]" /></a> (<s:property
								value="top[2]" />)</td>

				</tr>
				<tr style="display: none;"
					id='trCat<s:property value="top[0]" />_dishes'>
					<td class="first"></td>
					<td class="dishesList" style="text-align:left;padding-left:50px"></td>
				</tr>

			</s:iterator>
		</table>
	</div>
</s:if>
<s:else>
	<div class="content_diff"><s:text name="showcatalogue.nocat" /></div>
</s:else>

<script type="text/javascript" language="JavaScript">
	var href_ShowDish = '<s:url action="ShowDish" namespace="/unsecure" includeParams="none" />';
	$(function() {

		$('#categories').find(".actionLinkGetDishesPerCategory").click(function(e) {
	
			var rel = $(this).attr('rel').split('|');
			var target = '#categories #trCat'+rel[1]+'_dishes';
			$(target).toggle('slow', function() {
			if($(target+' .dishesList').is(':visible')==true && $(target+' .dishesList').text()=="")
					portal.dish.model.getDishesPerCategory(rel[0],rel[1]);
			});
			
			
			e.preventDefault();
			e.stopPropagation();
		});

	});

	
</script>