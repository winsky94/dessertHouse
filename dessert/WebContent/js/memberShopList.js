function getLine (tr) {
	cells=tr.cells;
	var name = cells[1].innerHTML;
	var url="shopDetail.jsp?shopName="+name;
	window.location.href=url;
}

