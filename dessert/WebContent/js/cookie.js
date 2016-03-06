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

function write_header(index) {
	var login = check_cookie();
	if (!login) {
		// if(index!="index"){
		// 	window.location.href="index.html";
		// }else{
		// 	write_header_not_login();
		// }
		write_header_not_login();
	} else {
		var userName = get_cookie('userName');
		var type = get_cookie('type');
		write_header_login(userName, type);
	}
}

function exit() {
	delete_cookie('userName');
	delete_cookie('type');
	delete_cookie('lastLoadTime');
	delete_cookie('owingTo');
	delete_cookie('memberId');
	window.location.href="index.html";
}

function write_header_login(userName, type) {
	if (type == 'admin') {
		// 系统管理员的页头
		var txt = '\
			<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="header">\
				<div class="navbar-inner">\
					<a class="brand" href="index.html" style="margin-left: 5%">Dessert House</a>\
					\
					<ul class="nav navbar-nav" style="margin-left: 5%">\
						<li><a href="shop_manage">店面管理</a></li>\
						<li><a href="waitress_manage">店员管理</a></li>\
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
								<li><a href="workerMyInfo.jsp">修改密码</a></li>\
								<li class="divider"></li>\
								<li><a href="index.html" onclick="exit();">退出登录</a></li>\
							</ul>\
						</li>\
					</ul>\
				</div>\
			</div>\
		';
	} else if (type == 'member') {
		var txt = '\
			<!-- 顶部导航栏 -->\
			<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="header">\
				<div class="navbar-inner">\
					<a class="brand" href="index.html" style="margin-left: 5%">Dessert House</a>\
					<ul class="nav navbar-nav" style="margin-left: 5%">\
						<li class="dropdown">\
							<a href="#" class="dropdown-toggle"data-toggle="dropdown">产品预订<b class="caret"></b></a>\
							<ul class="dropdown-menu">\
								<li><a href="allDesserts.jsp">随便看看</a></li>\
								<li><a href="memebr_shop_list"">浏览店铺</a></li>\
								<li class="divider"></li>\
								<li><a href="#"">我的购物车</a></li>\
							</ul>\
						</li>\
						<li class="dropdown">\
							<a href="#" class="dropdown-toggle"data-toggle="dropdown">会员管理<b class="caret"></b></a>\
							<ul class="dropdown-menu">\
								<li><a id="consume_href" href="member_consume">消费记录</a></li>\
								<li><a id="recharge_href" href="member_recharge">充值记录</a></li>\
								<li class="divider"></li>\
								<li><a href="member_info">资格管理</a></li>\
							</ul>\
						</li>\
					</ul>\
					<ul class="nav navbar-nav clearfix pull-right">\
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
					</ul>\
					<ul class="nav navbar-nav clearfix pull-right">\
						<li class="dropdown">\
							<a href="#" class="dropdown-toggle"data-toggle="dropdown"> '+ userName +' <b class="caret"></b></a>\
							<ul class="dropdown-menu">\
								<li><a href="memberMyInfo.jsp">个人信息</a></li>\
								<li class="divider"></li>\
								<li><a href="index.html" onclick="exit();">退出登录</a></li>\
							</ul>\
						</li>\
					</ul>\
				</div>\
			</div>\
			<!-- 导航栏结束 -->\
		';
	} else if (type == 'waitressManager') {
		//总店服务员
		var txt = '\
			<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="header">\
				<div class="navbar-inner">\
					<a class="brand" href="index.html" style="margin-left: 5%">Dessert House</a>\
					<ul class="nav navbar-nav" style="margin-left: 5%">\
						<li><a href="ZD_shop">计划制定</a></li>\
					</ul>\
					<ul class="nav navbar-nav clearfix pull-right">\
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
					</ul>\
					<ul class="nav navbar-nav pull-right">\
						<li class="dropdown">\
							<a href="#" class="dropdown-toggle"data-toggle="dropdown"> '+ userName +' <b class="caret"></b></a>\
							<ul class="dropdown-menu">\
								<li><a href="workerMyInfo.jsp">修改密码</a></li>\
								<li class="divider"></li>\
								<li><a href="index.html" onclick="exit();">退出登录</a></li>\
							</ul>\
						</li>\
					</ul>\
				</div>\
			</div>\
		';
	} else if(type == 'waitress'){
		//分店服务员
		var txt = '\
			<!-- 顶部导航栏 -->\
			<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="header">\
				<div class="navbar-inner">\
					<a class="brand" href="index.html" style="margin-left: 5%">Dessert House</a>\
					<ul class="nav navbar-nav" style="margin-left: 5%">\
						<li><a href="shopDetail.jsp?shopName='+get_cookie("owingTo")+'">产品销售</a></li>\
						<li class="dropdown">\
							<a href="#" class="dropdown-toggle"data-toggle="dropdown">会员信息<b class="caret"></b></a>\
							<ul class="dropdown-menu">\
								<li><a href="waitressMember.jsp"">基本信息</a></li>\
								<li class="divider"></li>\
								<li><a href="waitress_consume">消费记录</a></li>\
								<li><a href="waitress_recharge"">充值记录</a></li>\
							</ul>\
						</li>\
					</ul>\
					<ul class="nav navbar-nav clearfix pull-right">\
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
					</ul>\
					<ul class="nav navbar-nav clearfix pull-right">\
						<li class="dropdown">\
							<a href="#" class="dropdown-toggle"data-toggle="dropdown"> '+ userName +' <b class="caret"></b></a>\
							<ul class="dropdown-menu">\
								<li><a href="workerMyInfo.jsp">修改密码</a></li>\
								<li class="divider"></li>\
								<li><a href="index.html" onclick="exit();">退出登录</a></li>\
							</ul>\
						</li>\
					</ul>\
				</div>\
			</div>\
			<!-- 导航栏结束 -->\
		';
	} else if(type == 'manager'){
		//经理
		var txt = '\
			<!-- 顶部导航栏 -->\
			<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="header">\
				<div class="navbar-inner">\
					<a class="brand" href="index.html" style="margin-left: 5%">Dessert House</a>\
					<ul class="nav navbar-nav" style="margin-left: 5%">\
						<li><a href="manager_shop_plan">计划审核</a></li>\
						<li class="dropdown">\
							<a href="#" class="dropdown-toggle"data-toggle="dropdown">统计分析<b class="caret"></b></a>\
							<ul class="dropdown-menu">\
								<li><a href="managerMemberBaseInfo.jsp">会员分布</a></li>\
								<li><a href="manager_consume">消费统计</a></li>\
								<li><a href="manager_recharge">充值统计</a></li>\
								<li><a href="memberFavorite.jsp">会员喜好</a></li>\
								<li class="divider"></li>\
								<li><a href="manager_sale">销售业绩</a></li>\
								<li><a href="manager_hot_dessert">热卖产品</a></li>\
							</ul>\
						</li>\
					</ul>\
					<ul class="nav navbar-nav clearfix pull-right">\
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
					</ul>\
					<ul class="nav navbar-nav clearfix pull-right">\
						<li class="dropdown">\
							<a href="#" class="dropdown-toggle"data-toggle="dropdown"> '+ userName +' <b class="caret"></b></a>\
							<ul class="dropdown-menu">\
								<li><a href="workerMyInfo.jsp">修改密码</a></li>\
								<li class="divider"></li>\
								<li><a href="index.html" onclick="exit();">退出登录</a></li>\
							</ul>\
						</li>\
					</ul>\
				</div>\
			</div>\
			<!-- 导航栏结束 -->\
		';
	}

	$("header").html(txt);
}

