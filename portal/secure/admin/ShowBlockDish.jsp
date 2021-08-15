<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3>
	 <s:text name="blockdishes.h3" />
</h3>

<s:if test="dishes.size() > 0">
	<div class="content_diff">
		<h4><s:text name="blockdishes.h4" /></h4>

	
		<table id="dishes" class="width700">
			<tr>
				<td class="first">#</td>
				<td class="first"><s:text name="blockdishes.name" /></td>
				<td class="first"><s:text name="blockdishes.author" /></td>
				<td class="first"><s:text name="blockdishes.action" /></td>
			</tr>

			<s:iterator value="dishes" status="rowstatus">
				<tr id="trDish<s:property value="top[0]" />">
					<td class="first"><s:property value="#rowstatus.count" /></td>
					<td><a href="<s:url action="ShowDish" namespace="/secure" includeParams="none">
							<s:param name="dishKey" value="top[0]"/>
						</s:url>"><s:property value="top[1]" /></a></td>
					<td><a href="<s:url action="ShowThisUser" namespace="/secure" includeParams="none">
							<s:param name="userId" value="top[3]"/>
						</s:url>"><s:property value="top[4]" /></a></td>
				
					
					<s:if test='top[2] == "1"'>
						<td><a href="#"
							rel="<s:url action="BlockDish" namespace="/secure" includeParams="none" />|<s:property value="top[0]" />|0"
							class="actionLinkBlockDish"><s:text name="blockdishes.block" /></a></td>
					</s:if>
					<s:else>
						<td><a href="#"
							rel="<s:url action="BlockDish" namespace="/secure" includeParams="none" />|<s:property value="top[0]" />|1"
							class="actionLinkBlockDish"><s:text name="blockdishes.unblock" /></a></td>
					</s:else>



				</tr>
				
			</s:iterator>
		</table>
	</div>
</s:if>
<s:else>
	<div class="content_diff"><s:text name="blockdishes.nodishes" /></div>
</s:else>

<script type="text/javascript" language="JavaScript">

	$('#dishes').find(".actionLinkBlockDish").click(function(e) {
		var rel = $(this).attr('rel').split('|');
		portal.dish.model.blockDish(rel[0], rel[1], rel[2]);
		e.preventDefault();
		e.stopPropagation();
	});

	
</script>