function buy () {
	alert("buy");
	process("buy","card");
}

function appointment () {
	process("appointment","card");
}

function process (action,payMethod) {
	var memberName=get_cookie("userName");
	if(memberName==null || typeof(memberName)==undefined){
		alert("请先登录再操作");
		window.location.href="index.html";
		return;
	}
	var dessertId=$("#dessertId").val();
	var num=$("#quantity").val();
	var price=$("#price").text();
	$.ajax({
		url : 'api/dessert/consume',
		type : 'post',
		dataType : 'json',
		data : {
			memberName : memberName,
			dessertId : dessertId,
			num : num,
			price : price,
			action : action,
			payMethod : payMethod
		},
		success : function(result, textStatus) {
			if (result.length == 0) {
				alert("出现错误");
			} else {
				var message = result.message;
				if (message == "success") {
					window.location.href="allDesserts.jsp"
				} else {
					alert(message);
				}
			}
		}
	});
}

function cart () {
	alert("cart");
}

function addNum () {
	var original=$("#quantity").val();
	original=parseInt(original)+1;
	
	//判断如果是1，就设置-按钮可以按
	if(original>1){
		$("#delBtn").removeAttr("disabled");
	}

	var stockNum=$("#stockNum").text();
	if(original>stockNum){
		alert("购买输入大于库存数量");
		$("#quantity").val(stockNum);
	}else{
		$("#quantity").val(original);
	}
}

function delNum () {
	var original=$("#quantity").val();
	original=parseInt(original)-1;

	//判断如果是1，就设置-按钮不可以按
	if(original==1){
		$("#delBtn").attr("disabled", true);
	}

	$("#quantity").val(original);
}
$("#quantity").keyup(function(){  
	var c=$(this);
    if(/[^\d]/.test(c.val())){//替换非数字字符
    	var temp_amount=c.val().replace(/[^\d]/g,'');
    	$(this).val(temp_amount);
    }
})

$("#quantity").blur(function(){ 
	var quantity=$(this).val();
	var num=parseInt(quantity);
	var stockNum=parseInt($("#stockNum").text());
	if(quantity>stockNum){
		alert("购买数量超过库存数量");
		$(this).val(stockNum);
	}

	if(quantity==""){
		$(this).val(1);
	}
})