<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="addeddish.h3" /></h3>

<div id="content_diff">
	<h4><s:text name="addeddish.h4" /></h4>

	<p>
		<s:text name="addeddish.seeadded" /> <a href="<s:url action="ShowDish" namespace="/secure">
							<s:param name="dishKey" value="dishKey"/>
						</s:url>" ><i><s:property value="name" /></i></a>
	</p>


</div>
