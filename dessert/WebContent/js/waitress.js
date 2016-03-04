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
			+ validMoney
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