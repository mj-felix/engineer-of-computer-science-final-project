$(function() {

	$('div#container div#menu_top ul li').last().css('border-right', 'none');

	$.datepicker.regional['pl'] = {
		closeText : 'Zamknij',
		prevText : '&#x3c;Poprzedni',
		nextText : 'Następny&#x3e;',
		currentText : 'Dzi?',
		monthNames : [ 'Styczeń', 'Luty', 'Marzec', 'Kwiecień', 'Maj',
				'Czerwiec', 'Lipiec', 'Sierpień', 'Wrzesień', 'Październik',
				'Listopad', 'Grudzień' ],
		monthNamesShort : [ 'Sty', 'Lut', 'Mar', 'Kw', 'Maj', 'Cze', 'Lip',
				'Sie', 'Wrz', 'Paź', 'Lis', 'Gru' ],
		dayNames : [ 'Niedziela', 'Poniedzialek', 'Wtorek', 'Środa',
				'Czwartek', 'Piątek', 'Sobota' ],
		dayNamesShort : [ 'Nie', 'Pn', 'Wt', 'Śr', 'Czw', 'Pt', 'So' ],
		dayNamesMin : [ 'Nd', 'Pn', 'Wt', 'Śr', 'Cz', 'Pt', 'So' ],
		weekHeader : 'Tydz',
		dateFormat : 'yy-mm-dd',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : ''
	};
	$.datepicker.setDefaults($.datepicker.regional['pl']);

	$('input[type=submit]').css("background-color", "#C3FDB8");
	$('input[type=submit]').css("border-color", "#C3FDB8");
	$('input[type=submit]').hover(function() {
		$(this).css("cursor", "pointer");
	}, function() {
		$(this).css("cursor", "auto");
	});

	$('img.plusMinus').hover(function() {
		$(this).css("cursor", "pointer");
	}, function() {
		$(this).css("cursor", "auto");
	});
});

