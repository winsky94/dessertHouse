function del(index){
	var dessertId = $("#dessertId"+index).val();

	$.ajax({
		url : 'api/member/myCart',
		type : 'post',
		dataType : 'json',
		data : {
			dessertId : dessertId,
			action : 'del'
		},
		success : function(result, textStatus) {
			if (result.length == 0) {
				alert("出现错误");
			} else {
				var message = result.message;
				if (message == "success") {
					window.location.reload();
				} else {
					alert(message);
				}
			}
		}
	});
}

function buy(index,many,action){
	var dessertId = $("#dessertId"+index).val();
	var num=$("#quantity"+index).val();
	var memberName=get_cookie("userName");
	
	$.ajax({
		url : 'api/member/myCart',
		type : 'post',
		dataType : 'json',
		data : {
			dessertId : dessertId,
			num: num,
			memberName:memberName,
			action : action
		},
		success : function(result, textStatus) {
			if (result.length == 0) {
				alert("出现错误");
			} else {
				var message = result.message;
				if (message == "success") {
					if(many!="many"){
						var discount = result.discount;
						var point = result.point;
						var txt = '\
					<!-- Modal -->\
					<div id="consumeResultModel" class="modal hide fade" tabindex="-1" role="dialog"  aria-hidden="true">\
						<div class="modal-header">\
							<h3>消费结果提示</h3>\
						</div>\
						<div class="modal-body">\
							<div class="center">\
								恭喜您，您此次消费：\
								<br><br>&nbsp;&nbsp;&nbsp;&nbsp;获得折扣：'
								+ discount
								+ '\
								<br><br>&nbsp;&nbsp;&nbsp;&nbsp;获得积分：'
								+ point
								+ '\
							</div>\
						</div>\
						<div class="modal-footer">\
							<div style="text-align:center">\
								<button class="btn btn-primary" onclick="hide()">确认</button>\
							</div>\
					\
						</div>\
					</div>\
				';

						$("#consume_result").html(txt);
						$("#consumeResultModel").modal('show');
					}
				} else {
					alert(message);
				}
			}
		}
	});
}

function hide(){
	$("#consumeResultModel").modal('hide');
	window.location.reload();
}

function addNum (index) {
	var original=$("#quantity"+index).val();
	original=parseInt(original)+1;
	var price = $("#price"+index).html();
	var totalMoney= parseFloat(price)*parseInt(original);
	
	//判断如果是1，就设置-按钮可以按
	if(original>1){
		$("#delBtn"+index).removeAttr("disabled");
	}

	var stockNum=$("#stockNum"+index).text();
	if(original>stockNum){
		alert("购买输入大于库存数量");
		$("#quantity"+index).val(stockNum);
	}else{
		$("#quantity"+index).val(original);
		$("#totalMoney"+index).html(parseFloat(totalMoney));
	}
}

function delNum (index) {
	var original=$("#quantity"+index).val();
	original=parseInt(original)-1;
	var price = $("#price"+index).html();
	var totalMoney= parseFloat(price)*parseInt(original);

	//判断如果是1，就设置-按钮不可以按
	if(original==1){
		$("#delBtn"+index).attr("disabled", true);
	}

	$("#quantity"+index).val(original);
	$("#totalMoney"+index).html(parseFloat(totalMoney));
}

function addListener(index){
	$("#quantity"+index).keyup(function(){  
		var c=$(this);
	    if(/[^\d]/.test(c.val())){//替换非数字字符
	    	var temp_amount=c.val().replace(/[^\d]/g,'');
	    	$(this).val(temp_amount);
	    }
	})

	$("#quantity"+index).blur(function(){ 
		var quantity=$(this).val();
		var num=parseInt(quantity);
		var stockNum=parseInt($("#stockNum"+index).text());
		if(quantity>stockNum){
			alert("购买数量超过库存数量");
			$(this).val(stockNum);
		}

		if(quantity==""||quantity==0){
			$(this).val(1);
		}
		
		var original=$("#quantity"+index).val();
		var price = $("#price"+index).html();
		var totalMoney= parseFloat(price)*parseInt(original);
		$("#totalMoney"+index).html(parseFloat(totalMoney));
	})
}

function clearAll() {
	
}

function deleteSelected() {
	
}

function buySelected() {
	var es=getAllSelected();
	var arr=es[0];
	var actionArr=es[1];
	for(var i=0;i<arr.length;i++){
		buy(arr[i],'many',actionArr[i]);
	}
	alert("您已成功下单~~~");
	window.location.reload();
}

function getAllSelected(){
	var arr=new Array();
	var actionArr=new Array();
	
	var r=document.getElementsByName("checkbox");  
    for(var i=0;i<r.length;i++){
         if(r[i].checked){
        	 var a=r[i].value
        	 arr.push(a);
        	 actionArr.push($("#action"+a).val());
       }
    } 
    var result =new Array();
    result.push(arr);
    result.push(actionArr);
    
	return result;
}
