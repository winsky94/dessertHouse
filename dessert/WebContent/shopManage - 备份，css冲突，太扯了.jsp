<%@page import="dessert.configure.Configure"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="dessert.VO.ShopVO" %>
<%@ page language="java" import="dessert.configure.*" %>

<html>
<head>
	<title>店面管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

	<!-- CSS  -->
	<link href="css/material_icons.css" rel="stylesheet" media="screen,projection">
	<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/table_css/jquery.dataTables.min.css" rel="stylesheet" media="screen,projection"/>
	<link href="css/table_css/select.dataTables.min.css" rel="stylesheet" media="screen,projection"/>
	<link href="css/table_css/buttons.dataTables.min.css" rel="stylesheet" media="screen,projection"/>
	<link href="css/table_css/editor.dataTables.min.css" rel="stylesheet" media="screen,projection"/>

	<!-- 必须在开头引入的js -->
	<script src='js/vendor/jquery-1.11.3.min.js'></script>
	<script src="js/cookie.js"></script>
	<script src="js/writeHTML.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/icheck.js"></script>
	<script src="js/table_js/jquery.dataTables.min.js"></script>
	<script src="js/table_js/dataTables.buttons.min.js" charset="UTF-8"></script>
	<script src="js/table_js/dataTables.select.min.js" charset="UTF-8"></script>
	<script src="js/table_js/dataTables.editor.min.js" charset="UTF-8"></script>
</head>
<body>
	<!-- 顶部导航栏 -->
	<header></header>
	<script type="text/javascript">
		// write_header();
	</script>
	
	<!-- 网页正文 -->
	<div class="main container row">
		<!-- 左侧 用户信息 3列-->
		<div class="col s12 l3">
	        <ul class="collection with-header">
	            <li class="collection-header">
			        <img src="image/index1.jpg" width="180px" height="150px">
		            <h5 id="userName">admin</h5>
		        </li>
		        <li>
		        	<p class="collection-item">最近登录时间：<br> &nbsp;&nbsp;&nbsp;&nbsp; 2016-01-20 15:50:00</p>
		        </li>
		    </ul>
		</div>
	    <!-- 左侧 3列 结束-->

	    <!-- 右侧分店信息表 9列 -->
	    <div class="col s12 l9">
	    	<div style="none">
	    		<div class="table-container table-responsive">
					<table id="shopTable" class="table display dataTable table-hover table-condensed table-striped table-sm" aria-describedby="shopTable" width="100%">
						<thead>
							<tr>
								<th class="text-center">名称</th>
								<th class="text-center">负责人</th>
								<th class="text-center">联系电话</th>
								<th class="text-center">地址</th>
							</tr>
						</thead>
						<tbody id="query-body">
							<%  @SuppressWarnings("unchecked")
								ArrayList<ShopVO> shops = (ArrayList<ShopVO>)session.getAttribute(Configure.SHOP_SESSION);
								if(shops==null||shops.isEmpty()){%>
									<tr>
										<td class="text-center">(空) </td>
										<td class="text-center">(空)</td>
										<td class="text-center">(空)</td>
										<td class="text-center">(空)</td>
									</tr>
								<% 
								} else {
									for(int i=0;i<shops.size();i++){
										ShopVO shopVO = shops.get(i);
								%>
									<tr>
										<td class="text-center"><%=shopVO.getName() %></td>
										<td class="text-center"><%=shopVO.getOwner() %></td>
										<td class="text-center"><%=shopVO.getTelephone()%></td>
										<td class="text-center"><%=shopVO.getAddress() %></td>
									</tr>
								<%
									}
								}
							%>
						</tbody>
					</table>
				</div>
	    	</div>
	    </div>
	    <!-- 右侧分店信息表 9列结束 -->
	</div>
	<!-- 网页正文结束 -->

	<!-- 页脚 -->
	<div id="footer"></div>
	<script type="text/javascript">
		write_footer();
	</script>

	<!-- 引入js -->
	<script>
        $(document).ready(function(){
              $('.skin-square input').iCheck({
                checkboxClass: 'icheckbox_square-purple',
                radioClass: 'iradio_square-purple'
              });
        });

        var editor; // use a global for the submit and return data rendering in the examples
 
		$(document).ready(function() {

    		editor = new $.fn.dataTable.Editor( {
        		
        		ajax:"",
		        table: "#shopTable",
		        fields: [ {
		                label: "名称:",
		                name: "name",
		            }, {
		                label: "负责人:",
		                name: "owner"
		            }, {
		                label: "联系电话:",
		                name: "telephone"
		            }, {
		                label: "地址:",
		                name: "address",
		            }
		        ]
    		} );

    		var table=$('#shopTable').dataTable( {
    			dom: "Bfrtip",
	        	select: true,
	        	filter: true,
	        	ordering: true,
	        	pageLength: 10,
			    buttons: [
			        { 	
			        	extend: "create", 
			        	editor: editor, 
			        	text: "增加", 
			        	className: "btn-primary",
			        	formMessage: "增加一条资助记录",
			    		formButtons: [
                			{
                   				label: '取消',
                    			fn: function () { this.close(); }
                			},
                			{
                				label: '确定',
                				className: "btn-primary",
                				fn: function() { alert("增加成功");this.close();  }
                			}
            			]},
			        { extend: "edit",   editor: editor, text: "编辑", className: "btn-primary"},
			        { extend: "remove", editor: editor, text: "删除", className: "btn-primary"}
			    ],
			     language: {
			        paginate: {
			            first:    '第一页',
			            previous: '<',
			            next:     '>',
			            last:     '最后一页'
			        },
			        "info": "第_PAGE_页，共_PAGES_页  共有记录_MAX_条",
			        "emptyTable": "没有符合条件的记录",
			        "zeroRecords": "没有符合条件的记录",
			        "infoEmpty": "检索到记录0条"
			    }

			} );
    		

		} );

		function queryt(){
			var table = $('#shopTable').DataTable();
			table.ajax.reload();
		}


		$(document).ready(function(){
    		    $("th").click(function(){
    		    	sort($(this).html());
    		    });
    	});
    </script>
</body>
</html>