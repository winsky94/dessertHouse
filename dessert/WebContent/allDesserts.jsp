<%@page import="dessert.configure.Configure"%>
<%@page import="dessert.VO.DessertVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<!-- http://localhost:8080/dessert/shopDetail.jsp?shopName=shop1 -->

<html>
<head>
	<title>商品列表</title>
	<meta charset="utf-8">
	<meta http-equiv='x-ua-compatible' content='ie=edge'>
	<meta name='viewport' content='width=device-width, initial-scale=1'>

	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/table_css/sb-admin-2.css">
	<link rel="stylesheet" type="text/css" href="css/ul-li.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/瀑布流效果.css">

	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/cookie.js"></script>
	<script src="js/瀑布流.js"></script>
	<!-- <script src="js/allDesserts.js"></script> -->
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
			<!--左侧店面信息-->
			<%@ include file="memberInfo.jsp"%>
			<!-- 左侧店面信息结束 -->
			<!--右侧店铺内甜点图片 -->
			<div class="span10" id="desserts">
				<div id="container">
				<%
				String sessionName=Configure.All_DESSERT_SESSION;
				@SuppressWarnings("unchecked")
				ArrayList<DessertVO> desserts =(ArrayList<DessertVO>)session.getAttribute(sessionName);
				if(desserts!=null){
					for(DessertVO dessert:desserts){
						long id=dessert.getId();
						String path=dessert.getPath();
						String name=dessert.getName();
						double price=dessert.getPrice();
						int stockNum=dessert.getStockNum();
						String shopName=dessert.getOwingTo();
						%>
						<div class="box">
							<input id="shopName" type="hidden" value="'+shopName+'">
							<div class="box_img">
								<a href="dessert_detail?shopName=<%=shopName %>&id=<%=id %>&type=member" target="_self">
									<img src="<%=path %>">
									<br>
									<div class="text_center">
										<span><%=name %></span>
									</div>
								</a>
								<div class="text" >
									<span style="color: red">￥<%=price %> </span>
									<span style="float:right;color:rgb(163,167,176)">库存：<%=stockNum %>个</span>
								</div>
							</div>
						</div>
						<%
					}
				}else{
					%>
					<p class="text-center muted" style="padding-top:5%;font-size:16px;">暂无可售商品~~~敬请期待...</p>
					<%
				}
				%>
				</div>
			</div>
			<!--右侧店面甜点图片结束 -->
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

	<!-- 加载js -->
	<script src='bootstrap/js/bootstrap.js'></script>
</body>
</html>