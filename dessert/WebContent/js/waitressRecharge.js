function getRecharge() {
	var memberId = $("#memberId").val();
	if(memberId==""){
		$("#memberId").focus();
		return ;
	}
	var url="waitress_recharge?memberId="+memberId;
	window.location.href=url;
}
