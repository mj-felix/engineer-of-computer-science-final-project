<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h3><s:text name="showsearch.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="showsearch.h4" /></h4>

<s:select key="showsearch.ingr" name="ingredient[0]" list="ingr" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('showsearch.selectingr')}" />
<br />
<s:select key="showsearch.ingr" name="ingredient[1]" list="ingr" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('showsearch.selectingr')}" />
<br />

<s:select key="showsearch.ingr" name="ingredient[2]" list="ingr" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('showsearch.selectingr')}" />
<a href="#" rel="3" class="actionLinkShowMore"><img src="/portal/images/plus2.png" style=" vertical-align:middle"/></a><br />
<span style="display:none" id="showMore3">
<s:select key="showsearch.ingr" name="ingredient[3]" list="ingr" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('showsearch.selectingr')}" />
<a href="#" rel="4" class="actionLinkShowMore"><img src="/portal/images/plus2.png" style=" vertical-align:middle"/></a><br />
</span>
<span style="display:none" id="showMore4">
<s:select key="showsearch.ingr" name="ingredient[4]" list="ingr" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('showsearch.selectingr')}" />
<a href="#" rel="5" class="actionLinkShowMore"><img src="/portal/images/plus2.png" style=" vertical-align:middle"/></a><br />
</span>
<span style="display:none" id="showMore5">
<s:select key="showsearch.ingr" name="ingredient[5]" list="ingr" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('showsearch.selectingr')}" />
<br />
</span>

 <input type="submit" id="searchTxt_submit" value="<s:text name="showsearch.submit" />"/>

 
 <table id="searchResults" class="width700">
 </table>

</div>

<script type="text/javascript" language="JavaScript">
var href_ShowDish = '<s:url action="ShowDish" namespace="/unsecure" includeParams="none" />';
var p_name='<s:text name="showsearch.p_name" />';
$('#searchTxt_submit').click(function(e){

	portal.dish.model.sendSearch('<s:url action="GetDishes" namespace="/unsecure" includeParams="none" />');
	e.preventDefault();
	e.stopPropagation();
	
});

$('.actionLinkShowMore').click(function(e){
	var rel = $(this).attr('rel');
	$(this).hide();
	$('#showMore'+rel).show();
	e.preventDefault();
	e.stopPropagation();
	
});

$("#ingredient_0_,#ingredient_1_,#ingredient_2_,#ingredient_3_,#ingredient_4_,#ingredient_5_").keydown(function (event) {
	
	if (event.keyCode == 13) {
		$('#searchTxt_submit').click();
	}
});

</script>