function getShop(shopName) {
	$.ajax({
		url : 'api/shop/getShop',
		type : 'post',
		dataType : 'json',
		data : {
			shopName : shopName,
		},
		success : function(result, textStatus) {
			if(result.shopVO!=null){
				var vo = result.shopVO;
				var shopName=vo.name;
				var address=vo.address;
				var owner=vo.owner;
				var telephone=vo.telephone;
				var id = vo.id;
				
				var txt='\
					<input type="hidden" id="shopId" value="'+id+'">\
					<ul class="collection">\
						<li class="collection-item"><img src="image/shop.jpg"\
							width="180px" height="150px">\
							<h5 id="shopName" class="text-center">'+shopName+'</h5></li>\
							<li class="collection-item">\
								<p>\
									<strong>店名：</strong>'+shopName+'\
								</p>\
							</li>\
							<li class="collection-item">\
								<p>\
									<strong>负责人：</strong>'+owner+'\
								</p>\
							</li>\
							<li class="collection-item">\
								<p>\
									<strong>联系电话：</strong>'+telephone+'\
								</p>\
							</li>\
							<li class="collection-item">\
								<p>\
									<strong>地址：</strong>'+address+'\
								</p>\
							</li>\
					</ul>\
				';
			}
			$("#shopInfo").html(txt);
		}
	});
}
