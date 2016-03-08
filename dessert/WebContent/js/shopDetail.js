//得到url的参数
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function refreshSelectedDay() {
	// 选中周几
	var selectedDay = $("input[name='week']:checked").val();
	// 选中的星期是哪一天
	var date = $("#" + selectedDay).val();
	var shopName = getQueryString("shopName");
	var type = getQueryString("type");
	window.location.href = "shop_dessert?shopName=" + shopName + "&weekDay="
			+ selectedDay + "&date=" + date + "&type=" + type;
}

//刚进来的时候跳转方法
function jump() {
	// 选中周几
	var selectedDay = $("input[name='week']:checked").val();
	// 选中的星期是哪一天
	var date = $("#" + selectedDay).val();
	var shopName = getQueryString("shopName");
	var type = getQueryString("type");
	var from = getQueryString("from");
	if (type == "member" && from == "table") {
		selectedDay = getQueryString("weekDay");
		date = getQueryString("date");
	}

	if (selectedDay != getQueryString("weekDay")) {
		window.location.href = "shop_dessert?shopName=" + shopName
				+ "&weekDay=" + selectedDay + "&date=" + date + "&type=" + type;
	}
}

function setWeekBar() {
	$.ajax({
		url : 'api/shop/getSevenDay',
		type : 'post',
		dataType : 'json',
		data : {},
		success : function(result, textStatus) {
			var txt = '';

			var json = result.weeks;
			$.each(json, function(key, value) {
				// alert(key+":"+value);
				txt += getDayRadio(key, value);
			})
			$("#weekBar").html(txt);

			// 默认选中第一天
			var day = getQueryString("weekDay");
			if (day != null && day.toString().length > 1) {
				// 传过来的参数不为空
				// 为指定name的radio设置选中值
				var weeks = document.getElementsByName("week");
				for (var i = 0; i < weeks.length; i++) {
					if (weeks[i].value == day) {
						weeks[i].checked = true;
					} else {
						weeks[i].checked = false;
					}
				}
			} else {
				var weeks = document.getElementsByName("week");
				for (var i = 0; i < weeks.length; i++) {
					if (i == 0) {
						weeks[i].checked = true;
					} else {
						weeks[i].checked = false;
					}
				}
			}
			//jump();
		}
	});
}

function getDayRadio(date, weekDay) {
	var t = '\
		<label class="radio inline" style="margin-left:5%">\
			<input type="hidden" value="'
			+ date
			+ '" id="'
			+ weekDay
			+ '">\
			<input type="radio" name="week" value="'
			+ weekDay
			+ '" onclick="refreshSelectedDay();">\
	';
	if (weekDay == "Sunday") {
		t += '\
			周日\
		';
	} else if (weekDay == "Monday") {
		t += '\
			周一\
		';
	} else if (weekDay == "Tuesday") {
		t += '\
			周二\
		';
	} else if (weekDay == "Wednesday") {
		t += '\
			周三\
		';
	} else if (weekDay == "Thursday") {
		t += '\
			周四\
		';
	} else if (weekDay == "Friday") {
		t += '\
			周五\
		';
	} else if (weekDay == "Saturday") {
		t += '\
			周六\
		';
	}
	t += '\
		</label>\
	';

	return t;
}
