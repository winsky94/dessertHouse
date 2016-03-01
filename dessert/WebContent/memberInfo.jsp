<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="dessert.VO.ShopVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page language="java" import="java.net.URLDecoder"%>
<%@ page language="java" import="dessert.service.ShopService"%>
<%@ page language="java" import="org.springframework.beans.factory.annotation.Autowired"%>
<!--左侧店面信息-->
<div class="span2">
	<script  src="js/memberInfo.js" ></script>
	<script type="text/javascript">
		writeMemberShowInfo();
	</script>
	<div id="memberInfo"></div>
</div>
<!-- 左侧店面信息结束 -->
