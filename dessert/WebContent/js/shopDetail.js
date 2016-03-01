//得到url的参数
function getQueryString(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
}

function refreshSelectedDay () {
	//选中周几
	var selectedDay=$("input[name='week']:checked").val();
	//选中的星期是哪一天
	var date=$("#"+selectedDay).val();
	var shopName=getQueryString("shopName");

	//ajax取当天的商品内容
	$.ajax({
		url : 'api/plan/dayDessert',
		type : 'post',
		dataType : 'json',
		data : {
			weekDay:selectedDay,
			date:date,
			shopName:shopName
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
				var owingTo=dessert.owingTo;

				txt+='\
					<div class="box">\
						<input id="shopName" type="hidden" value="'+owingTo+'">\
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

	
	

}

function setWeekBar () {
	$.ajax({
		url : 'api/shop/getSevenDay',
		type : 'post',
		dataType : 'json',
		data : {
		},
		success : function(result, textStatus) {
			var txt='';

			var json=result.weeks;
			$.each(json, function(key, value){
		        //alert(key+":"+value);
		        txt+=getDayRadio(key,value);
		     })
			$("#weekBar").html(txt);

			//默认选中第一天
			// var day=getQueryString("day");
			// if(day!=null&&day.toString().length>1){
				//传过来的参数不为空
				//为指定name的radio设置选中值
				var weeks=document.getElementsByName("week");
				for(var i=0;i<weeks.length;i++){
					// if (weeks[i].value==day) {
					if(i==0) {
						weeks[i].checked=true;
					}else{
						weeks[i].checked=false;
					}
				}
			// }
			refreshSelectedDay();
		}
	});
}


function getDayRadio (date,weekDay) {
	var t='\
		<label class="radio inline" style="margin-left:5%">\
			<input type="hidden" value="'+date+'" id="'+weekDay+'">\
			<input type="radio" name="week" value="'+weekDay+'" onclick="refreshSelectedDay();">\
	';
	if(weekDay=="Sunday"){
		t+='\
			周日\
		';
	}else if(weekDay=="Monday"){
		t+='\
			周一\
		';
	}else if(weekDay=="Tuesday"){
		t+='\
			周二\
		';
	}else if(weekDay=="Wednesday"){
		t+='\
			周三\
		';
	}else if(weekDay=="Thursday"){
		t+='\
			周四\
		';
	}else if(weekDay=="Friday"){
		t+='\
			周五\
		';
	}else if(weekDay=="Saturday"){
		t+='\
			周六\
		';
	}
	t+='\
		</label>\
	';

	return t;
}

