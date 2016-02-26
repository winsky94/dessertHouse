<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page language="java" import="java.net.URLDecoder"%>
<!--左侧用户信息-->
<div class="span2">
	<ul class="collection">
		<%
			String userName = "";
			String lastTime = "-";
			Cookie[] cookies = request.getCookies();
			//遍历cookie
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];

				//对有中文的cookie进行解码，不然是乱码
				if (cookie.getName().equals("lastLoadTime")) {
					lastTime = URLDecoder.decode(cookie.getValue(), "utf-8");
					continue;
				}
				if (cookie.getName().equals("userName")) {
					userName = URLDecoder.decode(cookie.getValue(), "utf-8");
					continue;
				}
			}
		%>
		<li class="collection-item"><img src="image/index1.jpg"
			width="180px" height="150px">
			<h5 id="userName" class="text-center"><%=userName%></h5></li>
		<li class="collection-item">
			<p>
				上次登录时间：
				<%
				if (!"-".equals(lastTime)) {
			%>
				<br>
				<%
					}
				%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<%=lastTime%>
			</p>
		</li>
	</ul>
</div>
<!-- 左侧用户信息结束 -->
