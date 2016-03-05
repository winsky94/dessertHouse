<%@page import="dessert.configure.Configure"%>
<%@page import="dessert.VO.ConsumeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>消费统计</title>
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
		#consumeTable thead tr th {
			text-align: center;
			font-size: 16px;
			line-height: 14px;
		}

		#consumeTable tbody tr td {
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
			<!--右侧消费记录表格 -->
			<div class="span10">
				<!-- 年份月份选择 -->
				<div style="margin-bottom: 20px;">
					<%@ include file="year-month-select.jsp"%>
				</div>
				<!-- 年份月份选择 结束 -->
				<div class="table-container table-responsive">
					<table id="consumeTable" class="table display dataTable">
						<thead>
							<tr>
								<th class="order-header text-center">会员编号</th>
								<th class="order-header text-center">商品名称</th>
								<th class="order-header text-center">数量</th>
								<th class="order-header text-center">优惠</th>
								<th class="order-header text-center">总价</th>
								<th class="order-header text-center">积分</th>
								<th class="order-header text-center">行为</th>
								<th class="order-header text-center">付款方式</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!-- 消费记录内容 结束 -->
		</div>
	</div>
	<!-- 正文内容 结束 -->

	<!-- 页脚 -->
	<div class="navbar navbar-default navbar-fixed-bottom" role="navigation">
		<div class="navbar-inner text-center">
			<h4>
				© 2016 <abbr title="Email:ysk13@software.nju.edu.cn">winsky</abbr>,software institute, NJU
			</h4>
		</div>
	</div>

	<!-- 加载js -->
	<script src="js/jquery-2.1.1.min.js"></script>
	<script src='bootstrap/js/bootstrap.js'></script>
	<script src="js/icheck.js"></script>
	<script src="js/table_js/jquery.dataTables.min.js"></script>
	<script src="js/table_js/dataTables.buttons.min.js" charset="UTF-8"></script>
	<script src="js/table_js/dataTables.select.min.js" charset="UTF-8"></script>
	<script src="js/managerConsume.js"></script>
	<script>
		$(document).ready(function() {
			var table=$('#consumeTable').DataTable({
				"ajax": {
				    url: "api/manager/consumeData",
				    "data":function() {
				    	var year=$("#year").val();
				    	var month=$("#month").val();

				    	arguments={
				    		"year":year,
				    		"month":month,
				    	}
				    	return arguments;
				    }
				  },
		        "columns": [
		            { "data": "memberId" },
		            { "data": "dessertName" },
		            { "data": "num" },
		            { "data": "discount" },
		            { "data": "money" },
		            { "data": "point" },
		            { "data": "action" },
		            { "data": "cash" }
		        ],
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
					"emptyTable" : "暂无消费记录...",
					"zeroRecords" : "没有找到符合条件的记录...",
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