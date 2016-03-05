<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
	.year-month-selector{
		display: inline;
		margin-left: 50px;
	}
</style>
<h5 style="display: inline;">请选择要统计的月份：</h5>
<select class="browser-default input-small year-month-selector" id="year" onchange="refresh();">
	<option value="2016" selected>2016</option>
	<option value="2015">2015</option>
	<option value="2014">2014</option>
	<option value="2013">2013</option>
	<option value="2012">2012</option>
	<option value="2011">2011</option>
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
