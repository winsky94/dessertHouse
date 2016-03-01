<!DOCTYPE html>
<html>
<head>
	<title>甜点名</title>
	<meta charset="utf-8">
	<meta http-equiv='x-ua-compatible' content='ie=edge'>
	<meta name='viewport' content='width=device-width, initial-scale=1'>

	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/ul-li.css">
	<link rel="stylesheet" type="text/css" href="css/table_css/sb-admin-2.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/dessertDetail.css">

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
							店名：shop1
						</p>
					</li>
					<li class="collection-item">
						<p>
							负责人：严顺宽
						</p>
					</li>
					<li class="collection-item">
						<p>
							联系电话：18013878510
						</p>
					</li>
					<li class="collection-item">
						<p>
							地址：江苏省南京市鼓楼区汉口路22号
						</p>
					</li>
				</ul>
			</div>
			<!-- 左侧用户信息结束 -->
			<!--右侧店铺内甜点具体信息 -->
			<div class="span10">
				<!-- 右之左侧甜点图片 -->
				<div class="span4">
					<img src="image/desserts/1.jpg">
				</div>
				<!-- 右之左侧甜点图片 结束 -->

				<!-- 右之商品详情 -->
				<div class="span6">
					<h3 class="text-center dessert_title" id="dessertName">好好吃的商品</h3>
					<!-- 粉色底的条形区域 -->
					<div class="price_bar">
						<div style="height:12px;"></div>
						<div style="display: inline;">
							<ul class="report_data">
								<span class="price_word">价格:</span>
								<span class="price_num">
									￥<strong>10.00</strong>
								</span>
								<li class="sale_style co6 tc">
									<span class="f25 co6" id="meters_total">0</span>
									<br/>
									<span class="co8">交易成功</span>
								</li>
								<li class="comment_Style co6 tc" style="padding-top:0;">
									<span class="f25 co6" id="calories_total">0</span>
									<br/>
									<span class="co8">累计评论</span>
								</li>
							</ul>
						</div>
					</div>
					<!-- 粉色底条形区域结束 -->
					
					<!-- 购买数量 -->
					<div style="height:15px;"></div>
					<div>
						<span class="price_word" style="margin-left: 5%">数量:</span>
						<div class="input-prepend input-append" style="margin-left: 20px;">
							<button id="delBtn" class="btn" type="button" onclick="delNum();">-</button>
							<input id="quantity" class="span2 text-center" type="text" value="1">
							<button id="addBtn" class="btn" type="button" onclick="addNum();">+</button>
						</div>
						<span class="price_word">(库存:</span>
						<span class="price_word" id="stockNum">10</span>
						<span class="price_word">件)</span>
					</div>
					<!-- 购买数量 结束 -->

					<!-- 操作按钮 -->
					<div style="height:15px;"></div>
					<div>
						<button class="btn buyBtn btn-large" type="button" onclick="buy();">立即购买</button>
						<button class="btn appointmentBtn btn-large" type="button" onclick="appointment();">现在预约</button>
						<button class="btn cartBtn btn-large" type="button" onclick="cart();">
							<i class="icon-shopping-cart"></i>&nbsp;加入购物车
						</button>
					</div>
					<!-- 操作按钮结束 -->
					
				</div>
				<!-- 右之商品详情 结束 -->
			</div>
			<!--右侧甜点具体信息 结束 -->
	
			<!-- 商品评论 -->
			
			<div id="comments" class="span10" style="margin-top: 0px;">
				<div style="height:15px;"></div>
				<!-- 虚线效果 -->
				<div class="dotted_line"></div>
				
				<h4>宝贝评论</h4>
				<!-- 评论列表 -->
				<div id="comments" class="container-fluid">
					<!-- 评论墙 -->
					<div class="well row-fluid row">
						<!-- 左侧用户信息 -->
						<div class="span1">
							<img src="image/2.jpg">
							<div class="center" style="margin-top: 10px;">
								<div class="price_word center">捕风</div>
								<div class="price_word center">2016.03.01</div>
							</div>
						</div>
						<!-- 左侧用户信息 结束 -->
						<!-- 评论的具体内容 -->
						<div class="span4">
							<div id="comment_content">这个蛋糕好好吃啊，简直不能停，强烈安利一发</div>
							<div id="comment_date" class="price_word" style="margin-top: 10px;">2016.02.29</div>
						</div>
						<!-- 评论的具体内容 结束 -->
					</div>
					<!-- 评论墙 结束 -->

				</div>
			</div>
			<!-- 商品评论 结束 -->
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
	<script src="js/dessertDeatail.js"></script>
</body>
</html>