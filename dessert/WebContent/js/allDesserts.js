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
		for(var i=0;i<data.length;i++){
			var dessert=data[i];
			var id=dessert.id;
			var path=dessert.path;
			var name=dessert.name;
			var price=dessert.price;
			var stockNum=dessert.stockNum;
			var shopName=dessert.owingTo;

			txt+='\
				<div class="box">\
					<input id="shopName" type="hidden" value="'+shopName+'">\
					<div class="box_img">\
						<a href="dessert_detail?shopName='+shopName+'&id='+id+'" target="_self">\
							<img src="'+path+'">\
							<div class="text_center">\
								<span>'+name+'</span>\
							</div>\
						</a>\
						<div class="text" >\
							<span style="color: red">￥'+price+'</span>\
							<span style="float:right;color:rgb(163,167,176)">库存：'+stockNum+'个</span>\
						</div>\
					</div>\
				</div>\
			';
		}
		//重新设置商品内容
		if(data.length==0){
			txt='\
				<p class="text-center muted" style="padding-top:5%;font-size:16px;">暂无可售商品~~~敬请期待...</p>\
			';
		}
		$("#container").html(txt);
	}
});
