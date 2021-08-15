<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="sidebar_left_title"><s:text name="sidebar.menu" /></div>
<ul>
	<li><a href="<s:url action='Login' namespace='/secure' includeParams='none'/>" id="a_sidebar_left_login"><s:text name="sidebar.login" /></a></li>
	<li><a href="<s:url action='Register' namespace='/secure' includeParams='none' />" id="a_sidebar_left_register"><s:text name="sidebar.register" /></a></li>
	<li><a href="<s:url action='Activate' namespace='/secure' includeParams='none' />" id="a_sidebar_left_activate"><s:text name="sidebar.activate" /></a></li>
	<li><a href="<s:url action='Reset' namespace='/secure' includeParams='none' />" id="a_sidebar_left_reset"><s:text name="sidebar.resetpass" /></a></li>
</ul>