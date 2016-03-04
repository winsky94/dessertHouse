function getLine (tr) {
	cells=tr.cells;
}

function cancelOrder (id) {
	if(window.confirm('取消订单将产生2%的手续费（最低2元），确认继续吗？')){
		$.ajax({
			url : 'api/member/cancelOrder',
			type : 'post',
			dataType : 'json',
			data : {
				id : id
			},
			success : function(result, textStatus) {
				var message = result.message;
				if(message=="success"){
					window.location.reload();
				}
			}
		});
	}
}