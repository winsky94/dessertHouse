<%@page import="dessert.util.UserType"%>
<%@ page pageEncoding="utf-8"%>
	<!-- 顶部导航栏 -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="header">
		<div class="navbar-inner">
			<a class="brand" href="#" style="margin-left: 5%">Dessert House</a>

			<ul class="nav navbar-nav clearfix pull-right">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</ul>
			<ul class="nav navbar-nav pull-right">
				<li >
					<a href="#loginModel" role="button" data-toggle="modal" >登录/注册</a>
				</li>
			</ul>
		</div>
	</div>
	<!-- 导航栏结束 -->

	<!-- Modal -->
	<div id="loginModel" class="modal hide fade" tabindex="-1" role="dialog"  aria-hidden="true">
		<div class="modal-header">
			<h3>登录</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal">
				<div class="control-group"></div>
				<div class="control-group">
					<label class="control-label" for="userName">用户名:</label>
					<div class="controls">
						<input type="text" id="userName" placeholder="请输入用户名...">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password">密　码:</label>
					<div class="controls">
						<input type="password" id="password" placeholder="请输入密码...">
					</div>
				</div>
			</form>
			
		</div>
		<div class="modal-footer">
			<div style="text-align:center">
				<button class="btn btn-primary" onclick="login()">登录</button>
				<button class="btn btn-primary" style="margin-left: 10px" onclick="sign_up();">注册</button>
				<font color="red" size="2" style="margin-left: 4%">
					<span id="message"></span>
				</font>
			</div>

		</div>
	</div>

	<script type="text/javascript">
		function login () {
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
				var userType=result.userType;
				var error="<%= UserType.error %>";
				if (message != error) {
					add_cookie('userName',userName,30*24);
					alert("成功啦");
		//			window.location.href = "index.html";
				} else {
					var divNode = $("#message");
					divNode.innerHTML = message;
				}
			}
		}

		function sign_up () {
			window.location.href="sign_up.html";
		}
	</script>

