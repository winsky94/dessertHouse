<%@page import="dessert.configure.Configure"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="dessert.VO.ShopVO"%>
<%@ page language="java" import="dessert.configure.*"%>

<html>
<head>
<title>店面管理</title>
<meta charset="utf-8">
<meta http-equiv='x-ua-compatible' content='ie=edge'>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/ul-li.css">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link href="skins/square/red.css" rel="stylesheet" />
<link href="skins/all.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/buttons.css">
<link href="css/table_css/jquery.dataTables.min.css" rel="stylesheet"
	media="screen" />
<link href="css/table_css/select.dataTables.min.css" rel="stylesheet"
	media="screen" />
<link href="css/table_css/buttons.dataTables.min.css" rel="stylesheet"
	media="screen" />
<link href="css/table_css/editor.dataTables.min.css" rel="stylesheet"
	media="screen" />
<style type="text/css">
#shopTable thead tr th {
	text-align: center;
	font-size: 16px;
	line-height: 14px;
}

#shopTable tbody tr td {
	text-align: center;
	font-size: 16px;
	line-height: 14px;
}

.order-header {
	cursor: pointer;
}
</style>
</head>
<body>
	<!-- 顶部导航栏 -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation"
		id="header">
		<div class="navbar-inner">
			<a class="brand" href="#" style="margin-left: 5%">Dessert House</a>

			<ul class="nav navbar-nav" style="margin-left: 5%">
				<li><a href="#">健康管理</a></li>
				<li><a href="#">统计</a></li>
				<li><a href="#">活动</a></li>
				<li><a href="#">建议</a></li>
				<li><a href="#">圈子</a></li>
			</ul>

			<!-- <ul class="nav navbar-nav" style="margin-right: 10px">
					<li >
						<a href="#loginModel" role="button" data-toggle="modal" >登录/注册</a>
					</li>
				</ul> -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> 小王 <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#">登录</a></li>
						<li><a href="#">注册</a></li>
						<li><a href="#">退出</a></li>
						<li class="divider"></li>
						<li><a href="#">邀请好友</a></li>
						<li class="divider"></li>
						<li><a href="#">分享</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<!-- 导航栏结束 -->

	<!-- 正文内容 -->
	<div class="container-fluid">
		<div class="row-fluid">
			<!--左侧用户信息-->
			<div class="span2">
				<ul class="collection">
					<li class="collection-item"><img src="image/index1.jpg"
						width="180px" height="150px">
						<h5 id="userName" class="text-center">admin</h5></li>
					<li class="collection-item">
						<p>
							最近登录时间：<br> &nbsp;&nbsp;&nbsp;&nbsp; 2016-01-20 15:50:00
						</p>
					</li>
				</ul>
			</div>
			<!-- 左侧用户信息结束 -->
			<!--右侧店面表格 -->
			<div class="span10">
				<div class="table-container table-responsive">
					<table id="shopTable"
						class="table display dataTable table-hover table-condensed table-striped table-sm" width="100%">
						<thead>
							<tr>
								<th class="order-header text-center">名称</th>
								<th class="order-header text-center">负责人</th>
								<th class="order-header text-center">联系电话</th>
								<th class="order-header text-center">地址</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!--右侧店面表格结束 -->
		</div>
	</div>
	<!-- 正文内容结束 -->

	<!-- 页脚 -->
	<div class="navbar navbar-default navbar-fixed-bottom"
		role="navigation">
		<div class="navbar-inner text-center">
			<h4>
				© 2016 <abbr title="ysk13@software.nju.edu.cn">winsky</abbr>,software
				institute, NJU
			</h4>
		</div>
	</div>
	<!-- 加载js 
	<script src="js/jquery-2.1.1.min.js"></script>-->
	<script src='js/vendor/jquery-1.11.3.min.js'></script>
	<script src='bootstrap/js/bootstrap.js'></script>
	<script src="js/icheck.js"></script>
	<script src="js/table_js/jquery.dataTables.min.js"></script>
	<script src="js/table_js/dataTables.buttons.min.js" charset="UTF-8"></script>
	<script src="js/table_js/dataTables.select.min.js" charset="UTF-8"></script>
	<script src="js/table_js/dataTables.editor.min.js" charset="UTF-8"></script>
	<script>
		var editor; // use a global for the submit and return data rendering in the examples

		$(document).ready(function() {
			editor = new $.fn.dataTable.Editor({
				ajax : {
					create : "api/shop/add",
					edit : "api/shop/edit",
					remove : "api/shop/delete"
				},
				dataSrc : 'shops',
				table : "#shopTable",
				"idSrc" : "id",
				fields : [ {
					label : "名称:",
					name : "name",
				}, {
					label : "负责人:",
					name : "owner"
				}, {
					label : "联系电话:",
					name : "telephone"
				}, {
					label : "地址:",
					name : "address"
				} ]
			});
			var table=$('#shopTable').DataTable({
				dom : "Bfrtip",
				ajax : {
				    url: "api/shop/getShops",
				    dataSrc: 'shops',
				    "data":{}
				  },
				columns : [ 
					{data : "name"}, 
					{data : "owner"}, 
					{data : "telephone"}, 
					{data : "address"} 
				],
				select : true,
				filter : true,
				ordering : true,
				buttons : [ 
					{
						extend : "create",
						editor : editor,
						text : "增加",
						className : "btn btn-default data-table-btn"
					}, {
						extend : "edit",
						editor : editor,
						text : "修改",
						className : "btn btn-default data-table-btn"
					}, {
						extend : "remove",
						editor : editor,
						text : "删除",
						className : "btn btn-default data-table-btn",
						formMessage : "确认删除该记录吗"
					} 
				],
				language : {
					paginate : {
						first : '第一页',
						previous : '<',
						next:     '>',
						last : '最后一页'
					},
					"info" : "第_PAGE_页，共_PAGES_页  共有记录_MAX_条",
					"emptyTable" : "没有符合条件的记录",
					"zeroRecords" : "没有符合条件的记录",
					"infoEmpty" : "检索到记录0条"
				}
			});
		});
	</script>
</body>
</html>