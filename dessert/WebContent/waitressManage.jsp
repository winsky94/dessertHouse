<%@page import="dessert.util.UserType"%>
<%@page import="dessert.configure.Configure"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="dessert.VO.WorkerVO"%>
<%@ page language="java" import="dessert.configure.*"%>
<%@ page language="java" import="java.net.URLDecoder" %>

<html>
<head>
	<title>服务员管理</title>
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
		#waitressTable thead tr th {
			text-align: center;
			font-size: 16px;
			line-height: 14px;
		}

		#waitressTable tbody tr td {
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
			<!--右侧服务员表格 -->
			<div class="span10">
				<!-- 操作按钮 -->
				<div>
					<a class="btn btn-primary btn-small pull-right margin_button" href="#" onclick="deleteWaitress();">
						<i class="icon-minus icon-white"></i>删除
					</a>
					<a id="update" class="btn btn-primary btn-small pull-right margin_button" href="#waitressModel" role="button" data-toggle="modal" onclick="changeAction('update');">
						<i class="icon-edit icon-white"></i>修改
					</a>
					<a class="btn btn-primary btn-small pull-right margin_button" href="#waitressModel" role="button" data-toggle="modal" onclick="changeAction('add');">
						<i class="icon-plus icon-white"></i>增加
					</a>
				</div>
				<!-- 操作按钮结束 -->
				<div class="table-container table-responsive">
					<table id="waitressTable" class="table display dataTable">
						<thead>
							<tr>
								<th style="display:none">id</th>
								<th class="order-header text-center">工号</th>
								<th class="order-header text-center">职务</th>
								<th class="order-header text-center">所属店面</th>
								<th class="order-header text-center">上次登录时间</th>
							</tr>
						</thead>
						<tbody>
						<%
							@SuppressWarnings("unchecked")
							ArrayList<WorkerVO> workers=(ArrayList<WorkerVO>)session.getAttribute(Configure.WAITRESS_SESSION);
							for(WorkerVO workerVO:workers){
								long id=workerVO.getId();
								String workerId=workerVO.getWorkerId();
								String type=workerVO.getType();
								String owingTo=workerVO.getOwingTo();
								String lastLoadTime=workerVO.getLastLoadTime();
								if(lastLoadTime==null||lastLoadTime.equals("null")){
									lastLoadTime="-";
								}
						%>
							<tr class="point-line" onclick='getLine(this)'>
								<td style="display:none;"><% out.println(id); %></td>
								<td><% out.println(workerId); %></td>
								<td><% out.println(type); %></td>
								<td><% out.println(owingTo); %></td>
								<td><% out.println(lastLoadTime); %></td>
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
	<div id="waitressModel" class="modal hide fade" tabindex="-1" role="dialog"  aria-hidden="true">
		<div class="modal-header">
			<h3 id="title">添加员工</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal">
				<input type="hidden" id="wid" value="-1">
				<div class="control-group"></div>
				<div class="control-group">
					<label class="control-label" for="type">职　务:</label>
					<div class="controls">
						<select id="type" style="height:30px;width:206px;" onchange="setNewWorkerId();changeOwing();">
							<option value="请选择职务" disabled selected="selected">请选择职务</option>
							<option value="总店服务员">总店服务员</option>
							<option value="分店服务员">分店服务员</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="workerId">工　号:</label>
					<div class="controls">
						<input type="text" id="workerId" placeholder="工号..." style="height:30px;">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="owingTo">所属店面:</label>
					<div class="controls">
						<input type="text" id="owingTo" placeholder="请输入所属店面..." style="height:30px;" list="shopList" size="5">
						<div id="data"></div>
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
	<script src='bootstrap/js/bootstrap.js'></script>
	<script src="js/icheck.js"></script>
	<script src="js/table_js/jquery.dataTables.min.js"></script>
	<script src="js/table_js/dataTables.buttons.min.js" charset="UTF-8"></script>
	<script src="js/table_js/dataTables.select.min.js" charset="UTF-8"></script>
	<script src="js/waitressTableManage.js"></script>
	<script>
		$(document).ready(function() {
			var table=$('#waitressTable').DataTable({
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