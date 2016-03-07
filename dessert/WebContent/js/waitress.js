//得到url的参数
function getQueryString(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
}

function confirm(){
	var memberId = $("#memberId").val();
	var dessertId = $("#dessertId").html();
	var num = $("#num").html();
	var price = $("#price").html();
	var money = $("#money").html();
	var payMethod = $("input[type='radio']:checked").val();
	var actionP = getQueryString("action");

	if(actionP=="waitress_buy"){
		var action="buy";
	}else if(actionP=="waitress_appointment"){
		var action="appointment";
	}
	
	if(payMethod=="cash"){
		var pay=$("#payMoney").val();
		if(pay==""){
			alert("请输入付款金额");
			return;
		}else{
			var real=$("#money").html();
			
			var change=parseFloat(pay)-parseFloat(real);
			if(change>=0){
			}else{
				alert("付款额不足以支付商品费用");
				return;
			}
		}
		
	}

	if(memberId==""){
		alert("请选择购物会员");
		return ;
	}

	$.ajax({
		url : 'api/dessert/consume',
		type : 'post',
		dataType : 'json',
		data : {
			memberId : memberId,
			dessertId : dessertId,
			num : num,
			price : price,
			action : action,
			payMethod : payMethod//这个是后面controller会转换的
		},
		success : function(result, textStatus) {
			if (result.length == 0) {
				alert("出现错误");
			} else {
				var message = result.message;
				if (message == "success") {
					var discount = result.discount;
					var point = result.point;
					var txt='\
						<!-- Modal -->\
						<div id="consumeResultModel" class="modal hide fade" tabindex="-1" role="dialog"  aria-hidden="true">\
							<div class="modal-header">\
								<h3>消费结果提示</h3>\
							</div>\
							<div class="modal-body">\
								<div class="center">\
									恭喜您，您此次消费：\
									<br><br>&nbsp;&nbsp;&nbsp;&nbsp;获得折扣：'+discount+'元\
									<br><br>&nbsp;&nbsp;&nbsp;&nbsp;获得积分：'+point+'分\
								</div>\
							</div>\
							<div class="modal-footer">\
								<div style="text-align:center">\
									<button class="btn btn-primary" onclick="messageOK()">确认</button>\
								</div>\
						\
							</div>\
						</div>\
					';
					$("#consume_result").html(txt);
					$("#consumeResultModel").modal('show');
				} else {
					alert(message);
				}
			}
			
			
		}
	});
}

function cancel(){
	messageOK();
}


function getMemberInfo() {
	memberId=$("#memberId").val();
	if(memberId==""){
		return;
	}
	$.ajax({
		url : 'api/waitress/guestInfo',
		type : 'post',
		dataType : 'json',
		data : {
			memberId : memberId
		},
		success : function(result, textStatus) {
			if (result.member != null) {
				member = result.member;
				name = member.name;
				rank = member.rank;
				point = member.point;
				lastLoadTime = member.lastLoadTime;
				memberId = member.memberId;
				birthday = member.birthday;
				cardId = member.cardId;
				password = member.password;
				sex = member.sex;
				status = member.status;
				telephone = member.telephone;
				age = member.age;
				validMoney = member.validMoney;
				validDate = member.validDate;
				id = member.id;
				prov = member.prov;
				city = member.city;
				dist = member.dist;
				createAt = member.createAt;
				
				if(validDate=="null"||validDate==null){
					validDate="-";
				}

				showInfo();
			}else{
				alert("您查找的会员不存在，请核对会员号");
				$("#memberId").focus();
			}
		}
	});
}

function showInfo() {
	var txt = '\
			<!-- 会员左侧信息 -->\
			<div class="span4">\
				<div class=""> \
					<div class="property">等　级：</div> \
					<div class="value" id="rank">'
			+ rank
			+ '</div> \
				</div>\
				<div class="table-row"> \
					<div class="property">昵　称：</div> \
					<div class="value" id="memberName">'
			+ name
			+ '</div> \
				</div>\
				<div class="table-row"> \
					<div class="property">性　别：</div> \
					<div class="value" id="sex">'
			+ sex
			+ '</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">电　话：</div> \
					<div class="value" id="telephone">'
			+ telephone
			+ '</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">余　额：</div> \
					<div class="value" id="validMoney">'
			+ validMoney.toFixed(2)
			+ '元</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">有效期：</div> \
					<div class="value" id="validDate">'
			+ validDate
			+ '</div> \
				</div> \
			</div>\
			<!-- 会员左侧信息 结束 -->\
			<!-- 会员右侧信息 -->\
			<div class="span4">\
				<div class=""> \
					<div class="property">积　分：</div> \
					<div class="value" id="point">'
			+ point
			+ '</div> \
				</div>\
				<div class="table-row"> \
					<div class="property">年　龄：</div> \
					<div class="value" id="age">'
			+ age
			+ '</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">生　日：</div> \
					<div class="value" id="birthday">'
			+ birthday
			+ '</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">银行卡号：</div> \
					<div class="value" id="cardId">'
			+ cardId
			+ '</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">家庭住址：</div> \
					<div class="value" id="address">'
			+ prov+'-'+city+'-'+dist
			+ '</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">创建日期：</div> \
					<div class="value" id="createAt">'
			+ createAt
			+ '</div> \
				</div> \
			</div>\
			<!-- 会员右侧信息 结束 -->\
	';
	$("#info").html(txt);
	$("#guestWell").show();
}

function messageOK () {
	var url = 'shopDetail.jsp?shopName='+get_cookie("owingTo");
	window.location.href=url;
}

function showCash(select){
	if(select){
		$("#cashWall").show();
	}else{
		$("#cashWall").hide();
	}
}

function calChange(){
	var pay=$("#payMoney").val();
	var real=$("#money").html();
	
	
	var change=parseFloat(pay)-parseFloat(real);
	if(change>=0){
		$("#change").val(change);
	}else{
		if(pay!=""){
			alert("付款额不足以支付商品费用");
		}
	}
	
}