var portal = {
	utils : {
		getParam : function(paramName) {
			var searchString = window.location.search.substring(1), i, val, params = searchString
					.split("&");
			for (i = 0; i < params.length; i++) {
				val = params[i].split("=");
				if (val[0] == paramName) {

					return unescape(val[1]) || '';
				}
			}
			return '';

		},
		arrUnique : function(a) {
			a.sort();
			for ( var i = 1; i < a.length;) {
				if (a[i - 1] == a[i]) {
					a.splice(i, 1);
				} else {
					i++;
				}
			}
			return a;

		}
	},
	
	reward:{
		model : {
			getOrderHistory: function(url){
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					success : function(data) {

						portal.reward.view.gotOrderHistory(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});
				
			}
		},
		view : {
			gotOrderHistory : function(data){
				var json = data;

				var isNotEmpty = $
						.isArray(json.orders[0]["string-array"][0].string);
				var s = [];
				if (isNotEmpty) {
					var dishArr = json.orders[0]["string-array"];
					s
							.push('<tr><td class="first">#</td><td class="first">'+p_name+'</td><td class="first">'+p_address+'</td><td class="first">'+p_date+'</td><td class="first">'+p_cost+'</td></tr>')
					for (i = 0; i < dishArr.length; i++) {
						s.push('<tr>');
						s.push('<td class="first" style="width:40px">'
								+ (i + 1) + '</td>');
						s.push('<td>'+ dishArr[i].string[0] + '</td>');
						s.push('<td>'+ dishArr[i].string[1] + '</td>');
						s.push('<td>'+ dishArr[i].string[2] +' '+dishArr[i].string[3]+ '</td>');
						s.push('<td>'+ dishArr[i].string[4] + '</td>');

						s.push('</tr>');
					}

				} else {

					s.push(json.orders[0]["string-array"][0].string);
				}
				$('#orderResults').empty().html(s.join('\n'));
			}
		}
		
		
	}
	,
	admin : {
		model : {
			sendApprovePhoto : function(url, action, id) {
				var postData = {
					action : action,
					id : id
				};
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					data : postData,
					success : function(data) {

						portal.admin.view.showApprovedPhoto(data, id);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

			},
			sendBanUser : function(url, action, id) {
				var postData = {
					action : action,
					id : id
				};
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					data : postData,
					success : function(data) {

						portal.admin.view.showBannedUser(data, id);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

			}

		},
		view : {
			showApprovedPhoto : function(data, id) {
				var json = data;
				switch (json.approvePhoto[0].string[0]) {
				case 0:
					$('tr#trPhoto' + id).hide().html(
							'<td colspan="4" style="background-color:green;">'
									+ json.approvePhoto[0].string[1] + '</td>')
							.fadeIn('slow');
					break;
				case 1:
					$('tr#trPhoto' + id).hide().html(
							'<td colspan="4" style="background-color:red;">'
									+ json.approvePhoto[0].string[1] + '</td>')
							.fadeIn('slow');
					break;
				default:
					alert('json error');
				}

			},
			showBannedUser : function(data, id) {
				var json = data;
				switch (json.banUser[0].string[0]) {
				case 0:
					$('tr#trUser' + id).hide().html(
							'<td colspan="6" style="background-color:green;">'
									+ json.banUser[0].string[1] + '</td>')
							.fadeIn('slow');
					break;
				case 1:
					$('tr#trUser' + id).hide().html(
							'<td colspan="6" style="background-color:red;">'
									+ json.banUser[0].string[1] + '</td>')
							.fadeIn('slow');
					break;
				default:
					alert('json error');
				}

			}

		}

	},

	dish : {
		model : {
			sendComment : function(url, id, comment) {
				$('input,select,textarea').css('border', '1px solid #CCCCCC');
				if (comment == '') {
					$('#ShowDish_comment').css('border', '3px solid red');
					$('#ShowDish_comment').focus();
					return false;
				}

				var intRegex = /^[\r\nąćęłńóśźżĄĆĘŁŃÓŚŹŻa-z ,.A-Z0-9_-]*$/;

				if (!intRegex.test(comment)) {
					$('#ShowDish_comment_error').show();
					return false;
				}
				var postData = {
					id : id,
					comment : comment
				};
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					data : postData,
					success : function(data) {

						portal.dish.view.showComment(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

			},
			banComment : function(url, id) {

				var postData = {
					id : id
				};
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					data : postData,
					success : function(data) {

						portal.dish.view.bannedComment(data, id);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

			},
			blockDish : function(url, id, action) {

				var postData = {
					id : id,
					action : action
				};
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					data : postData,
					success : function(data) {

						portal.dish.view.blockedDish(data, id);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

			},
			addToDo : function(url, id) {

				var postData = {
					id : id
				};
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					data : postData,
					success : function(data) {

						portal.dish.view.addedToDo(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

			},
			sendRate : function(url, id, rate) {

				var postData = {
					id : id,
					rate : rate
				};
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					data : postData,
					success : function(data) {

						portal.dish.view.sentRate(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

			},
			getDishesPerCategory : function(url, id) {

				var postData = {
					id : id,
				};
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					data : postData,
					success : function(data) {

						portal.dish.view.gotDishesPerCategory(data, id);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

			},
			sendSearchTitle : function(url, txt) {
				$('input,select,textarea').css('border', '1px solid #CCCCCC');
				$('#searchTxt_error').hide();
				if (txt == '') {
					$('#searchTxt').css('border', '3px solid red');
					$('#searchTxt').focus();
					return false;
				}

				var intRegex = /^[ąćęłńóśźżĄĆĘŁŃÓŚŹŻa-z A-Z]*$/;

				if (!intRegex.test(txt)) {
					$('#searchTxt').css('border', '3px solid red');
					$('#searchTxt').focus();
					$('#searchTxt_error').css('display', 'inline')
					return false;
				}
				var postData = {
					txt : txt,
				};
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					data : postData,
					success : function(data) {

						portal.dish.view.gotSearchTitle(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

			},
			sendSearch : function(url) {
				$('input,select,textarea').css('border', '1px solid #CCCCCC');

				var params = [];

				for ( var i = 0; i < 6; i++) {
					var v = $("#ingredient_" + i + "_").val();
					if (v != '-1' && jQuery.inArray( v, params ))
						params.push(v);

				}

				params = portal.utils.arrUnique(params);

				params = params.join('|');
				params = params+'|';
				
				if (params == "|") {
					$("#ingredient_0_").css('border', '3px solid red').focus();
					return false;

				}

				var postData = {
					params : params,
				};
				$.ajax({
					type : 'POST',
					url : url,
					dataType : "json",
					async : true,
					data : postData,
					success : function(data) {

						portal.dish.view.gotSearchTitle(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

					}
				});

			}

		},
		view : {
			showComment : function(data) {
				var json = data.sendComment;
				if (json == true) {
					location.reload();
				}

			},
			bannedComment : function(data, id) {
				var json = data.banComment;
				if (json == true) {
					$('#trComment' + id).fadeOut();
				}

			},
			gotDishesPerCategory : function(data, id) {
				var json = data;

				var isNotEmpty = $
						.isArray(json.dishes[0]["string-array"][0].string);
				var s = [];
				if (isNotEmpty) {
					var dishArr = json.dishes[0]["string-array"];

					s.push('<ul>');
					for (i = 0; i < dishArr.length; i++) {
						s.push('<li><a href="' + href_ShowDish + '?dishKey='
								+ dishArr[i].string[0] + '">')
						s.push(dishArr[i].string[1]);
						s.push('</a></li>');
					}
					s.push('</ul>');
				} else {

					s.push(json.dishes[0]["string-array"][0].string);
				}
				$('#trCat' + id + '_dishes td.dishesList').html(s.join('\n'));

			},
			gotSearchTitle : function(data) {
				var json = data;

				var isNotEmpty = $
						.isArray(json.dishes[0]["string-array"][0].string);
				var s = [];
				if (isNotEmpty) {
					var dishArr = json.dishes[0]["string-array"];
					s
							.push('<tr><td class="first">#</td><td class="first">'+p_name+'</td></tr>');
					var counter = 1
					for (i = 0; i < dishArr.length; i++) {
						if(dishArr[i].string[0]=='') continue;
						s.push('<tr>');
						s.push('<td class="first" style="width:40px">'
								+ (counter++) + '</td>');
						s.push('<td><a href="' + href_ShowDish + '?dishKey='
								+ dishArr[i].string[0] + '">')
						s.push(dishArr[i].string[1]);
						s.push('</a></td>');
						s.push('</tr>');
					}

				} else {

					s.push(json.dishes[0]["string-array"][0].string);
				}
				$('#searchResults').empty().html(s.join('\n'));

			},
			addedToDo : function(data) {

				var json = data.addedToDo;
				$("#actionLinkAddToDo").replaceWith(json);

			},
			sentRate : function(data) {

				var json = data;

				$('#AvgRateTd').text(json.sendRate[0].string[0]);

				$('#SendRateTd').text(json.sendRate[0].string[1]);

			},
			blockedDish : function(data, id) {
				var json = data;

				switch (json.blockedDish[0].string[0]) {
				case 0:
					$('tr#trDish' + id).hide().html(
							'<td colspan="4" style="background-color:green;">'
									+ json.blockedDish[0].string[1] + '</td>')
							.fadeIn('slow');
					break;
				case 1:
					$('tr#trDish' + id).hide().html(
							'<td colspan="4" style="background-color:red;">'
									+ json.blockedDish[0].string[1] + '</td>')
							.fadeIn('slow');
					break;
				default:
					alert('json error');
				}

			}

		}

	}

};