<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>Gospodynka.pl</title>
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control"
	content="no-store, no-cache, must-revalidate" />
<meta http-equiv="Expires" content="-1" />
<noscript>
	<style type="text/css">
body {
	display: none
}
</style>
</noscript>

<style type="text/css">
html {
	display: none;
}
</style>
<script>
	if (self == top) {
		document.documentElement.style.display = 'block';
	} else {
		top.location = self.location;
	}

	if ((navigator.userAgent.indexOf('Mac') == -1) && (top != self))
		top.location = self.location;
</script>

<meta name="description" content="" />
<link rel="stylesheet" href="/portal/css/style.css" type="text/css" />
<link rel="stylesheet" href="/portal/css/start/jui.css" type="text/css" />
<script type="text/javascript" language="JavaScript"
	src="/portal/js/j.js"></script>
<script type="text/javascript" language="JavaScript"
	src="/portal/js/jui.js"></script>
</head>
<body>
	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="menu_top">
			<tiles:insertAttribute name="menu_top" />
		</div>
		<div id="sidebar_left">
			<tiles:insertAttribute name="sidebar_left" />
		</div>
		<div id="content">
			<tiles:insertAttribute name="content" />
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
	<script type="text/javascript" language="JavaScript"
		src="/portal/js/common.js"></script>
</body>
</html>


