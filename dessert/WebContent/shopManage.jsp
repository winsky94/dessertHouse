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
	<link rel="stylesheet" type="text/css" href="css/table_css/metisMenu.css">
	<link href="css/table_css/jquery.dataTables.min.css" rel="stylesheet" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/table_css/dataTables.bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/table_css/dataTables.responsive.css">
	<link rel="stylesheet" type="text/css" href="css/table_css/sb-admin-2.css">
	<link href="css/table_css/editor.dataTables.min.css" rel="stylesheet" media="screen" />
	<link rel="stylesheet" type="text/css" href="font-awesome-4.4.0/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="css/ul-li.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.css">
	<link href="css/table_css/select.dataTables.min.css" rel="stylesheet" media="screen" />
	<link href="css/table_css/buttons.dataTables.min.css" rel="stylesheet" media="screen" />
	
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

		.point-line{
			cursor: pointer;
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
			<div class="span2">
				<ul class="collection">
					<li class="collection-item"><img src="image/index1.jpg" width="180px" height="150px">
						<h5 id="userName" class="text-center">admin</h5>
					</li>
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
					<table id="shopTable" class="table display dataTable" width="100%">
						<thead>
							<tr>
								<th class="order-header text-center">名称</th>
								<th class="order-header text-center">负责人</th>
								<th class="order-header text-center">联系电话</th>
								<th class="order-header text-center">地址</th>
							</tr>
						</thead>
						<tbody>
						<%
							@SuppressWarnings("unchecked")
							ArrayList<ShopVO> shops=(ArrayList<ShopVO>)session.getAttribute(Configure.SHOP_SESSION);
							for(ShopVO shopVO:shops){
								String name=shopVO.getName();
								String owner=shopVO.getOwner();
								String telephone=shopVO.getTelephone();
								String address=shopVO.getAddress();
						%>
							<tr class="point-line">
								<td><% out.println(name); %></td>
								<td><% out.println(owner); %></td>
								<td><% out.println(telephone); %></td>
								<td><% out.println(address); %></td>
							</tr>
						<%
							}
						%>
						</tbody>
						
					</table>
				</div>
			</div>
			<!--右侧店面表格结束 -->
		</div>
	</div>
	<!-- 正文内容结束 -->

	<!-- 页脚 -->
	<div class="navbar navbar-default navbar-fixed-bottom" role="navigation">
		<div class="navbar-inner text-center">
			<h4>
				© 2016 <abbr title="ysk13@software.nju.edu.cn">winsky</abbr>,software institute, NJU
			</h4>
		</div>
	</div>
	<!-- 加载js -->
	<script src="js/jquery-2.1.1.min.js"></script>

	<!--<script src='js/vendor/jquery-1.11.3.min.js'></script>-->
	<script src='bootstrap/js/bootstrap.js'></script>
	<script src="js/icheck.js"></script>
	<script src="js/table_js/jquery.dataTables.min.js"></script>
	<script src="js/table_js/dataTables.buttons.min.js" charset="UTF-8"></script>
	<script src="js/table_js/dataTables.select.min.js" charset="UTF-8"></script>
	<script>
		$(document).ready(function() {
			var table=$('#shopTable').DataTable({
				"bStateSave": true,
				select : true,
				filter : true,
				ordering : true,
				language : {
					paginate : {
						first : '第一页',
						previous : '<',
						next:     '>',
						last : '最后一页'
					},
					"info" : "第_PAGE_页，共_PAGES_页  共有记录_MAX_条",
					"emptyTable" : "",
					"zeroRecords" : "",
					"infoEmpty" : "检索到记录0条"
				}
			});
		});
	</script>
</body>
</html>