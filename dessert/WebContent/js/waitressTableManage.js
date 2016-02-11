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
			$("#update").attr('href','#waitressModel');
			$("#title").text("修改服务员信息");

			var wid = cells[0].innerHTML;
			wid = wid.replace(/[\r\n]/g,"");//去掉回车换行
		    var workerId = cells[1].innerHTML;
		    var type = cells[2].innerHTML;
		    var owingTo = cells[3].innerHTML;
		    var address = cells[4].innerHTML;

			//给模态框的输入框赋值
			$("#wid").val(wid);
			$("#workerId").val(workerId);
			$("#type").val(type);
			$("#owingTo").val(owingTo);
			$("#address").val(address);

			// 设置服务员编号不可以被修改
			$("#workerId").attr("disabled",true);
		}
	} else if(action=="add"){
		// 调整模态框
		$("#title").text("添加服务员");
		// 设置服务员工号可以被输入
		$("#workerId").attr("disabled",false);
		$("#wid").val("-1");
		$("#workerId").val("");
		$("#type").val("");
		$("#owingTo").val("");
		$("#address").val("");
	}
}

function process () {
	var action = $("#action").val();
	var ajax_url='';
	if(action=="add"){
		ajax_url='api/worker/add';
	}else if(action=="update"){
		ajax_url='api/worker/edit';
	}else{
		alert("ajax错误");
	}
	var wid = $("#wid").val();
	var workerId = $("#workerId").val();
	var type = $("#type").val();
	var owingTo = $("#owingTo").val();
	var address = $("#address").val();

	if (wid==""){
		$("#process_result").html("店面id异常");
		return;
	}else if (workerId==""){
		$("#process_result").html("店面名字不能为空");
		return;
	}else if (type==""){
		$("#process_result").html("店面负责人不能为空");
		return;
	}else if (owingTo==""){
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
			wid:wid,
			workerId : workerId,
			type : type,
			owingTo:owingTo,
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
		    var wid = cells[0].innerHTML;
		    wid = wid.replace(/[\r\n]/g,"");//去掉回车换行

		    $.ajax({
		    	url : 'api/worker/delete',
		    	type : 'post',
		    	dataType : 'json',
		    	data : {
		    		wid:wid
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