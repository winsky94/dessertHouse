function getLine(tr) {
	var show_day = new Array('Sunday', 'Monday', 'TuesDay', 'Wednesday',
			'Thursday', 'Friday', 'Saturday');
	var time = new Date();
	var day = time.getDay();

	var weekDay = show_day[day];
	var date=new Date().format("yyyy-MM-dd");

	cells = tr.cells;
	var name = cells[1].innerHTML;
	var url = "shop_dessert?shopName=" + name + "&weekDay=" + weekDay
			+ "&date=" + date;
	window.location.href = url;
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
