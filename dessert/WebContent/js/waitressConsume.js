function getConsume() {
	var memberId = $("#memberId").val();
	var url="waitress_consume?memberId="+memberId;
	window.location.href=url;
}
