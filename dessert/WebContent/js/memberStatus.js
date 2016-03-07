require.config({
	paths : {
		echarts : 'js/eChart-2.2.7/build/dist'
	}
});

// 使用
require([ 'echarts', 'echarts/chart/pie',// 饼状图
'echarts/chart/funnel', // 漏斗图
'echarts/chart/map' // 地图
], function(ec) {
	// 基于准备好的dom，初始化echarts图表
	baseData(ec);
});

function baseData(ec) {
	if (ec == "") {
		ec = require('echarts');
	}
	var year = $("#year").val();
	var month = $("#month").val();

	$.ajax({
		url : 'api/manager/status',
		type : 'post',
		dataType : 'json',
		data : {
			year : year,
			month : month
		},
		success : function(result, textStatus) {
			var statusInfo = result.statusInfo;

			var statusData = [];
			var maxAge = 0;

			// 拼装status====================================================================================
			var n1 = ageInfo["20以下"];
			if (n1 > maxAge) {
				maxAge = n1;
			}
			var arr1 = {
				value : n1,
				name : '20以下'
			};
			ageData.push(arr1);

			var n2 = ageInfo["20-30"];
			if (n2 > maxAge) {
				maxAge = n2;
			}
			var arr2 = {
				value : n2,
				name : '20-30'
			};
			ageData.push(arr2);

			var n3 = ageInfo["30-40"];
			if (n3 > maxAge) {
				maxAge = n3;
			}
			var arr3 = {
				value : n3,
				name : '30-40'
			};
			ageData.push(arr3);

			var n4 = ageInfo["40-50"];
			if (n4 > maxAge) {
				maxAge = n4;
			}
			var arr4 = {
				value : n4,
				name : '40-50'
			};
			ageData.push(arr4);

			var n5 = ageInfo["50-60"];
			if (n5 > maxAge) {
				maxAge = n5;
			}
			var arr5 = {
				value : n4,
				name : '50-60'
			};
			ageData.push(arr5);

			var n6 = ageInfo["60以上"];
			if (n6 > maxAge) {
				maxAge = n6;
			}
			var arr6 = {
				value : n6,
				name : '60以上'
			};
			ageData.push(arr6);

			statusChart(ec, ageData, maxAge, year, month);
		}
	});
}

function statusChart(ec, validNum, pauseNum, overNum, cancelNum,monthData) {
	var myStatusChart = ec.init(document.getElementById('status'));
	var option = {
		title : {
			text : '会员数量变化趋势',
		// subtext: '纯属虚构'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '有效会员','暂停会员', '停止会员', '取消会员' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : monthData
		} ],
		yAxis : [ {
			type : 'value',
			axisLabel : {
				formatter : '{value} '
			}
		} ],
		series : [ {
			name : '有效会员',
			type : 'line',
			data : validNum,
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
		// markLine: {
		// data: [
		// {type: 'average', name: '平均值'}
		// ]
		// }
		}, {
			name : '暂停会员',
			type : 'line',
			data : pauseNum,
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
		// markLine: {
		// data: [
		// {type: 'average', name: '平均值'}
		// ]
		// }
		}, {
			name : '停止会员',
			type : 'line',
			data : overNum,
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
//			markLine : {
//				data : [ {
//					type : 'average',
//					name : '平均值'
//				} ]
//			}
		},{
			name : '取消会员',
			type : 'line',
			data : cancelNum,
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
//			markLine : {
//				data : [ {
//					type : 'average',
//					name : '平均值'
//				} ]
//			}
		}

		]
	};
	// 为echarts对象加载数据
	myStatusChart.setOption(option);
}

function refresh() {
	// 刷新图表，传入空，basedata中判断如果是空就ec = require('echarts');
	baseData("");
}