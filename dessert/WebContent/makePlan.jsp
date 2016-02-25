<%@page import="dessert.configure.Configure"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="dessert.VO.DessertVO"%>
<!-- http://localhost:8080/dessert/ZD_waitress?day=Sunday&shopName=shop1&action=create -->
<html>
<head>
	<title>销售计划</title>
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
	<link href="uploadify/uploadify.css" rel="stylesheet" media="screen" />

	<style type="text/css">
		#dessertsTable tbody tr td img{
			width: 50px;
			height: 50px;
		}

		#dessertsTable thead tr th {
			text-align: center;
			vertical-align:middle;
			font-size: 16px;
			line-height: 14px;
		}

		#dessertsTable tbody tr td {
			text-align: center;
			vertical-align:middle;
			font-size: 16px;
			line-height: 14px;
		}
	</style>

	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/cookie.js"></script>
	<script src="js/makePlan.js"></script>
</head>
<body>
	<!-- 顶部导航栏 -->
	<header></header>
	<script type="text/javascript">
		write_header();
	</script>
	<!-- 导航栏结束 -->

	<!-- 当前店面名称 -->
	<input type="hidden" id="shopName" value="shop1">
	<input type="hidden" id="planId" value="-1">

	<!-- 正文内容 -->
	<div class="container-fluid">
		<div class="row-fluid">
			<!--左侧用户信息-->
			<div class="span2">
				<ul class="collection">
					<li class="collection-item"><img src="image/index1.jpg" width="180px" height="150px">
						<h5 id="userName" class="text-center">ZD0000001</h5>
					</li>
					<li class="collection-item">
						<p>
							最近登录时间：<br> &nbsp;&nbsp;&nbsp;&nbsp; 2016-01-20 15:50:00
						</p>
					</li>
				</ul>
			</div>
			<!-- 左侧用户信息结束 -->

			<!--右侧制定周销售计划 -->
			<div class="span10">
				<!-- 选择制定周几的计划 -->
				<div>
 					<label class="radio inline" style="margin-left:15%">
						<input type="radio" name="week" id="Sunday" value="Sunday" onclick="refresh()">
						  周日
					</label>
					<label class="radio inline" style="margin-left: 5%">
						<input type="radio" name="week" id="Monday" value="Monday" onclick="refresh()">
						  周一
					</label>
					<label class="radio inline" style="margin-left: 5%">
						<input type="radio" name="week" id="Tuesday" value="Tuesday" onclick="refresh()">
						  周二
					</label>
					<label class="radio inline" style="margin-left: 5%">
						<input type="radio" name="week" id="Wednesday" value="Wednesday" onclick="refresh()">
						  周三
					</label>
					<label class="radio inline" style="margin-left: 5%">
						<input type="radio" name="week" id="Thursday" value="Thursday" onclick="refresh()">
						  周四
					</label>
					<label class="radio inline" style="margin-left: 5%">
						<input type="radio" name="week" id="Friday" value="Friday" onclick="refresh()">
						  周五
					</label>
					<label class="radio inline" style="margin-left: 5%">
						<input type="radio" name="week" id="Saturday" value="Saturday" onclick="refresh()">
						  周六
					</label>

					<script type="text/javascript">
						//得到url的参数
						function getQueryString(name){
						    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
						    var r = window.location.search.substr(1).match(reg);
						    if(r!=null)return  unescape(r[2]); return null;
						}

						var day=getQueryString("day");
						if(day!=null&&day.toString().length>1){
							//传过来的参数不为空
							//为指定name的radio设置选中值
							var weeks=document.getElementsByName("week");
							for(var i=0;i<weeks.length;i++){
								var id = weeks[i].id;
								if (weeks[i].value==day) {
									weeks[i].checked=true;
								}else{
									weeks[i].checked=false;
								}
							}
						}
					</script>
				</div>
				<!-- 选择制定周几的计划 结束-->

				<!-- 操作按钮 -->
				<div>
					<a class="btn btn-primary btn-small pull-left margin_button" href="#" onclick="submitPlan();">
						<i class="icon-ok icon-white"></i>提交
					</a>

					<a class="btn btn-primary btn-small pull-right margin_button" href="#" onclick="deleteDessert();">
						<i class="icon-minus icon-white"></i>删除
					</a>
					<a id="update" class="btn btn-primary btn-small pull-right margin_button" href="#dessertModel" role="button" data-toggle="modal" onclick="changeAction('update');">
						<i class="icon-edit icon-white"></i>修改
					</a>
					<a class="btn btn-primary btn-small pull-right margin_button" href="#dessertModel" role="button" data-toggle="modal" onclick="changeAction('add');">
						<i class="icon-plus icon-white"></i>增加
					</a>
				</div>
				<!-- 操作按钮结束 -->

				<!-- 选择商品 -->
				<div style="margin-top: 2%">
					<div class="table-container table-responsive">
						<table id="dessertsTable" class="table display dataTable">
							<thead>
								<tr>
									<th style="display:none">id</th>
									<th style="display:none">picName</th>
									<th class="order-header text-center">图片</th>
									<th class="order-header text-center">名称</th>
									<th class="order-header text-center">售价</th>
									<th class="order-header text-center">销售量</th>
								</tr>
							</thead>
							<tbody>
							<%
								@SuppressWarnings("unchecked")
								ArrayList<DessertVO> desserts=(ArrayList<DessertVO>)session.getAttribute(Configure.PLAN_SESSION);
								if(desserts!=null){
									for (int i=0;i<desserts.size();i++) {
										DessertVO dessertVO=desserts.get(i);
										long id=dessertVO.getId();
										String picName=dessertVO.getPath();
										int index=picName.lastIndexOf("\\");
										picName=picName.substring(index+1);
										
										String path=dessertVO.getPath();
										String name=dessertVO.getName();
										double price=dessertVO.getPrice();
										int stockNum=dessertVO.getStockNum();
							%>
								<tr onclick='getLine(this);'>
									<td style="display:none;"><% out.println(id); %></td>
									<td style="display:none;"><% out.println(picName); %></td>
									<td class="point-line"><img src="<% out.println(path); %>"></td>
									<td class="point-line"><% out.println(name); %></td>
									<td class="point-line"><% out.println(price); %></td>
									<td class="point-line"><% out.println(stockNum); %></td>
								</tr>
							<%
									}
								}else{
							%>
							<%
								}
							%>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 选择商品 结束-->
			</div>
			<!--右侧制定周销售计划 结束 -->
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
	<div id="dessertModel" class="modal hide fade" tabindex="-1" role="dialog"  aria-hidden="true">
		<div class="modal-header">
			<h3 id="title">新加甜点</h3>
		</div>
		<div class="modal-body">
			<form id="dessertForm" class="form-horizontal" enctype="multipart/form-data">
				<input type="hidden" id="dessertId" value="-1">
				<!-- 记录商品属于哪个店铺 -->
				<input type="hidden" id="owingTo" value="shop1">
				<input type="hidden" id="originalPicName" value="null">
				<div class="control-group"></div>
				<div class="control-group">
					<label class="control-label" for="picture">图　片:</label>
					<div class="controls">
						<!-- <input type="file" id="picture" placeholder="请选择图片..." style="height:25px;"> -->
						<input type="hidden" value="" id="picName">
						<input type="file" name="uploadFiles" id="picture" class="btn btn-primary" value="选择图片"/>
					</div>
					
				</div>
				<div class="control-group">
					<label class="control-label" for="name">名　称:</label>
					<div class="controls">
						<input type="text" id="name" name="dessert.name" placeholder="请输入商品名称..." style="height:25px;">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="price">售　价:</label>
					<div class="controls">
						<input type="text" id="price" name="dessert.price" placeholder="请输入商品售价..." style="height:25px;" onkeyup="javascript:CheckInputIntFloat(this);">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="stockNum">库　存:</label>
					<div class="controls">
						<input type="text" id="stockNum" name="dessert.stockNum" placeholder="请输入可销售数量..." style="height:25px;" onkeyup="value=value.replace(/[^\d]/g,'')">
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
	
	<script src="uploadify/jquery.uploadify.js"></script>
	<script src="uploadify/upload.js"></script>
	<script>
		$(document).ready(function() {
			var table=$('#dessertsTable').DataTable({
				"bStateSave": true,
				select : true,
				filter : true,
				ordering : true,
				columnDefs:[{
                	orderable:false,//禁用排序
                	targets:[0,1]   //指定的列
             	}],
             	"lengthMenu": [[10, 20, 50, -1], [10, 20, 50, "All"]],
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