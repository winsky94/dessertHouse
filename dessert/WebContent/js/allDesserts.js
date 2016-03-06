//ajax取当天的商品内容
$.ajax({
	url : 'api/dessert/allDesserts',
	type : 'post',
	dataType : 'json',
	data : {
	},
	success : function(result, textStatus) {
		var data=result.desserts;
		var txt='';
		var cParent = document.getElementById("container");
            cParent.innerHTML="";
		for(var i=0;i<data.length;i++){
			var dessert=data[i];
			var id=dessert.id;
			var path=dessert.path;
			var name=dessert.name;
			var price=dessert.price;
			var stockNum=dessert.stockNum;
			var shopName=dessert.owingTo;

            //创建图片节点
            var cContent = document.createElement("div");
            cContent.className = "box";
            cParent.appendChild(cContent);

            var boxImg = document.createElement("div");
            boxImg.className = "box_img";
            cContent.appendChild(boxImg);
            
            var ah=document.createElement("a");
            ah.href="dessert_detail?shopName="+shopName+"&id="+id+"&type="+get_cookie("type");
            boxImg.appendChild(ah);

            var img = document.createElement("img");
            img.src = path;
            ah.appendChild(img);
            
            var nameDiv = document.createElement("div");
            nameDiv.className = "text_center";
            ah.appendChild(nameDiv);
            
            var nameSpan = document.createElement("span");
            nameSpan.innerHTML=name;
            nameDiv.appendChild(nameSpan);

            //
            var divText=document.createElement("div");
            divText.className="text";
            boxImg.appendChild(divText);

            var priceSpan = document.createElement("span");
            priceSpan.style="color:red;";
            priceSpan.innerHTML="￥"+price;
            divText.appendChild(priceSpan);

            var stockSpan = document.createElement("span");
            stockSpan.style="float:right;color:rgb(163,167,176);";
            stockSpan.innerHTML="库存："+stockNum+"个";
            divText.appendChild(stockSpan);

//			txt+='\
//				<div class="box">\
//					<input id="shopName" type="hidden" value="'+shopName+'">\
//					<div class="box_img">\
//						<a href="dessert_detail?shopName='+shopName+'&id='+id+'" target="_self">\
//							<img src="'+path+'">\
//							<div class="text_center">\
//								<span>'+name+'</span>\
//							</div>\
//						</a>\
//						<div class="text" >\
//							<span style="color: red">￥'+price+'</span>\
//							<span style="float:right;color:rgb(163,167,176)">库存：'+stockNum+'个</span>\
//						</div>\
//					</div>\
//				</div>\
//			';
		}
		imgLocation("container", "box");
		//重新设置商品内容
		if(data.length==0){
			txt='\
			<p class="text-center muted" style="padding-top:5%;font-size:16px;">暂无可售商品~~~敬请期待...</p>\
			';
			$("#container").html(txt);
		}
	
	}
});
