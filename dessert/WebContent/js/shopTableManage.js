function getLine (tr) {
	cells=tr.cells;
}

function changeAction(action){
	$("#action").val(action);

	if(action=="update"){
		// 检测是否选中行
		if (typeof(cells) == "undefined") {
		   alert("请选择一行");
		   $("#update").attr('href','#');
		   return;
		} else {
			// 调整模态框
			$("#update").attr('href','#shopModel');
			$("#title").text("修改店面信息");

			var shopId = cells[0].innerHTML;
			shopId = shopId.replace(/[\r\n]/g,"");//去掉回车换行
		    var name = cells[1].innerHTML;
		    var owner = cells[2].innerHTML;
		    var telephone = cells[3].innerHTML;
		    var address = cells[4].innerHTML;

			//给模态框的输入框赋值
			$("#shopId").val(shopId);
			$("#name").val(name);
			$("#owner").val(owner);
			$("#telephone").val(telephone);
			$("#address").val(address);

			// 设置店名不可以被修改
			$("#name").attr("disabled",true);
		}
	} else if(action=="add"){
		// 调整模态框
		$("#title").text("创建店面");
		// 设置店名可以被输入
		$("#name").attr("disabled",false);
		$("#shopId").val("-1");
		$("#name").val("");
		$("#owner").val("");
		$("#telephone").val("");
		$("#address").val("");
	}
}

function process () {
	var action = $("#action").val();
	var ajax_url='';
	if(action=="add"){
		ajax_url='api/shop/add';
	}else if(action=="update"){
		ajax_url='api/shop/edit';
	}else{
		alert("ajax错误");
	}
	var shopId = $("#shopId").val();
	var name = $("#name").val();
	var owner = $("#owner").val();
	var telephone = $("#telephone").val();
	var address = $("#address").val();

	if (shopId==""){
		$("#process_result").html("店面id异常");
		return;
	}else if (name==""){
		$("#process_result").html("店面名字不能为空");
		return;
	}else if (owner==""){
		$("#process_result").html("店面负责人不能为空");
		return;
	}else if (telephone==""){
		$("#process_result").html("联系电话不能为空");
		return;
	}else if (address==""){
		$("#process_result").html("店面地址不能为空");
		return;
	}

	$.ajax({
		url : ajax_url,
		type : 'post',
		dataType : 'json',
		data : {
			shopId:shopId,
			name : name,
			owner : owner,
			telephone:telephone,
			address:address
		},
		success : function(result, textStatus) {
			callback_func(result);
		}
	});
}


function deleteShop () {
	// 检测是否选中行
	if (typeof(cells) == "undefined") {
	   alert("请选择一行");
	   return;
	} else {
		if(window.confirm('你确定要删除所选店面吗？')){
		    var shopId = cells[0].innerHTML;
		    shopId = shopId.replace(/[\r\n]/g,"");//去掉回车换行

		    $.ajax({
		    	url : 'api/shop/delete',
		    	type : 'post',
		    	dataType : 'json',
		    	data : {
		    		shopId:shopId
		    	},
		    	success : function(result, textStatus) {
		    		callback_func(result);
		    	}
		    });
		}
	}
}

// 回调函数
function callback_func(result) {
	if (result.length == 0) {
		alert("出现错误");
	} else {
		var message = result.message;
		var success = "success";
		if (message == success) {
			window.location.href = "shop_manage";
		} else {
			$("#process_result").html(message);
		}
	}
}