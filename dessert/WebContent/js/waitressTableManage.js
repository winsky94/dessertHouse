function getLine (tr) {
	cells=tr.cells;
}

function changeAction(action){
	$("#action").val(action);

	setShops();

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

			$("#type").attr('onchange',"changeOwing();");

			var wid = cells[0].innerHTML;
			wid = wid.replace(/[\r\n]/g,"");//去掉回车换行
		    var workerId = cells[1].innerHTML;
		    var type = cells[2].innerHTML;
		    var owingTo = cells[3].innerHTML;

			//给模态框的输入框赋值
			$("#wid").val(wid);
			$("#workerId").val(workerId);
			$("#type option[value="+type+"]").attr("selected",true); 

			$("#owingTo").val(owingTo);

			// 设置服务员编号不可以被修改
			$("#workerId").attr("disabled",true);
		}
	} else if(action=="add"){
		// 调整模态框
		$("#title").text("添加服务员");
		$("#type").attr("onchange","setNewWorkerId();changeOwing();")

		// 设置服务员工号不可以被输入
		$("#workerId").attr("disabled",true);
		$("#wid").val("-1");
		$("#owingTo").val("");

		setNewWorkerId();
		
	}
}

function process () {
	var action = $("#action").val();
	var ajax_url='';

	if(action=="add"){
		ajax_url='api/waitress/add';
	}else if(action=="update"){
		ajax_url='api/waitress/edit';
	}else{
		alert("ajax错误");
	}
	var wid = $("#wid").val();
	var workerId = $("#workerId").val();
	var type = $("#type").val();
	var owingTo = $("#owingTo").val();

	if (wid==""){
		$("#process_result").html("服务员id异常");
		return;
	}else if (workerId==""){
		$("#process_result").html("员工工号不能为空");
		return;
	}else if (type==""||type=="请选择职务"){
		$("#process_result").html("请选择员工职务");
		return;
	}else if (type!="总店服务员"&&owingTo==""){
		$("#process_result").html("服务员所属店面不能为空");
		return;
	}else if (type=="分店服务员"){
		var res=checkShop(owingTo);
		if(!res){
			$("#process_result").html("服务员所属店面不存在");
			$("#owingTo").val("");
			return;
		}
	}
	var userType="";

	if(type=="总店服务员"){
		userType='waitressManager';
	}else if(type=="分店服务员"){
		userType='waitress';
	}else {
		alert("用户身份错误");
		return;
	}

	$.ajax({
		url : ajax_url,
		type : 'post',
		dataType : 'json',
		data : {
			wid:wid,
			workerId : workerId,
			type : userType,
			owingTo:owingTo,
		},
		success : function(result, textStatus) {
			callback_func(result);
		}
	});
}


function deleteWaitress () {
	// 检测是否选中行
	if (typeof(cells) == "undefined") {
	   alert("请选择一行");
	   return;
	} else {
		if(window.confirm('你确定要删除所选店面吗？')){
		    var wid = cells[0].innerHTML;
		    wid = wid.replace(/[\r\n]/g,"");//去掉回车换行

		    $.ajax({
		    	url : 'api/waitress/delete',
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
			window.location.href = "waitress_manage";
		} else {
			$("#process_result").html(message);
		}
	}
}

//设置新员工编号
function setNewWorkerId () {
	//向后台请求这次增加服务员的工号，这是系统自动生成的
	var workerId="";

	var type=$("#type option:selected").text();
	if (type!="请选择职务") {
		var userType="";
		if(type=="总店服务员"){
			userType='waitressManager';
		}else if(type=="分店服务员"){
			userType='waitress';
		}else {
			alert("用户身份错误");
			return;
		}
		$.ajax({
			url : 'api/waitress/generateWaitressId',
			type : 'post',
			dataType : 'json',
			data : {
				type:userType
			},
			success : function(result, textStatus) {
				workerId=result.workerId;
				$("#workerId").val(workerId);
			}
		});
	} else{
		$("#workerId").val(workerId);
	}
}

//设置店面输入框的值
function setShops(){
	$.ajax({
		url : 'api/shop/getShops',
		type : 'post',
		dataType : 'json',
		data : {
		},
		success : function(result, textStatus) {
			var shops=result.shops;

			var shopList = document.createElement("datalist");
			shopList.setAttribute("id", "shopList");
			$("#data").append(shopList);

			for(var i=0;i<shops.length;i++){
				var shop=shops[i];

				var opt = document.createElement("option");
				opt.setAttribute("value", shop.name);
				$("#shopList").append(opt);
			}
		}
	});
}

function changeOwing () {
	// 设置总店服务员不可以选取所属店面
	var type=$("#type option:selected").text();
	if (type!="请选择职务"&&type=="总店服务员") {
		$("#owingTo").val("");
		$("#owingTo").attr("disabled",true);
		$("#owingTo").attr('placeholder','不隶属于任何店面');
	}else if(type=="分店服务员"){
		$("#owingTo").val("");
		$("#owingTo").attr("disabled",false);
		$("#owingTo").attr('placeholder','请输入所属店面...');
	}
}

//检查店面是否存在,true-存在 false-不存在
function checkShop(shopName){
	var res=false;
	$.ajax({
		url : 'api/shop/checkShop',
		async: false,//设置ajax同步,不然返回值在调用方if判断完了之后才会返回，返回时间不确定
		type : 'post',
		dataType : 'json',
		data : {
			name:shopName
		},
		success : function(result, textStatus) {
			var mes=result.message;
			if(mes=="所属店面存在") {
				res=true;
			}else {
				res=false;
			}
		}
	});
	return res;
}