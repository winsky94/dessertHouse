function getMemberInfo() {
	name = get_cookie('userName');
	$.ajax({
		url : 'api/member/memberInfo',
		type : 'post',
		dataType : 'json',
		data : {
			name : name
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
				
				if(validDate=="null"||validDate==null){
					validDate="-";
				}

				showInfo();
			}
		}
	});
}

function cancelMember() {
	if(window.confirm('你确定要取消会员资格吗？')){
		$.ajax({
			url : 'api/member/cancelMember',
			type : 'post',
			dataType : 'json',
			data : {
				memberId : memberId
			},
			success : function(result, textStatus) {
				if (result.length == 0) {
					alert("出现错误");
				} else {
					var message = result.message;
					if (message == "success") {
						exit();
					} else {
						var divNode = document.getElementById("pay_message");
						divNode.innerHTML = message;
					}
				}
			}
		});
    }else{
    	//取消就是啥都不干
    }
}

function charge() {
	$("#pay").toggle();
}

function pay () {
	var pay_money=$("#money").val();
	var confirm_money=$("#money_confirm").val();
	if(pay_money!=confirm_money){
		var divNode = document.getElementById("pay_message");
		$("#money_confirm").focus();
		divNode.innerHTML = "两次充值金额输入不一致";
		return;
	}

	$.ajax({
		url : 'api/member/pay',
		type : 'post',
		dataType : 'json',
		data : {
			memberId : memberId,
			pay_money : pay_money
		},
		success : function(result, textStatus) {
			if (result.length == 0) {
				alert("出现错误");
			} else {
				var message = result.message;
				if (message == "success") {
					window.location.href = "member_info";
				} else {
					var divNode = document.getElementById("pay_message");
					divNode.innerHTML = message;
				}
			}
		}
	});
}

function update() {
	var id = $("#id").val();
	var userName = $("#memberName").val();
	var password = $("#password").val();
	var confirm_password = $("#confirm_password").val();
	var age = $("#age").val();
	var sex = $("#sex").val();
	var birthday = $("#birthday").val();
	var telephone = $("#telephone").val();
	var cardId = $("#cardId").val();

	if (userName == "") {
		var divNode = document.getElementById("message");
		$("#memberName").focus();
		divNode.innerHTML = "用户名不能为空";
		return;
	}
	if (age == "") {
		var divNode = document.getElementById("message");
		$("#age").focus();
		divNode.innerHTML = "年龄不能为空";
		return;
	}
	if (password == "") {
		var divNode = document.getElementById("message");
		$("#password").focus();
		divNode.innerHTML = "密码不能为空";
		return;
	}
	if (confirm_password != password) {
		var divNode = document.getElementById("message");
		$("#confirm_password").focus();
		divNode.innerHTML = "两次密码输入不一致";
		return;
	}
	if (sex == null) {
		var divNode = document.getElementById("message");
		$("#sex").focus();
		divNode.innerHTML = "请选择性别";
		return;
	}
	if (birthday == "") {
		var divNode = document.getElementById("message");
		$("#birthday").focus();
		divNode.innerHTML = "生日不能为空";
		return;
	}
	if (!checkDate(birthday)) {
		var divNode = document.getElementById("message");
		$("#birthday").focus();
		divNode.innerHTML = "生日要在今天之前";
		return;
	}
	if (telephone == "") {
		var divNode = document.getElementById("message");
		$("#telephone").focus();
		divNode.innerHTML = "电话不能为空";
		return;
	}
	if (!checkMobile(telephone)) {
		var divNode = document.getElementById("message");
		$("#telephone").focus();
		divNode.innerHTML = "手机号不对";
		return;
	}
	if (cardId == "") {
		var divNode = document.getElementById("message");
		$("#cardId").focus();
		divNode.innerHTML = "银行卡号不能为空";
		return;
	}

	$.ajax({
		url : 'api/member/update',
		type : 'post',
		dataType : 'json',
		data : {
			id : id,
			userName : userName,
			password : password,
			age : age,
			sex : sex,
			birthday : birthday,
			telephone : telephone,
			cardId : cardId,
			lastLoadTime : lastLoadTime,
			memberId : memberId,
			status : status,
			point : point,
			rank : rank,
			validMoney : validMoney,
			validDate : validDate//注意这儿可能传递的是-，后面判断一下
		},
		success : function(result, textStatus) {
			callback_update(result);
		}
	});
}

// 回调函数
function callback_update(result) {
	if (result.length == 0) {
		alert("出现错误");
	} else {
		var message = result.message;
		if (message == "success") {
			window.location.href = "member_info";
		} else {
			var divNode = document.getElementById("message");
			divNode.innerHTML = message;
		}
	}
}

