<%@page import="dessert.configure.Configure"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="dessert.VO.ShopVO"%>
<%@ page language="java" import="dessert.configure.*"%>
<!-- http://localhost:8080/dessert/shop_manage -->
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
			<!--左侧用户信息 结束-->
			<!--右侧店面表格 -->
			<div class="span10">
				<!-- 操作按钮 -->
				<div>
					<a class="btn btn-primary btn-small pull-right margin_button" href="#" onclick="deleteShop();">
						<i class="icon-minus icon-white"></i>删除
					</a>
					<a id="update" class="btn btn-primary btn-small pull-right margin_button" href="#shopModel" role="button" data-toggle="modal" onclick="changeAction('update');">
						<i class="icon-edit icon-white"></i>修改
					</a>
					<a class="btn btn-primary btn-small pull-right margin_button" href="#shopModel" role="button" data-toggle="modal" onclick="changeAction('add');">
						<i class="icon-plus icon-white"></i>增加
					</a>
				</div>
				<!-- 操作按钮结束 -->
				<div class="table-container table-responsive">
					<table id="shopTable" class="table display dataTable">
						<thead>
							<tr>
								<th style="display:none">id</th>
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
								long shopId=shopVO.getId();
								String name=shopVO.getName();
								String owner=shopVO.getOwner();
								String telephone=shopVO.getTelephone();
								String address=shopVO.getAddress();
						%>
							<tr class="point-line" onclick='getLine(this)'>
								<td style="display:none;"><% out.println(shopId); %></td>
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
				© 2016 <abbr title="Email:ysk13@software.nju.edu.cn">winsky</abbr>,software institute, NJU
			</h4>
		</div>
	</div>


	<!-- Modal -->
	<div id="shopModel" class="modal hide fade" tabindex="-1" role="dialog"  aria-hidden="true">
		<div class="modal-header">
			<h3 id="title">创建店面</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal">
				<input type="hidden" id="shopId" value="-1">
				<div class="control-group"></div>
				<div class="control-group">
					<label class="control-label" for="name">店　名:</label>
					<div class="controls">
						<input type="text" id="name" placeholder="请输入店名..." style="height:25px;">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="owner">负责人:</label>
					<div class="controls">
						<input type="text" id="owner" placeholder="请输入负责人..." style="height:25px;">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="telephone">电　话:</label>
					<div class="controls">
						<input type="tel" id="telephone" placeholder="请输入联系电话..." style="height:25px;">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="address">地　址:</label>
					<div class="controls">
						<input type="text" id="address" placeholder="请输入地址..." style="height:25px;">
					</div>
				</div>
			</form>

		</div>
		<div class="modal-footer">
			<div style="text-align:center">
				<input type="hidden" id="action" value="add">
				<button class="btn btn-primary" onclick="process()">确定</button>
				<button class="btn btn-primary" style="margin-left: 10%" data-dismiss="modal" aria-hidden="true">取消</button>
				<font color="red" size="2" style="margin-left: 4%">
					<span id="process_result"></span>
				</font>
			</div>

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
	<script src="js/shopTableManage.js"></script>
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
					"info" : "第_PAGE_页，共_PAGES_页，共有记录_MAX_条",
					"emptyTable" : "",
					"zeroRecords" : "",
					"infoEmpty" : "检索到记录0条",
					"lengthMenu": "每页显示 _MENU_ 条记录",
					"infoFiltered": "(从 _MAX_ 条记录筛选)",
					"loadingRecords": "载入中...",
					"processing": "处理中..."
				}
			});
		});
	</script>
</body>
</html>