<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<ul>
	<li><a href="<s:url action='ShowSearch' namespace='/unsecure' includeParams='none' />"><s:text name="menutop.showsearch" /></a></li>
	<li><a href="<s:url action='ShowSearchTitle' namespace='/unsecure' includeParams='none' />"><s:text name="menutop.showsearchtitle" /></a></li>
	<li><a href="<s:url action='ShowCatalogue' namespace='/unsecure' includeParams='none' />"><s:text name="menutop.showcatalogue" /></a></li>
</ul>
<div class="clearer"></div>