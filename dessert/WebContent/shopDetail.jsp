<%@page import="dessert.configure.Configure"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="dessert.configure.*"%>
<!-- http://localhost:8080/dessert/ -->

<html>
<head>
	<title>XX店面</title>
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
	<script src="js/shopDetail.js"></script>
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
			<%@ include file="ShopInfo.jsp"%>
			<!-- 左侧店面信息结束 -->
			<!--右侧店铺内甜点图片 -->
			<div class="span10" id="desserts">
				<!-- 选择周几的产品 -->
				<div id="weekBar" style="margin-left:15%">
					<script type="text/javascript">
						setWeekBar();
					</script>
				</div>
				<!-- 选择周几的产品 结束-->
				<div id="container">
					<!-- <div class="box">
						<div class="box_img">
							<a href="" target="_self">
								<img src="image/desserts/shop1/1.jpg">
								<div class="text">
									这是海中体育馆
								</div>
							</a>
						</div>
					</div> -->
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