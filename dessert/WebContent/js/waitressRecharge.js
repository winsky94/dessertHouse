function getRecharge() {
	var memberId = $("#memberId").val();
	var url="waitress_recharge?memberId="+memberId;
	window.location.href=url;
}
