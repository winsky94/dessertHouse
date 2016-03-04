function getConsume() {
	var memberId = $("#memberId").val();
	if(memberId==""){
		$("#memberId").focus();
		return ;
	}
	var url="waitress_consume?memberId="+memberId;
	window.location.href=url;
}
