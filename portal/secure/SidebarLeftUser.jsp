<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="sidebar_left_title logout">
	<a href="<s:url action='Logout' namespace='/secure' includeParams='none'/>"><s:text name="sidebar.logout" /></a>
</div>
<p  style="text-align:center" id="sT">
<s:if test='%{#session.user.iPath!=""}'>

		<img src="/portal<s:property value='%{#session.user.iPath}' />" style="width:150px;"/><br />

</s:if>
	<s:text name="sidebar.user" /> <b><s:property value="%{#session.user.username}" /></b> <br /><s:text name="sidebar.fromdate" /><b>
	 <s:date name="%{#session.user.registrationDate}" format="yyyy-MM-dd" />
		<s:property value="%{#session.user.registrationTime}" />
	</b> <br />
	<s:text name="sidebar.rank" /> <b><s:property value="%{#session.user.rank}" /></b> <br />


</p>
<div class="hr"></div>


<div class="sidebar_left_title"><s:text name="sidebar.menu" /></div>
<ul>
	<li><a href="<s:url action='ShowAddDish' namespace='/secure' includeParams='none' />"><s:text name="sidebar.adddish" /></a></li>
	<li><a href="<s:url action='ShowMyDishes' namespace='/secure' includeParams='none' />"><s:text name="sidebar.mydishes" /></a></li>
	<li><a href="<s:url action='ShowRequestIngredient' namespace='/secure' includeParams='none' />"><s:text name="sidebar.ingredientrequest" /></a></li>
	<li><a href="<s:url action='ShowRewards' namespace='/secure' includeParams='none' />"><s:text name="sidebar.rewardcatalogue" /></a></li>
	<li><a href="<s:url action='ViewProfile' namespace='/secure' includeParams='none' />"><s:text name="sidebar.viewprofile" /></a></li>
	<li><a href="<s:url action='ShowMyAchievementsRanks' namespace='/secure' includeParams='none' />"><s:text name="sidebar.myachievements" /></a></li>
	<li><a
		href="<s:url action='ShowChangePassword' namespace='/secure' includeParams='none' />"
		id="a_sidebar_left_changePassword"><s:text name="sidebar.changepass" /></a></li>
</ul>

<s:if test="%{#session.user.privilegeLevel==1}">

	<div class="hr"></div>
	<div class="sidebar_left_title"><s:text name="sidebar.admin" /></div>
	<ul>
		<li><a
			href="<s:url action='ShowAddCategory' namespace='/secure' includeParams='none' />"><s:text name="sidebar.addcat" /></a></li>
		<li><a href="<s:url action='ShowAddIngredient' namespace='/secure' includeParams='none' />"><s:text name="sidebar.addingr" /></a></li>
		<li><a href="<s:url action='ShowUsers' namespace='/secure' includeParams='none' />"><s:text name="sidebar.viewusers" /></a></li>
		<li><a href="<s:url action='ShowBlockDish' namespace='/secure' includeParams='none' />"><s:text name="sidebar.blockdishes" /></a></li>
		<li><a href="<s:url action='ShowApprovePhoto' namespace='/secure' includeParams='none' />"><s:text name="sidebar.approvephotos" /></a></li>
	</ul>

</s:if>