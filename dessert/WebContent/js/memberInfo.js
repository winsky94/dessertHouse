function writeMemberShowInfo () {
	var name = get_cookie('userName');
	$.ajax({
		url : 'api/member/memberInfo',
		type : 'post',
		dataType : 'json',
		data : {
			name : name
		},
		success : function(result, textStatus) {
			if(result.member!=null){
				var member = result.member;
				var name=member.name;
				var rank=member.rank;
				var point=member.point;
				var lastLoadTime=get_cookie("lastLoadTime");
				var id = member.memberId;
				
				var txt='\
					<ul class="collection">\
						<li class="collection-item"><img src="image/index1.jpg" width="180px" height="150px">\
							<h5 class="text-center">欢迎您~~<span id="memberId">'+id+'</span></h5>\
						</li>\
						<li class="collection-item">\
							<p>\
								会员名：'+name+'\
							</p>\
						</li>\
						<li class="collection-item">\
							<p>\
								当前等级：'+rank+'\
							</p>\
						</li>\
						<li class="collection-item">\
							<p>\
								消费积分：'+point+'\
							</p>\
						</li>\
						<li class="collection-item">\
							<p>\
								上次登录时间：<br> &nbsp;&nbsp;&nbsp;&nbsp; '+lastLoadTime+'\
							</p>\
						</li>\
					</ul>\
				';
			}
			$("#memberInfo").html(txt);
			
			//给导航栏上的消费记录增加参数
			var hrefObj=document.getElementById("consume_href");
			var hrefs=hrefObj.getAttribute("href")+"?memberId="+id;
			hrefObj.setAttribute("href",hrefs);
			//给导航栏上的充值记录增加参数
			var rechargeHrefObj=document.getElementById("recharge_href");
			if(typeof(rechargeHrefObj)!=undefined && rechargeHrefObj!=null){
				var hrefs=rechargeHrefObj.getAttribute("href")+"?memberId="+get_cookie("memberId");
				rechargeHrefObj.setAttribute("href",hrefs);
			}
		}
	});
}