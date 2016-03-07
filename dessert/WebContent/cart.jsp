<%@page import="dessert.util.DateUtil"%>
<%@page import="dessert.util.Week"%>
<%@page import="dessert.VO.CartDessert"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dessert.VO.Cart"%>
<%@page import="dessert.configure.Configure"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html>
<html>
<head>
	<title>我的购物车</title>
	<meta charset="utf-8">
	<meta name='viewport' content='width=device-width, initial-scale=1'>

	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/ul-li.css">
	<link rel="stylesheet" type="text/css" href="css/table_css/sb-admin-2.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/dessertDetail.css">

	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/cookie.js"></script>
	<script src="js/cart.js"></script>
</head>
<body>
	<!-- 顶部导航栏 -->
	<header></header>
	<script type="text/javascript">
		write_header();
	</script>
	<!-- 导航栏结束 -->

	<%
	String sessionName = Configure.Member_CART_SESSION;
	Cart myCart = (Cart) request.getSession().getAttribute(sessionName);
	ArrayList<CartDessert> carts = new ArrayList<CartDessert>();
	if (myCart != null) {
		carts = myCart.getMyCart();
	}else{
		ServletContext sc = getServletContext();
		RequestDispatcher rd = null;
		rd = sc.getRequestDispatcher("/EmptyCart.jsp"); //定向的页面
		rd.forward(request, response);
	}
	%>
	<!-- 正文内容 -->
	<div class="container-fluid">
		<div class="row-fluid">
			<!--左侧用户信息-->
			<%@ include file="memberInfo.jsp"%>
			<!-- 左侧用户信息结束 -->
			<!--右侧购物车具体信息 -->
			<div class="span10">
				<!-- 操作 -->
				<div style="margin-bottom: 20px;">
					<h4 style="display: inline;">购物车详情</h4>
					<div style="display: inline;margin-left: 20px;">
						<span class="price_word">购物车总额：</span>
						<span id="allMoney" class="price_num" style="margin-left: 10px;">0</span>元
					</div>
					<div style="display: inline;">
						<a class="btn pull-right margin_button" href="#"
							onclick="clearAll();"> <i class="icon-trash"></i>&nbsp;清空
						</a> 
						<a class="btn pull-right margin_button" href="#"
							onclick="selectAll();"> <i class="icon-check"></i>&nbsp;全选
						</a>
						<a class="btn pull-right margin_button" href="#"
							onclick="deleteSelected();"> <i class="icon-remove"></i>&nbsp;删除
						</a> 
						<a class="btn pull-right margin_button" href="#"
							onclick="buySelected();"> <i class=" icon-folder-open"></i>&nbsp;提交
						</a> 
					</div>
				</div>
				<div class="dotted_line" style="margin-bottom: 10px;"></div>
				<!-- 操作  结束-->
				<div id="cart" class="container-fluid">
					<%
						if(carts!=null&&carts.size()!=0){
							int i=0;
							for(CartDessert cartDessert:carts){
								i++;
								
								long id=cartDessert.getId();
								String name=cartDessert.getName();
								double price=cartDessert.getPrice();
								int stockNum=cartDessert.getStockNum();
								int num=cartDessert.getNum();
								String path=cartDessert.getPath();
								String owingTo=cartDessert.getOwingTo();
								Week weekDay=cartDessert.getWeekDay();
								String date=cartDessert.getDate();
					%>
					店铺： <span id="shopName" class="price_word<%=i %>"><%=owingTo %></span>
					<div class="well row-fluid row" style="margin-top: 10px;">
						<label class="checkbox inline pull-left">
							<input type="checkbox" name="checkbox" value="<%=i %>" onchange="getClick()">
						</label>
						<div class="span1 inline pull-left">
							<img src="<%=path %>">
						</div>

						<div class="span2">
							<h4 class="dessert_title" id="dessertName<%=i %>"><%=name %></h4>
							<input type="hidden" id="dessertId<%=i %>" value="<%=id %>">
						</div>
						
						<div class="span2" style="padding-top: 15px;">
							<span class="price_word">单价:</span>
							<span class="price_num"style="margin-left: 10px;">
								￥<strong id="price<%=i %>"><%=price %></strong>
							</span>
							<br><br>
							<span class="price_word" style="margin-left:15px;">
								库存:</span> <span class="price_word" id="stockNum<%=i %>">
										<%=stockNum %>
									</span>
									<span class="price_word">件</span>
						</div>
						<div class="span3">
							<div class="input-prepend input-append" style="margin-left: 10px;padding-top: 30px;">
								<button id="delBtn<%=i %>" class="btn" type="button" onclick="delNum(<%=i %>);">-</button>
								<input id="quantity<%=i %>" class="span2 text-center" type="text" value="<%=num %>" onblur="">
								<button id="addBtn<%=i %>" class="btn" type="button" onclick="addNum(<%=i %>);">+</button>
							</div>
						</div>
						<div class="span3">
							<div style="padding-top: 30px;">
								<span class="price_word">总价:</span>
								<span class="price_num" style="margin-left: 0px;">
									￥<strong id="totalMoney<%=i %>"><%=num*price %></strong>
								</span>

								<a href="#" style="margin-left: 10px;float: right" onclick="del(<%=i %>)">删除</a>
								<% 
								String today = DateUtil.getToday();
								
								if (today.equals(date)) {
								%>
								<input id="action<%=i %>" type="hidden" value="buy">
								<a href="#" style="float: right" onclick="buy(<%=i %>,'no','buy')">购买</a>
								<%
								}else{
									%>
								<input id="action<%=i %>" type="hidden" value="appointment">
								<a href="#" style="float: right" onclick="buy(<%=i %>,'no','appointment')">预约</a>	
								<%
								}
								%>
							</div>
						</div>
					</div>
					<script type="text/javascript">
						addListener(<%=i %>);
					</script>
					<%
							}
						}else{
							ServletContext sc = getServletContext();
							RequestDispatcher rd = null;
							rd = sc.getRequestDispatcher("/EmptyCart.jsp"); //定向的页面
							rd.forward(request, response);
						}
					%>
					
					
				</div>
			</div>
			<!--右侧购物车具体信息 结束 -->
		</div>
	</div>
	<!-- 正文内容 结束 -->

	<!-- 页脚 -->
	<div class="navbar navbar-default navbar-fixed-bottom"
		role="navigation">
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