function write_header_not_login() {
	var txt = '\
		<!-- 顶部导航栏 -->\
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
							<input type="text" id="userName" placeholder="请输入用户名..." style="height:25px;">\
						</div>\
					</div>\
					<div class="control-group">\
						<label class="control-label" for="password">密　码:</label>\
						<div class="controls">\
							<input type="password" id="password" placeholder="请输入密码..." style="height:25px;">\
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
						<span id="message"></span>\
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
		var lastLoadTime = result.lastLoadTime;
		var error = "error";
		if (userType != error) {
			add_cookie('userName', userName, 30 * 24);
			add_cookie("type", userType, 30 * 24);
			add_cookie("lastLoadTime", lastLoadTime, 30 * 24);

			if(userType == "member"){
				//检查会员状态
				$.ajax({
					url : 'api/member/checkStatus',
					type : 'post',
					dataType : 'json',
					data : {
						name : userName,
					},
					success : function(result, textStatus) {
						var memberId=result.memberId;
						add_cookie("memberId", memberId, 30 * 24);

						var msg=result.message;
						var url="index.html";
						//关闭登录对话框
						$('#loginModel').modal('hide')
						if(msg==null||msg=="null"){
							alert("检查会员状态出错啦");
						}else if(msg=="您的会员资格尚未激活，请尽快充值>=200元以激活"){
							alert(msg);
							url="member_info?action=pay";
						}else if(msg=="您的会员资格已暂停，请尽快充值以恢复"){
							alert(msg);
							url="member_info?action=pay";
						}
						window.location.href = url;

						
					}
				});
			} else if(userType == "waitress"){
				//请求员工所属商店
				var workerId=get_cookie("userName");
				$.ajax({
					url : 'api/waitress/owingTo',
					type : 'post',
					dataType : 'json',
					data : {
						workerId : workerId,
					},
					success : function(result, textStatus) {
						var owingTo=result.owingTo;
						add_cookie("owingTo", owingTo, 30 * 24);

						var url="index.html";
						window.location.href = url;
					}
				});
			} else{
				var url="index.html";
				window.location.href = url;
			}
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
