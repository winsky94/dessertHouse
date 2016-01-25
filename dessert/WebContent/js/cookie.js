//function open_modal(){
//    $('#modal1').openModal();
//}

//函数返回名称为name的cookie值，如果不存在则返回空
function get_cookie(name) {
	var strcookie = document.cookie;
	var arrcookie = strcookie.split("; ");
	for (var i = 0; i < arrcookie.length; i++) {
		var arr = arrcookie[i].split("=");
		if (arr[0] == name)
			return unescape(arr[1]);
	}
	return "";
}

function add_cookie(name, value, expireHours) {

	var cookieString = name + "=" + escape(value);
	// 判断是否设置过期时间
	if (expireHours > 0) {
		var date = new Date();
		date.setTime(date.getTime + expireHours * 3600 * 1000);
		cookieString = cookieString + "; expire=" + date.toGMTString();
	}
	document.cookie = cookieString;
}

function delete_cookie() {
	var date = new Date();
	date.setTime(date.getTime() - 10000);
	document.cookie = 'userName' + '=v; expire=' + date.toGMTString();
}

function check_cookie() {
	var userName = get_cookie('userName');
	if (userName != null && userName != "" && userName != 'v') {
		return true;
	} else {
		return false;
	}
}

function write_header() {
	var login = check_cookie();
	if (!login) {
		write_header_not_login();
	} else {
		var userName = get_cookie('userName');
		var type = get_cookie('type');
		write_header_login(userName, type);
	}
}

function exit() {
	delete_cookie('userName');
}

function write_header_login(userName, type) {
	if (type == 'admin') {
		// 系统管理员的页头
		var txt = '\
			<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="header">\
		<div class="navbar-inner">\
			<a class="brand" href="#" style="margin-left: 5%">Dessert House</a>\
\
			<ul class="nav navbar-nav" style="margin-left: 5%">\
				<li><a href="#">店面管理</a></li>\
				<li><a href="#">店员管理</a></li>\
			</ul>\
\
			<ul class="nav navbar-nav clearfix pull-right">\
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
			</ul>\
			<ul class="nav navbar-nav pull-right">\
				<li class="dropdown">\
					<a href="#" class="dropdown-toggle"data-toggle="dropdown"> '+ userName +' <b class="caret"></b></a>\
					<ul class="dropdown-menu">\
						<li><a href="#">个人信息</a></li>\
						<li class="divider"></li>\
						<li><a href="#">退出登录</a></li>\
					</ul>\
				</li>\
			</ul>\
		</div>\
	</div>\
		';
	} else if (type == 'user') {
		var txt = '';
	} else if (type == 'coach' || type == 'doctor') {
		var txt = '';
	}

	$("header").html(txt);
}

function write_header_not_login() {
	var txt = '<!-- 顶部导航栏 -->\
    	<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="header">\
	<div class="navbar-inner">\
		<a class="brand" href="#" style="margin-left: 5%">Dessert House</a>\
\
		<ul class="nav navbar-nav clearfix pull-right">\
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
		</ul>\
		<ul class="nav navbar-nav pull-right">\
			<li >\
				<a href="#loginModel" role="button" data-toggle="modal" >登录/注册</a>\
			</li>\
		</ul>\
	</div>\
</div>\
<!-- 导航栏结束 -->\
\
<!-- Modal -->\
<div id="loginModel" class="modal hide fade" tabindex="-1" role="dialog"  aria-hidden="true">\
	<div class="modal-header">\
		<h3>登录</h3>\
	</div>\
	<div class="modal-body">\
		<form class="form-horizontal">\
			<div class="control-group"></div>\
			<div class="control-group">\
				<label class="control-label" for="userName">用户名:</label>\
				<div class="controls">\
					<input type="text" id="userName" placeholder="请输入用户名...">\
				</div>\
			</div>\
			<div class="control-group">\
				<label class="control-label" for="password">密　码:</label>\
				<div class="controls">\
					<input type="password" id="password" placeholder="请输入密码...">\
				</div>\
			</div>\
		</form>\
		\
	</div>\
	<div class="modal-footer">\
		<div style="text-align:center">\
			<button class="btn btn-primary" onclick="login()">登录</button>\
			<button class="btn btn-primary" style="margin-left: 10px" onclick="sign_up();">注册</button>\
			<font color="red" size="2" style="margin-left: 4%">\
				<span id="message">呵呵</span>\
			</font>\
		</div>\
\
	</div>\
</div>\
';
	$("header").html(txt);
}

function login() {
	userName = $("#userName").val();
	var password = $("#password").val();

	$.ajax({
		url : 'api/login',
		type : 'post',
		dataType : 'json',
		data : {
			userName : userName,
			password : password,
		},
		success : function(result, textStatus) {
			callback_login(result);
		}
	});
}

// 回调函数
function callback_login(result) {
	if (result.length == 0) {
		alert("出现错误");
	} else {
		var userType = result.userType;
		var error = "error";
		if (userType != error) {
			add_cookie('userName', userName, 30 * 24);
			add_cookie("type", userType, 30 * 24);
			alert("成功啦");
			// window.location.href = "index.html";
		} else if(userType == error){
			// var divNode = $("#message");
			var divNode=document.getElementById("message");
			divNode.innerText = "用户名密码错误~";
		} else {
			alert("出错啦");
		}
	}
}

function sign_up() {
	window.location.href = "sign_up.html";
}
