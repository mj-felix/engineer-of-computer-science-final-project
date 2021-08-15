<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h3><s:text name="showrewards.h3" /></h3>


<div class="content_diff">
	<h4><s:text name="showrewards.h4" /></h4>
	<s:if test="msg != null">
		<s:property value="msg" />
	</s:if>
	<s:form action="SendReward" namespace="/secure">
		<s:radio key="showrewards.select" name="reward"
			list="#session.rewards" listKey="top[0]" listValue="top[1]" />
		<s:textarea key="showrewards.address" name="address" cols="40"
			rows="5" />
		<s:submit key="showrewards.submit" />
	</s:form>



</div>

<div id="content_diff">

	<h4><s:text name="showrewards.orderhistory" /> <img class="plusMinus" src="/portal/images/plus.png"/></h4><br>
 <table id="orderResults" class="width700" style="display:none"></table>
</div>


<script type="text/javascript" language="JavaScript">
var p_name='<s:text name="showrewards.p_name" />';
var p_address='<s:text name="showrewards.p_address" />';
var p_date='<s:text name="showrewards.p_date" />';
var p_cost='<s:text name="showrewards.p_cost" />';

	$(function() {
		$('#SendReward_reward2').before('<br />');
		$('form#SendReward table.wwFormTable').prepend('<tr><td><b><s:text name="showrewards.avalpoints" /></b></td><td><b><s:property value="%{#session.user.xpoints-#session.user.xpointsreedemed}" /></b></td></tr>');
		$('#SendReward').submit(
				function() {
					$('input,select,textarea').css('border',
							'1px solid #CCCCCC');

					if ($('#SendReward_address').val() == "") {

						$('#SendReward_address').css('border-color', 'red')
								.css('border-width', '3px');
						$('#SendReward_address').focus();
						return false;
					}

					return true;
				});
		<s:if test="msg != null">
			$('#SendReward_address').val('');
		</s:if>
		
		$('img.plusMinus').click(function(e) {

			$('#orderResults').toggle('slow', function() {
			if($('#orderResults').is(':visible')==true && $('#orderResults').text()==""){
					portal.reward.model.getOrderHistory('<s:url action="GetOrderHistory" namespace="/secure" includeParams="none" />');
			}
			});
			if($(this).attr('src')=='/portal/images/plus.png') $(this).attr('src','/portal/images/minus.png');
			else $(this).attr('src','/portal/images/plus.png');

		});
	});

	
</script>