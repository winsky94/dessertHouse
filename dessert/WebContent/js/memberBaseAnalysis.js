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
	$.ajax({
		url : 'api/manager/baseData',
		type : 'post',
		dataType : 'json',
		data : {},
		success : function(result, textStatus) {
			var ageInfo = result.ageInfo;
			var sexInfo = result.sexInfo;
			var addressInfo = result.addressInfo;

			var ageData = [];
			var maxAge = 0;
			var sexData = [];
			var maxSex = 0;
			var addressData = [];
			var maxAddr = 0;

			// 拼装age================================================================================================
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

			ageChart(ec, ageData, maxAge);

			// 拼装sex================================================================================================
			var n1 = sexInfo["男"];
			if (n1 > maxSex) {
				maxSex = n1;
			}
			var arr1 = {
				value : n1,
				name : '男'
			};
			sexData.push(arr1);

			var n2 = sexInfo["女"];
			if (n2 > maxSex) {
				maxSex = n2;
			}
			var arr2 = {
				value : n2,
				name : '女'
			};
			sexData.push(arr2);

			sexChart(ec, sexData, maxSex);

			// 拼装address================================================================================================
			$.each(addressInfo, function(key, value) {
				if (value > maxAddr) {
					maxAddr = value;
				}
				var arr = {
					value : value,
					name : key
				};
				addressData.push(arr);
			});
			addrChart(ec, addressData, maxAddr);
		}
	});
}

function ageChart(ec, ageData, maxAge) {
	var myAgeChart = ec.init(document.getElementById('age'));
	var optionAge = {
		title : {
			text : '会员年龄分布',
			subtext : '截止当前时间',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : [ '20以下', '20-30', '30-40', '40-50', '50-60', '60以上' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : false,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'pie', 'funnel' ],
					option : {
						funnel : {
							x : '25%',
							width : '50%',
							funnelAlign : 'left',
							max : maxAge
						}
					}
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
		series : [ {
			name : '年龄分布',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : ageData
		// data:[
		// {value:335, name:'20以下'},
		// {value:310, name:'20-30'},
		// {value:234, name:'30-40'},
		// {value:135, name:'40-50'},
		// {value:1548, name:'50-60'},
		// {value:500, name:'60以上'}
		// ]
		} ]
	};
	// 为echarts对象加载数据
	myAgeChart.setOption(optionAge);
}

function sexChart(ec, sexData, maxSex) {
	var mySexChart = ec.init(document.getElementById('sex'));
	var optionSex = {
		title : {
			text : '会员性别百分比',
			subtext : '截止当前时间',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : [ '男', '女' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : false,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'pie', 'funnel' ],
					option : {
						funnel : {
							x : '25%',
							width : '50%',
							funnelAlign : 'left',
							max : maxSex
						}
					}
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
		series : [ {
			name : '性别百分比',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : sexData
		// data:[
		// {value:335, name:'男'},
		// {value:310, name:'女'}
		// ]
		} ]
	};
	// 为echarts对象加载数据
	mySexChart.setOption(optionSex);
}

function addrChart(ec, addressData, maxAddr) {
	var myAddrChart = ec.init(document.getElementById('address'));
	var optionAddr = {
		title : {
			text : '会员地域分布',
			subtext : '截止当前时间',
			x : 'center'
		},
		tooltip : {
			trigger : 'item'
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : [ '' ]
		},
		dataRange : {
			min : 0,
			max : maxAddr,
			x : 'left',
			y : 'bottom',
			text : [ '高', '低' ], // 文本，默认为数值文本
			calculable : true
		},
		toolbox : {
			show : true,
			orient : 'vertical',
			x : 'right',
			y : 'center',
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : false,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		roamController : {
			show : true,
			x : 'right',
			mapTypeControl : {
				'china' : true
			}
		},
		series : [ {
			name : '会员分布',
			type : 'map',
			mapType : 'china',
			roam : false,
			itemStyle : {
				normal : {
					label : {
						show : true
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : addressData
		// data:[
		// {name: '北京',value: Math.round(Math.random()*1000)},
		// {name: '天津',value: Math.round(Math.random()*1000)},
		// {name: '上海',value: Math.round(Math.random()*1000)},
		// {name: '重庆',value: Math.round(Math.random()*1000)},
		// {name: '河北',value: Math.round(Math.random()*1000)},
		// {name: '河南',value: Math.round(Math.random()*1000)},
		// {name: '云南',value: Math.round(Math.random()*1000)},
		// {name: '辽宁',value: Math.round(Math.random()*1000)},
		// {name: '黑龙江',value: Math.round(Math.random()*1000)},
		// {name: '湖南',value: Math.round(Math.random()*1000)},
		// {name: '安徽',value: Math.round(Math.random()*1000)},
		// {name: '山东',value: Math.round(Math.random()*1000)},
		// {name: '新疆',value: Math.round(Math.random()*1000)},
		// {name: '江苏',value: Math.round(Math.random()*1000)},
		// {name: '浙江',value: Math.round(Math.random()*1000)},
		// {name: '江西',value: Math.round(Math.random()*1000)},
		// {name: '湖北',value: Math.round(Math.random()*1000)},
		// {name: '广西',value: Math.round(Math.random()*1000)},
		// {name: '甘肃',value: Math.round(Math.random()*1000)},
		// {name: '山西',value: Math.round(Math.random()*1000)},
		// {name: '内蒙古',value: Math.round(Math.random()*1000)},
		// {name: '陕西',value: Math.round(Math.random()*1000)},
		// {name: '吉林',value: Math.round(Math.random()*1000)},
		// {name: '福建',value: Math.round(Math.random()*1000)},
		// {name: '贵州',value: Math.round(Math.random()*1000)},
		// {name: '广东',value: Math.round(Math.random()*1000)},
		// {name: '青海',value: Math.round(Math.random()*1000)},
		// {name: '西藏',value: Math.round(Math.random()*1000)},
		// {name: '四川',value: Math.round(Math.random()*1000)},
		// {name: '宁夏',value: Math.round(Math.random()*1000)},
		// {name: '海南',value: Math.round(Math.random()*1000)},
		// {name: '台湾',value: Math.round(Math.random()*1000)},
		// {name: '香港',value: Math.round(Math.random()*1000)},
		// {name: '澳门',value: Math.round(Math.random()*1000)}
		// ]
		} ]
	};
	// 为echarts对象加载数据
	myAddrChart.setOption(optionAddr);
}