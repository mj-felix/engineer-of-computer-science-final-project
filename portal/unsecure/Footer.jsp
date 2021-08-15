<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" language="JavaScript">
var locale = '<s:property value='locale.toString()'/>';
if(locale=='en'||locale=='en_US'||locale=='en_GB') $('div#menu_top ul li').css('padding','0 85px');
</script>
Copyright &copy; 2012 Gospodynka.pl