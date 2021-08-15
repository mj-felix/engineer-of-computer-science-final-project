<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h3>
	<s:text name="showcategorydishes.h3" />
</h3>
<div class="content_diff">
	<h4><s:property value="catName" /></h4>
<s:if test="dishes.size()>0">


		
	
		<table id="myDishes" class="width700">
			<tr>
				<td class="first">#</td>
				<td class="first"><s:text name="showcategorydishes.dishname" /></td>
			</tr>

	<s:iterator value="dishes" status="rowstatus">
	 <tr id="trMyDish<s:property value="top[0]" />">
	 		<td class="first"><s:property value="#rowstatus.count" /></td>
			<td><a href="<s:url action="ShowDish" namespace="/unsecure" includeParams="none">
							<s:param name="dishKey" value="top[0]"/>
						</s:url>"><s:property value="top[1]" /></a></td>

			
		

		</tr>



	</s:iterator>
	</table>
</s:if>
<s:else>

Brak potraw w tej kategorii.

</s:else>
</div>

