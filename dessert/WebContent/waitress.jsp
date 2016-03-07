<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
	<title>分店服务员</title>
	<meta charset="utf-8">
	<meta http-equiv='x-ua-compatible' content='ie=edge'>
	<meta name='viewport' content='width=device-width, initial-scale=1'>

	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/ul-li.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/table_css/sb-admin-2.css"><!--达到灰色背景的效果-->
	<style>
		#info {
            display: table;
            /*border-spacing: 10px;*/
            padding-left: 280px;
        }

        #dessertInfo {
            display: table;
            /*border-spacing: 10px;*/
            padding-left: 280px;
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
	<script src="js/waitress.js"></script>
</head>
<body>
	<%
		String dessertId = request.getParameter("dessertId");
		String numStr = request.getParameter("num");
		String priceStr = request.getParameter("price");
		String action = request.getParameter("action");
		int num = 0;
		double price = 0;
		if (numStr != null) {
			num = Integer.parseInt(numStr);
		}
		if (priceStr != null) {
			price = Double.parseDouble(priceStr);
		}
		double money = num * price;
	%>
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
			<!--右侧销售流程 -->
			<div class="span10" id="desserts">
				<div class="form-horizontal">
					<div class="row-fluid control-group"></div>

					<div class="row-fluid control-group">
						<div class="span8">
							<label class="control-label" for="memberId">请输入会员编号：</label>
							<div class="controls">
								<input type="text" class="input-medium search-query" id="memberId">
								<button type="button" class="btn" onclick="getMemberInfo();" style="margin-left: 50px;">
									确定
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 会员信息 -->
				<div id="guestWell" class="well" style="display:none">
					<h3 style="margin-left: 60px;">会员信息</h3>
					<div id="info" style="width:800px;"></div>
				</div>
				<!-- 会员信息 结束 -->
				<!-- 商品信息 -->
				<div class="well">
					<h3 style="margin-left: 60px;">商品信息</h3>
					<div id="dessertInfo" style="width:800px;">
						<div class="span4">
							<div class=""> 
								<div class="property">编　号：</div> 
								<div class="value">
									<span id="dessertId"><%=dessertId %></span>
								</div> 
							</div>
							<div class="table-row"> 
								<div class="property">单　价：</div> 
								<div class="value">
									<span id="price"><%=price %></span>
								</div> 
							</div>
						</div>
						<div class="span4">
							<div class=""> 
								<div class="property">数　量：</div> 
								<div class="value">
									<span id="num"><%=num %></span>
								</div>
							</div>
							<div class="table-row"> 
								<div class="property">总　价：</div> 
								<div class="value" id="money"><%=money %>
								</div> 
							</div>
						</div>
					</div>
				</div>
				<!-- 商品信息 结束 -->
				
				<!-- 支付方式 -->
				<div class="form-horizontal well">
					<div class="row-fluid control-group">
						<div class="span8">
							<label class="control-label" for="payMethod">请选择支付方式：</label>
							<div class="controls">
								<div style="width:800px;margin-left: 102px;">
									<div class="span4">
										<div class="table-row"> 
											<label class="radio inline">
												<input type="radio" name="optionsRadios" id="card" value="card" checked onchange="showCash(false)">
												会员支付
											</label>
										</div>
									</div>
									<div class="span4">
										<div class="table-row"> 
											<label class="radio inline">
												<input type="radio" name="optionsRadios" id="cash" value="cash" onchange="showCash(true)">
												现金支付
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 支付方式 结束 -->
				
				<!-- 现金支付 -->
				<div class="form-horizontal well" style="padding-left: 200px;display: none" id="cashWall">
					<div class="row-fluid control-group"></div>
					<div class="row-fluid control-group">
						<div class="span8">
							<label class="control-label" for="payMoney">付款：</label>
							<div class="controls">
								<input type="text" id="payMoney" placeholder="请输入付款金额..." onblur="calChange()">
							</div>
						</div>
					</div>
					<div class="row-fluid control-group">
						<div class="span8">
							<label class="control-label" for="change">找零：</label>
							<div class="controls">
								<input type="text" id="change" placeholder="找零" value="" disabled>
							</div>
						</div>
					</div>
				</div>
				<!-- 现金支付结束 -->
				
				<!-- 操作按钮 -->
				<div class="form-horizontal well">
					<div class="row-fluid control-group">
						<div class="span8">
							<div class="controls">
								<div style="width:800px;margin-left: 102px;">
									<div class="span4">
										<div class="table-row"> 
											<input type="button" class="btn" onclick="confirm();" value="提交">
										</div>
									</div>
									<div class="span4">
										<div class="table-row"> 
											<input type="button" class="btn" onclick="cancel();" value="取消">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 操作按钮 结束 -->

			</div>
			<!--右侧销售流程结束 -->
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
	
	<div id="consume_result"></div>

	<!-- 加载js -->
	<script src='bootstrap/js/bootstrap.js'></script>
</body>
</html>