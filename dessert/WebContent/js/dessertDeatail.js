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

function buy () {
	alert("buy");
}

function appointment () {
	alert("appointment");
}

function cart () {
	alert("cart");
}