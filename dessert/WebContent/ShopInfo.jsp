<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="dessert.VO.ShopVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page language="java" import="java.net.URLDecoder"%>
<%@ page language="java" import="dessert.service.ShopService"%>
<%@ page language="java" import="org.springframework.beans.factory.annotation.Autowired"%>
<!--左侧店面信息-->
<div class="span2">
	<%
		String param=request.getQueryString();
		String shopName="";
		if(param!=null){
			String []params = param.split("&");
			for(int i=0;i<params.length;i++){
				String temp=params[i];
				if(temp.startsWith("shopName=")){
					int index=temp.indexOf("=");
					shopName=temp.substring(index+1);
					//对中文店名进行解码，不然是乱码
					shopName=URLDecoder.decode(shopName, "utf-8");
				}
			}
		}
	%>
	<script  src="js/shopInfo.js" ></script>
	<script type="text/javascript">
		getShop('<%=shopName %>');
	</script>
	<div id="shopInfo"></div>
</div>
<!-- 左侧店面信息结束 -->
