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
<script src="js/memberManage.js"></script>
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
			<%@ include file="memberInfo.jsp"%>
			<!-- 左侧用户信息结束 -->
			<!--右侧会员具体信息 -->
			<div class="span10">
				<div id="memberInfo">
					<div>
						<div style="height: 15px;"></div>
						<h4 style="display: inline;">个人资料</h4>
						<div style="display: inline;">
							<a class="btn pull-right margin_button" href="#"
								onclick="editInfo();"> <i class="icon-edit"></i>&nbsp;编辑
							</a>
						</div>
						<div style="height: 15px;"></div>
					</div>
					<div style="height: 20px;"></div>
					<div class="well">
						<!-- 会员信息 -->
						<div id="info" style="width: 800px;">
							<script type="text/javascript">
								getMemberInfo();
								//showInfo();
							</script>
						</div>
						<!-- 会员信息 结束 -->
						<!-- 修改会员信息 -->
						<div id="edit_info" style="width: 800px;"></div>
						<!-- 修改会员信息 结束 -->
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
	<script src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
		src="js/city_select/jquery.cityselect.js"></script>
</body>
</html>