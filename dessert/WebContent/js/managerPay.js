function refresh() {
	var table = $('#payTable').DataTable();
	table.ajax.reload();
}