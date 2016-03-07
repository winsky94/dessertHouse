<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
	.year-month-selector{
		display: inline;
		margin-left: 50px;
	}
</style>
<h5 style="display: inline;">请选择要统计的月份：</h5>
<select class="browser-default input-small year-month-selector" id="year" onchange="refresh();">
</select>
<select class="browser-default input-small year-month-selector" id="month" onchange="refresh();">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3" selected>3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
</select>
<script type="text/javascript">
$(document).ready(function(){
    var year=new Date().getFullYear();
    $("#year").append("<option value="+year+" selected>"+year+"</option><option value="+(year-1)+">"+(year-1)+
    		"</option><option value="+(year-2)+">"+(year-2)+"</option><option value="+(year-3)+">"+(year-3)+
    		"</option><option value="+(year-4)+">"+(year-4)+"</option><option value="+(year-5)+">"+(year-5)+
    		"</option><option value="+(year-6)+">"+(year-6)+"</option><option value="+(year-7)+">"+(year-7)+
    		"</option>");
})
</script>
