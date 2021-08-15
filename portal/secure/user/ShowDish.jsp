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
			<td colspan="3"><a href="<s:url action="ShowCategoryDishes" namespace="/secure" includeParams="none">
							<s:param name="catId" value="catId" />
						</s:url>"><s:property value="catName" /></a></td>
		</tr>
		<tr>
			<td class="first"><s:text name="showdish.additiondate" /></td>
			<td><s:date name="additiondate" format="yyyy-MM-dd" /> <s:property
					value="additiontime" /></td>
			<td class="first"><s:text name="showdish.autor" /></td>
			<td><a href="<s:url action="ShowThisUser" namespace="/secure" includeParams="none">
							<s:param name="userId" value="userId"/>
						</s:url>"><s:property value="userName" /></a></td>
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
			<s:if test="rate == -1">
			<td id="SendRateTd">
				<a href="#" class="actionLinkSendRate" rel="<s:url action="SendRate" namespace="/secure" includeParams="none" />|<s:property value="dishKey" />|1">1</a> 
				<a href="#" class="actionLinkSendRate" rel="<s:url action="SendRate" namespace="/secure" includeParams="none" />|<s:property value="dishKey" />|2">2</a>
				<a href="#" class="actionLinkSendRate" rel="<s:url action="SendRate" namespace="/secure" includeParams="none" />|<s:property value="dishKey" />|3">3</a>
				<a href="#" class="actionLinkSendRate" rel="<s:url action="SendRate" namespace="/secure" includeParams="none" />|<s:property value="dishKey" />|4">4</a>
				<a href="#" class="actionLinkSendRate" rel="<s:url action="SendRate" namespace="/secure" includeParams="none" />|<s:property value="dishKey" />|5">5</a>
			</td>
			</s:if>
			<s:else>
			<td><s:property value='rate' /></td>
			</s:else>
		</tr>
		<s:if test="canBeToDo == 1">
		<tr>
			<td class="first"><s:text name="showdish.dishtodo" /></td>
			<td colspan="3"><a href="#" id="actionLinkAddToDo" rel="<s:url action="AddToDo" namespace="/secure" includeParams="none" />|<s:property value="dishKey" />"><s:text name="showdish.addtodo" /></a></td>
		</tr>
		</s:if>
		<s:if test="canBeToDo == 2">
		<tr>
			<td class="first"><s:text name="showdish.dishtodo" /></td>
			<td colspan="3"><s:text name="showdish.dishon" /> <a href="<s:url action="ShowMyDishesToDo" namespace="/secure" includeParams="none" />"><s:text name="showdish.dishtodolist" /></a></td>
		</tr>
		</s:if>
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
			<td><a href="<s:url action="ShowThisUser" namespace="/secure" includeParams="none">
							<s:param name="userId" value="top[3]"/>
						</s:url>"><s:property value="top[4]" /></a></td>
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
			<td class="first"  style="width:200px"><a href="<s:url action="ShowThisUser" namespace="/secure" includeParams="none">
							<s:param name="userId" value="top[1]"/>
						</s:url>"><s:property value="top[0]" /></a> @ <s:property value="top[3]" /> <s:property value="top[4]" /></td>
			<td><s:property value="top[2]" escape="false"/></td>
			<s:if test="%{#session.user.privilegeLevel==1}">
			<td class="first"><a href="#" class="actionLinkBanComment" rel="<s:url action="BanComment" namespace="/secure" includeParams="none" />|<s:property value="top[5]" />"><s:text name="showdish.block" /></a></td>
			</s:if>

		</tr>


	</s:iterator>
	</table>
</div>
</s:if>
<div id="content_diff">
	<h4><s:text name="showdish.h4.addcomment" /></h4>
	<table style="width: 700px">
		 <tr style="display:none" id="ShowDish_comment_error">
    		<td class="first"><span class="errorMessage"><s:text name="showdish.err.addcomment" /></span></td>
		</tr>
		<tr>
			<td class="first">
				<textarea name="comment" style="width:500px" rows="3" id="ShowDish_comment"></textarea>
			</td>
		</tr>
		<tr>
			<td class="first" style="text-align:right;">
				<input type="submit" id="ShowDish_comment_submit" rel="<s:url action="SendComment" namespace="/secure" includeParams="none"/>|<s:property value='dishKey' />" value="<s:text name="showdish.sendcomment" />"/>			
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript" language="JavaScript">
	$('#ShowDish_comment_submit').click(function(e){
		var rel = $(this).attr('rel').split('|');
		portal.dish.model.sendComment(rel[0],rel[1], $('#ShowDish_comment').val());
		e.preventDefault();
		e.stopPropagation();
		
	});
	<s:if test="%{#session.user.privilegeLevel==1}">
	$('#table_comments').find(".actionLinkBanComment").click(function(e) {
		var rel = $(this).attr('rel').split('|');
		portal.dish.model.banComment(rel[0], rel[1]);
		e.preventDefault();
		e.stopPropagation();
	});
	</s:if>
	$("#actionLinkAddToDo").click(function(e) {
		var rel = $(this).attr('rel').split('|');
		portal.dish.model.addToDo(rel[0], rel[1]);
		e.preventDefault();
		e.stopPropagation();
	});
	$(".actionLinkSendRate").click(function(e) {
		var rel = $(this).attr('rel').split('|');
		portal.dish.model.sendRate(rel[0], rel[1], rel[2]);
		e.preventDefault();
		e.stopPropagation();
	});

	
</script>

</s:else>