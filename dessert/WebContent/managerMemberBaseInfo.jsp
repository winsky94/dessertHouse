<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>图表统计</title>
	<meta charset="utf-8">
	<meta name='viewport' content='width=device-width, initial-scale=1'>

	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/ul-li.css">
	<link rel="stylesheet" type="text/css" href="css/table_css/sb-admin-2.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<style type="text/css">
		.num{
			font-size: 20px;
			font-weight: 800;
		}
	</style>

	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/cookie.js"></script>
	<script src="js/eChart-2.2.7/build/dist/echarts.js"></script>
	<script src="js/memberBaseAnalysis.js"></script>
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
			<!-- 左侧用户信息结束 -->
			<!--右侧会员具体信息 -->
			<div class="span10">
				<!-- 年份月份选择 -->
				<div style="margin-bottom: 20px;">
					<%@ include file="year-month-select.jsp"%>
				</div>
				<div class="well">
					<div style="font-family: 微软雅黑;">
						截止当前，
						本系统共有 <span id="totalNum" class="num"> </span> 名注册会员，
						当月新注册 <span id="newNum" class="num"> </span> 人，
						<span id="validNum" class="num"> </span> 人会员卡有效，
						<span id="pauseNum" class="num"> </span> 人会员卡停止，
						<span id="overNum" class="num"> </span> 人会员卡取消。
					</div>
				</div>
				<script type="text/javascript">
					getTotalInfo();
				</script>
				<div>
					<!-- 年龄分布 -->
					<div style="float: left;">
						<div id="age" style="width: 500px;height:400px;"></div>
					</div>
					<!-- 年龄分布 -->
					<!-- 性别分布 -->
					<div style="float: right;">
						<div id="sex" style="width: 500px;height:400px;"></div>
					</div>
					<!-- 性别分布 -->
				</div>
				
				

				<!-- 居住地分布 -->
				<div style="margin-left: 200px;">
					<div id="address" style="width: 600px;height:400px;"></div>
				</div>
				<!-- 居住地分布 -->
			</div>
		</div>
	</div>
	<!-- 正文内容 结束 -->
	<div style="height: 500px;"></div>
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