function editInfo() {
	$("#pay").hide();
	var txt = '\
		<form class="form-horizontal">\
			<div class="row-fluid control-group"></div>\
			<input type="hidden" id="id" value="'+id+'">\
			<div class="row-fluid control-group">\
				<div class="span4">\
					<label class="control-label" for="memberName">用户名：</label>\
					<div class="controls">\
						<input type="text" id="memberName" placeholder="用户名" value="'+name+'" disabled>\
					</div>\
				</div>\
				<div class="span4">\
					<label class="control-label" for="age">年　龄：</label>\
					<div class="controls">\
						<input type="number" id="age" placeholder="年　龄" min="0" max="100" value="'+age+'">\
					</div>\
				</div>\
			</div>\
			<div class="row-fluid control-group"></div>\
			<div class="row-fluid control-group">\
				<div class="span4">\
					<label class="control-label" for="password">密　码：</label>\
					<div class="controls">\
						<input type="password" id="password" placeholder="密　码" value="'+password+'">\
					</div>\
				</div>\
				<div class="span4">\
					<label class="control-label" for="confirm_password">确认密码：</label>\
					<div class="controls">\
						<input type="password" id="confirm_password" placeholder="确认密码" value="'+password+'">\
					</div>\
				</div>\
			</div>\
			<div class="row-fluid control-group"></div>\
			<div class="row-fluid control-group">\
				<div class="span4">\
					<label class="control-label" for="age">性　别：</label>\
					<div class="controls">\
						<select class="browser-default" id="sex">\
							<option value="" disabled>请选择性别</option>\
							';
					if(sex=="男"){
						txt+='\
							<option value="男" selected>男</option>\
							<option value="女">女</option>\
							<option value="保密">保密</option>\
						';
					}else if(sex=="女"){
						txt+='\
							<option value="男">男</option>\
							<option value="女" selected>女</option>\
							<option value="保密">保密</option>\
						';
					}else{
						txt+='\
							<option value="男">男</option>\
							<option value="女">女</option>\
							<option value="保密" selected>保密</option>\
						';
					}
						txt+='\
						</select>\
					</div>\
				</div>\
				<div class="span4">\
					<label class="control-label" for="birthday">生　日：</label>\
					<div class="controls">\
						<input id="birthday" class="Wdate" type="text" onClick="WdatePicker();" placeholder="生　日" value="'+birthday+'">\
					</div>\
				</div>\
			</div>\
			<div class="row-fluid control-group"></div>\
			<div class="row-fluid control-group">\
				<div class="span4">\
					<label class="control-label" for="telephone">电　话：</label>\
					<div class="controls">\
						<input type="tel" id="telephone" placeholder="电　话" value="'+telephone+'">\
					</div>\
				</div>\
				<div class="span4">\
					<label class="control-label" for="cardId">银行卡号：</label>\
					<div class="controls">\
						<input type="text" id="cardId" placeholder="银行卡号" onkeyup="formatBankNo(this)" onkeydown="formatBankNo(this)" value="'+cardId+'">\
					</div>\
				</div>\
			</div>\
			<div class="row-fluid control-group"></div>\
			<div class="row-fluid control-group">\
				<div class="span4">\
					<div class="controls">\
						<button type="button" class="btn" onclick="update();">\
							确认\
						</button>\
						<button type="button" style="display:inline;margin-left: 5px;" class="btn center" onclick="showInfo();">\
							取消\
						</button>\
					</div>\
				</div>\
				<div class="span4">\
					<div class="controls">\
						<font color="red" size="2" style="display:inline;">\
							<span id="message"></span>\
						</font>\
					</div>\
				</div>\
			</div>\
		</form>\
	';
	$("#info").html('');
	$("#edit_info").html(txt);
}

function showInfo() {
	var txt = '\
			<!-- 会员左侧信息 -->\
			<div class="span4">\
				<div class="table-row" style="padding-top: 10px;"> \
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
			</div>\
			<!-- 会员左侧信息 结束 -->\
			<!-- 会员右侧信息 -->\
			<div class="span4">\
				<div class="table-row" style="padding-top: 10px;"> \
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
					<div class="property">有效期：</div> \
					<div class="value" id="validDate">'
			+ validDate
			+ '</div> \
				</div> \
			</div>\
			<!-- 会员右侧信息 结束 -->\
	';
	$("#edit_info").html('');
	$("#info").html(txt);
}

// 检测手机号
function checkMobile(mobile) {
	if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile))) {
		return false;
	} else {
		return true;
	}
}

// 银行卡的输入检测及格式化
function formatBankNo(BankNo) {
	if (BankNo.value == "")
		return;
	var account = new String(BankNo.value);
	account = account.substring(0, 22); /* 帐号的总数, 包括空格在内 */
	if (account.match(".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null) {
		/* 对照格式 */
		if (account.match(".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|"
				+ ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|"
				+ ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|"
				+ ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null) {
			var accountNumeric = accountChar = "", i;
			for (i = 0; i < account.length; i++) {
				accountChar = account.substr(i, 1);
				if (!isNaN(accountChar) && (accountChar != " "))
					accountNumeric = accountNumeric + accountChar;
			}
			account = "";
			for (i = 0; i < accountNumeric.length; i++) { /* 可将以下空格改为-,效果也不错 */
				if (i == 4)
					account = account + " "; /* 帐号第四位数后加空格 */
				if (i == 8)
					account = account + " "; /* 帐号第八位数后加空格 */
				if (i == 12)
					account = account + " ";/* 帐号第十二位后数后加空格 */
				account = account + accountNumeric.substr(i, 1)
			}
		}
	} else {
		account = " " + account.substring(1, 5) + " "
				+ account.substring(6, 10) + " " + account.substring(14, 18)
				+ "-" + account.substring(18, 25);
	}
	if (account != BankNo.value) {
		BankNo.value = account;
	}
}

function checkDate(pdate) {
	var d = new Date;
	var today = new Date(d.getFullYear(), d.getMonth(), d.getDate());
	var reg = /\d+/g;
	var temp = pdate.match(reg);
	var foday = new Date(temp[0], parseInt(temp[1]) - 1, temp[2]);
	if (foday < today) {
		return true;
	} else {
		return false;
	}
}