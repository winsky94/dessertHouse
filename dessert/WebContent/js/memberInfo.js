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
				var lastLoadTime=member.lastLoadTime;
				var id = member.memberId;
				
				var txt='\
					<ul class="collection">\
						<li class="collection-item"><img src="image/index1.jpg" width="180px" height="150px">\
							<h5 id="memberId" class="text-center">欢迎您~~'+id+'</h5>\
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
								最近登录时间：<br> &nbsp;&nbsp;&nbsp;&nbsp; '+lastLoadTime+'\
							</p>\
						</li>\
					</ul>\
				';
			}
			$("#memberInfo").html(txt);
		}
	});
}