function refresh(){
	var shopName = $("#shopName").val();
	//获取选中的值
	var selectedDay=$("input[name='week']:checked").val();
	var params={
		shopName:shopName
	}
	$.getJSON("api/plan/getPlans",params, function(data) {
		//js解析json格式的字符串
		var obj = eval("("+data.plansJson+")");
        //如果有，就拼装表格数据

        if(selectedDay=="Sunday"){
        	var plans=obj.Sunday;
        }else if (selectedDay=="Monday") {
        	var plans=obj.Monday;
        }else if (selectedDay=="Tuesday") {
        	var plans=obj.Tuesday;
        }else if (selectedDay=="Wednesday") {
        	var plans=obj.Wednesday;
        }else if (selectedDay=="Thursday") {
        	var plans=obj.Thursday;
        }else if (selectedDay=="Friday") {
        	var plans=obj.Friday;
        }else if (selectedDay=="Saturday") {
        	var plans=obj.Saturday;
        };

        var txt='';
        if(plans!=undefined){
        	$.each(plans, function(i, item) {
                txt+='\
                <tr onclick="getLine(this);">\
	                <td style="display:none;">'+item.id+'</td>\
	                <td style="display:none;">'+item.path+'</td>\
	                <td class="point-line"><img src="'+item.path+'"></td>\
	                <td class="point-line">'+item.owingTo+'</td>\
	                <td class="point-line">'+item.price+'</td>\
	                <td class="point-line">'+item.stockNum+'</td>\
				</tr>\
				'
        	});
        }
        // $("#table_body").html(txt);
	});

	

}



function getLine (tr) {
	cells=tr.cells;
}

function changeAction(action){
	$("#action").val(action);
	$('#picture').uploadify('settings','buttonText','选择图片');

	if(action=="update"){
		// 检测是否选中行
		if (typeof(cells) == "undefined") {
		   alert("请选择一行");
		   $("#update").attr('href','#');
		   return;
		} else {
			// 调整模态框
			$("#update").attr('href','#dessertModel');
			$("#title").text("修改商品信息");

			var dessertId = cells[0].innerHTML;
			dessertId = dessertId.replace(/[\r\n]/g,"");//去掉回车换行
		    var picName = cells[1].innerHTML;
		    var name = cells[3].innerHTML;
		    var price = cells[4].innerHTML;
		    var stockNum = cells[5].innerHTML;

			//给模态框的输入框赋值
			$("#dessertId").val(dessertId);
			$("#originalPicName").val(picName);
			$('#picture').uploadify('settings','buttonText',picName);
			$("#name").val(name);
			$("#price").val(price);
			$("#stockNum").val(stockNum);

			// 设置不可以被修改
			$("#name").attr("disabled",true);
		}
	} else if(action=="add"){
		// 调整模态框
		$("#title").text("添加商品");
		// 设置可以被输入
		$("#picture").attr("disabled",false);
		$("#name").attr("disabled",false);

		$("#dessertId").val("-1");
		$("#picName").val("");
		$("#picture").val("");
		$("#name").val("");
		$("#price").val("");
		$("#stockNum").val("");
	}
}

function process(){
	var action = $("#action").val();
	var ajax_url='';
	if(action=="add"){
		ajax_url='api/plan/add';
	}else if(action=="update"){
		ajax_url='api/plan/edit';
	}else{
		alert("ajax错误");
	}
	var dessertId = $("#dessertId").val();
	var picName = $("#picName").val();
	var name = $("#name").val();
	var price = $("#price").val();
	var stockNum = $("#stockNum").val();
	var owingTo = $("#owingTo").val();
	var day = $("input[name='week']:checked").val();

	if (dessertId==""){
		$("#process_result").html("店面id异常");
		return;
	}else if(owingTo=="null"){
		$("#process_result").html("所属店面名称异常");
		return;
	}else if (picName==""){
		$("#process_result").html("商品照片不能为空");
		return;
	}else if (name==""){
		$("#process_result").html("商品名字不能为空");
		return;
	}else if (price==""){
		$("#process_result").html("商品价格不能为空");
		return;
	}else if (stockNum==""){
		$("#process_result").html("商品可销售数量不能为空");
		return;
	}

	//传额外参数
	$("#picture").uploadify("settings", "formData", {
		'dessertId' : dessertId, 
		'picName' : picName, 
		'owingTo' : owingTo,
		'name' : name, 
		'price' : price, 
		'stockNum' : stockNum, 
		'day' : day
	});

	//先检测商品是否存在了，存在就不可以上传
	$.ajax({
		url : 'api/plan/checkExist',
		type : 'post',
		dataType : 'json',
		data : {
			name:name
		},
		success : function(result, textStatus) {
			if(result.message!=""){
				//商品名存在，不可以上传
				$("#process_result").html(result.message);
			}else{
				//商品名不存在，可以上传
				javascript:$('#picture').uploadify('upload', '*');
			}
			
		}
	});

	
	// var params = $("#dessertForm").serialize();
	// $.ajax({
	// 	url : ajax_url,
	// 	type : 'post',
	// 	dataType : 'json',
	// 	data : params,
	// 	success : function(result, textStatus) {
	// 		callback_func(result);
	// 	}
	// });
}

function deleteDessert () {
	// 检测是否选中行
	if (typeof(cells) == "undefined") {
	   alert("请选择一行");
	   return;
	} else {
		if(window.confirm('你确定要删除所选店面吗？')){
		    var dessertId = cells[0].innerHTML;
		    dessertId = dessertId.replace(/[\r\n]/g,"");//去掉回车换行

		    $.ajax({
		    	url : 'api/plan/delete',
		    	type : 'post',
		    	dataType : 'json',
		    	data : {
		    		dessertId:dessertId
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
			alert("success");
			// window.location.href = "#";
		} else {
			$("#process_result").html(message);
		}
	}
}

function submitPlan () {
	var shopName=$("#owingTo").val();
	var planId=$("#planId").val();
	$.ajax({
		url : 'api/plan/submitPlan',
		type : 'post',
		dataType : 'json',
		data : {
			planId:planId,
			shopName:shopName
		},
		success : function(result, textStatus) {
			if (result.length == 0) {
					alert("出现错误");
				} else {
					var message = result.message;
					var success = "success";
					if (message == success) {
						alert("submit plan success");
						// window.location.href = "#";
					} else {
						$("#process_result").html(message);
					}
				}
		}
	});
}

//输入商品售价——浮点数
function CheckInputIntFloat(oInput) { 
	if('' != oInput.value.replace(/\d{1,}\.{0,1}\d{0,}/,'')) {
		oInput.value = oInput.value.match(/\d{1,}\.{0,1}\d{0,}/) == null ? '' :oInput.value.match(/\d{1,}\.{0,1}\d{0,}/); 
	}
}

//输入库存数量
//onkeyup="value=value.replace(/[^\d]/g,'')"