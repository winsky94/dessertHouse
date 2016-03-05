<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>会员管理</title>
<meta charset="utf-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/ul-li.css">
<link rel="stylesheet" type="text/css"
	href="css/table_css/sb-admin-2.css">
<link rel="stylesheet" type="text/css" href="css/base.css">
<style>
#info {
	display: table;
	/*border-spacing: 10px;*/
	padding-left: 280px;
}

#edit_info {
	display: table;
	/*border-spacing: 10px;*/
	padding-left: 100px;
}

.table-row {
	/*display: table-row;*/
	padding-top: 20px;
}

.property {
	display: table-cell;
}

.value {
	display: table-cell;
}
</style>

<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/cookie.js"></script>
</head>
<body>
	<!-- 顶部导航栏 -->
	<header></header>
	<script type="text/javascript">
		write_header();
	</script>
	<!-- 导航栏结束 -->

	<!-- 正文内容 -->
	<div class="container-fluid">
		<div class="row-fluid">
			<!--左侧用户信息-->
			<%@ include file="WorkerInfo.jsp"%>
			<!-- 左侧用户信息结束 -->
			<!--右侧会员具体信息 -->
			<div class="span10">
				<div id="memberInfo">
					<div>
						<div style="height: 15px;"></div>
						<h4 style="display: inline;">密码修改</h4>
						<div style="display: inline;">
							<a class="btn pull-right margin_button" href="#" onclick="changePW();">
								<i class="icon-edit"></i>&nbsp;修改
							</a>
						</div>
						<div style="height: 15px;"></div>
					</div>
					<div style="height: 20px;"></div>
					<div class="" style="margin-left: 250px;">
						<form class="form-horizontal">
							<div class="row-fluid control-group"></div>
							
							<div class="row-fluid control-group">
								<label class="control-label" for="password">原密码：</label>
								<div class="controls">
									<input type="password" id="oldPassword" placeholder="请输入原密码">
								</div>
							</div>
							
							<div class="row-fluid control-group"></div>
							
							<div class="row-fluid control-group">
								<label class="control-label" for="password">新密码：</label>
								<div class="controls">
									<input type="password" id="newPassword" placeholder="请输入新密码">
								</div>
							</div>
							
							<div class="row-fluid control-group"></div>

							<div class="row-fluid control-group">
								<label class="control-label" for="confirm_password">确　认：</label>
								<div class="controls">
									<input type="password" id="confirm_password" placeholder="确认密码">
								</div>
							</div>
							<div class="row-fluid control-group"></div>
							
							<div class="row-fluid control-group">
								<div class="controls">
									<font color="red" size="3">
										<span id="message"></span>
									</font>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!--右侧会员具体信息 结束 -->
			</div>
		</div>
	</div>
	<!-- 正文内容结束 -->

	<!-- 页脚 -->
	<div class="navbar navbar-default navbar-fixed-bottom"
		role="navigation">
		<div class="navbar-inner text-center">
			<h4>
				© 2016 <abbr title="Email:ysk13@software.nju.edu.cn">winsky</abbr>,software institute, NJU
			</h4>
		</div>
	</div>

	<!-- 加载js -->
	<script src='bootstrap/js/bootstrap.js'></script>
	<script src='js/workerMyInfo.js'></script>
</body>
</html>