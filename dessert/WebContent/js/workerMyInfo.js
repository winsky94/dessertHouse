function changePW() {
	var workerId = get_cookie("userName");
	var originalPW = $("#oldPassword").val();
	var newPW = $("#newPassword").val();
	var confirmPW = $("#confirm_password").val();

	if (newPW != confirmPW) {
		var divNode = document.getElementById("message");
		$("#confirm_password").focus();
		divNode.innerHTML = "两次密码输入不一致";
		return;
	}
	$.ajax({
		url : 'api/worker/changePW',
		type : 'post',
		dataType : 'json',
		data : {
			originalPW : originalPW,
			newPW : newPW,
			workerId : workerId
		},
		success : function(result, textStatus) {
			callback_changePW(result);
		}
	});
}

function callback_changePW(result) {
	var message = result.message;
	if (message == "success") {
		alert("恭喜您，密码修改成功，请使用新密码重新登录~~~")
		exit();
	} else {
		var divNode = document.getElementById("message");
		$("#oldPassword").focus();
		divNode.innerHTML = message;
	}
}