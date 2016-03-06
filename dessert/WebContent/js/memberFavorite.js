function refresh() {
	var table = $('#favoriteTable').DataTable();
	table.ajax.reload();
}