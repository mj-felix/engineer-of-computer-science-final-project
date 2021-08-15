<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="showsearchtitle.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="showsearchtitle.h4" /></h4>

	<input id="searchTxt" type="text" />
 <input type="submit" id="searchTxt_submit" value="<s:text name="showsearchtitle.submit" />"/>
 <span id="searchTxt_error" class="errorMessage" style="display:none; margin-left:5px"><s:text name="showsearchtitle.err.norestricted" /></span>
 
 <table id="searchResults" class="width700">
 </table>

</div>

<script type="text/javascript" language="JavaScript">
var href_ShowDish = '<s:url action="ShowDish" namespace="/unsecure" includeParams="none" />';
var p_name='<s:text name="showsearch.p_name" />';
$('#searchTxt_submit').click(function(e){

	portal.dish.model.sendSearchTitle('<s:url action="GetDishesTitle" namespace="/unsecure" includeParams="none" />', $('#searchTxt').val());
	e.preventDefault();
	e.stopPropagation();
	
});

$("#searchTxt").keydown(function (event) {
	
	if (event.keyCode == 13) {
		$('#searchTxt_submit').click();
	}
});

</script>