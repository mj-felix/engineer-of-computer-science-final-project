<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h3><s:text name="adddish.h3" /></h3>

<div id="content_diff">
<h4><s:text name="adddish.h4" /></h4>
<s:form action="AddDish" namespace="/secure">
		<s:select key="adddish.category" name="category" list="#session.categories" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectcat')}" />
		<s:textfield key="adddish.name" name="name" />
		<s:textarea cols="50" rows="10" key="adddish.description" name="description" />
		<s:textfield key="adddish.amountofcalories" name="amountofcalories" />
		<s:textfield key="adddish.preptime" name="preptime" />
		<s:radio key="adddish.difficultylevel" name="difficultylevel"
			list="#{'0': '1','1':'2','2':'3'}" />
	
		<s:textfield key="adddish.size" name="dsize" />

		<s:select key="adddish.ingredients" name="ingredient[0]" list="#session.ingredients" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectingr')}" />
		<s:textfield key="adddish.iamount" name="iamount[0]" />
		<s:select key="adddish.ingredients" name="ingredient[1]" list="#session.ingredients" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectingr')}" />
		<s:textfield key="adddish.iamount" name="iamount[1]" />
		<s:select key="adddish.ingredients" name="ingredient[2]" list="#session.ingredients" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectingr')}" />
		<s:textfield key="adddish.iamount" name="iamount[2]" />
		<s:select key="adddish.ingredients" name="ingredient[3]" list="#session.ingredients" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectingr')}" />
		<s:textfield key="adddish.iamount" name="iamount[3]" />
		<s:select key="adddish.ingredients" name="ingredient[4]" list="#session.ingredients" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectingr')}" />
		<s:textfield key="adddish.iamount" name="iamount[4]" />
		<s:select key="adddish.ingredients" name="ingredient[5]" list="#session.ingredients" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectingr')}" />
		<s:textfield key="adddish.iamount" name="iamount[5]" />
		<s:select key="adddish.ingredients" name="ingredient[6]" list="#session.ingredients" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectingr')}" />
		<s:textfield key="adddish.iamount" name="iamount[6]" />
		<s:select key="adddish.ingredients" name="ingredient[7]" list="#session.ingredients" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectingr')}" />
		<s:textfield key="adddish.iamount" name="iamount[7]" />
		<s:select key="adddish.ingredients" name="ingredient[8]" list="#session.ingredients" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectingr')}" />
		<s:textfield key="adddish.iamount" name="iamount[8]" />
		<s:select key="adddish.ingredients" name="ingredient[9]" list="#session.ingredients" listKey="top.id" listValue="top.name" headerKey="-1" headerValue="%{getText('adddish.selectingr')}" />
		<s:textfield key="adddish.iamount" name="iamount[9]" />
		
		<s:submit key="adddish.submit" />
	</s:form>
</div>

<script type="text/javascript" language="JavaScript">
	$(function() {
		$('label[for=AddDish_difficultylevel0]').text('<s:text name="adddish.easy" />');
		$('label[for=AddDish_difficultylevel1]').text('<s:text name="adddish.soso" />');
		$('label[for=AddDish_difficultylevel2]').text('<s:text name="adddish.difficult" />');
		
		for (var i=0; i<10;i++){
			$('#AddDish_ingredient_'+i+'_').after($('#AddDish_iamount_'+i+'_'));
			
			$('label[for=AddDish_iamount_'+i+'_]').parent().parent().remove();
			if(i>4 && $('.errorMessage').length==0) $('#AddDish_ingredient_'+i+'_').parent().parent().hide();
		}
		
		if($('.errorMessage').length==0){
			$('td[colspan=2]').last().parent().before('<tr><td></td><td><a href="#" id="moreIngredients"><s:text name="adddish.moreingr" /></a></td></tr>');
			$('#moreIngredients').click(function(e){
				$('select').parent().parent().show();
				$(this).parent().parent().hide();
				e.preventDefault();
				e.stopPropagation();
				return false;
				
			});
		}
		$('#AddDish_ingredient_0_ option[value=<s:property value="ingredient[0]" />]').attr('selected', 'selected');
		$('#AddDish_ingredient_1_ option[value=<s:property value="ingredient[1]" />]').attr('selected', 'selected');
		$('#AddDish_ingredient_2_ option[value=<s:property value="ingredient[2]" />]').attr('selected', 'selected');
		$('#AddDish_ingredient_3_ option[value=<s:property value="ingredient[3]" />]').attr('selected', 'selected');
		$('#AddDish_ingredient_4_ option[value=<s:property value="ingredient[4]" />]').attr('selected', 'selected');
		$('#AddDish_ingredient_5_ option[value=<s:property value="ingredient[5]" />]').attr('selected', 'selected');
		$('#AddDish_ingredient_6_ option[value=<s:property value="ingredient[6]" />]').attr('selected', 'selected');
		$('#AddDish_ingredient_7_ option[value=<s:property value="ingredient[7]" />]').attr('selected', 'selected');
		$('#AddDish_ingredient_8_ option[value=<s:property value="ingredient[8]" />]').attr('selected', 'selected');
		$('#AddDish_ingredient_9_ option[value=<s:property value="ingredient[9]" />]').attr('selected', 'selected');
		
		$('#AddDish').submit(function() {
			$('input,select,textarea').css('border','1px solid #CCCCCC');
			
			if ($('#AddDish_category option:selected').val() == "-1"){
				
				$('#AddDish_category').css('border-color','red').css('border-width','3px');
				$('#AddDish_category').focus();
				return false;
			}
			
			if ($('#AddDish_name').val() == ""){
				
				$('#AddDish_name').css('border-color','red').css('border-width','3px');
				$('#AddDish_name').focus();
				return false;
			}
			
			if ($('#AddDish_description').val() == ""){
				
				$('#AddDish_description').css('border-color','red').css('border-width','3px');
				$('#AddDish_description').focus();
				return false;
			}
			
			for (var i=0;i<10;i++){
				var intRegex = /^\d+$/;

				
				if ($('#AddDish_ingredient_'+i+'_ option:selected').val() != "-1" &&
						$('#AddDish_iamount_'+i+'_').val() ==""	)
					{
						$('#AddDish_iamount_'+i+'_').css('border-color','red').css('border-width','3px');
						$('#AddDish_iamount_'+i+'_').focus();
						return false;
						
					}
				
				if ($('#AddDish_ingredient_'+i+'_ option:selected').val() != "-1" &&
						!intRegex.test($('#AddDish_iamount_'+i+'_').val())	){
					$('#AddDish_iamount_'+i+'_').css('border-color','red').css('border-width','3px');
					$('#AddDish_iamount_'+i+'_').val('<s:text name="adddish.insertpositivenumber" />');
					$('#AddDish_iamount_'+i+'_').focus();
					return false;
				}
		      	
			}
			return true;
		 });


	});

	
</script>