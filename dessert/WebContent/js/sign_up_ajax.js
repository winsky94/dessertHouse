//用户注册
function verify_sign_up() {
	// 使用dom的方式获取文本框中的值
	// .value可以获取一个元素节点的value属性
	var userName = $("#memberName").val();
	var password = $("#password_input").val();
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
		url : 'api/signUp',
		type : 'post',
		dataType : 'json',
		data : {
			userName : userName,
			password : password,
			age : age,
			sex : sex,
			birthday : birthday,
			telephone : telephone,
			cardId : cardId
		},
		success : function(result, textStatus) {
			callback_signup(result);
		}
	});
}
// 回调函数
function callback_signup(result) {
	if (result.length == 0) {
		alert("出现错误");
	} else {
		var message = result.message;
		if (message == "success") {
			add_cookie('userName',userName,30*24);
			alert("成功啦");
//			window.location.href = "index.html";
		} else {
			var divNode = $("#result");
			divNode.innerHTML = message;
		}
	}
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