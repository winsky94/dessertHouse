function cancelMember () {
	alert("取消会员资格");
}

function charge () {
	$("#pay").toggle();
}

function update () {
	
}

function editInfo () {
	var txt='\
		<form class="form-horizontal">\
			<div class="row-fluid control-group"></div>\
			<div class="row-fluid control-group">\
				<div class="span4">\
					<label class="control-label" for="memberName">用户名：</label>\
					<div class="controls">\
						<input type="text" id="memberName" placeholder="用户名" value="winsky" disabled>\
					</div>\
				</div>\
				<div class="span4">\
					<label class="control-label" for="age">年　龄：</label>\
					<div class="controls">\
						<input type="number" id="age" placeholder="年　龄" min="0" max="100" value="20">\
					</div>\
				</div>\
			</div>\
			<div class="row-fluid control-group"></div>\
			<div class="row-fluid control-group">\
				<div class="span4">\
					<label class="control-label" for="password">密　码：</label>\
					<div class="controls">\
						<input type="password" id="password" placeholder="密　码" value="12345a">\
					</div>\
				</div>\
				<div class="span4">\
					<label class="control-label" for="confirm_password">确认密码：</label>\
					<div class="controls">\
						<input type="password" id="confirm_password" placeholder="确认密码" value="12345a">\
					</div>\
				</div>\
			</div>\
			<div class="row-fluid control-group"></div>\
			<div class="row-fluid control-group">\
				<div class="span4">\
					<label class="control-label" for="age">性　别：</label>\
					<div class="controls">\
						<select class="browser-default" id="sex">\
							<option value="" disabled selected>请选择性别</option>\
							<option value="男" selected>男</option>\
							<option value="女">女</option>\
							<option value="保密">保密</option>\
						</select>\
					</div>\
				</div>\
				<div class="span4">\
					<label class="control-label" for="birthday">生　日：</label>\
					<div class="controls">\
						<input id="birthday" class="Wdate" type="text" onClick="WdatePicker();" placeholder="生　日" value="1994-10-23">\
					</div>\
				</div>\
			</div>\
			<div class="row-fluid control-group"></div>\
			<div class="row-fluid control-group">\
				<div class="span4">\
					<label class="control-label" for="telephone">电　话：</label>\
					<div class="controls">\
						<input type="tel" id="telephone" placeholder="电　话" value="18013878510">\
					</div>\
				</div>\
				<div class="span4">\
					<label class="control-label" for="cardId">银行卡号：</label>\
					<div class="controls">\
						<input type="text" id="cardId" placeholder="银行卡号" onkeyup="formatBankNo(this)" onkeydown="formatBankNo(this)" value="222">\
					</div>\
				</div>\
			</div>\
			<div class="row-fluid control-group"></div>\
			<div class="row-fluid control-group">\
				<div class="span4">\
					<div class="controls">\
						<button type="button" class="btn" onclick="update();">\
							确认\
						</button>\
						<button type="button" style="display:inline;margin-left: 5px;" class="btn center" onclick="showInfo();">\
							取消\
						</button>\
					</div>\
				</div>\
				<div class="span4">\
					<div class="controls">\
						<font color="red" size="2" style="display:inline;">\
							<span id="message"></span>\
						</font>\
					</div>\
				</div>\
			</div>\
		</form>\
	';
	$("#info").html('');
	$("#edit_info").html(txt);
}

function showInfo () {
	var txt='\
			<!-- 会员左侧信息 -->\
			<div class="span4">\
				<div class="table-row" style="padding-top: 10px;"> \
					<div class="property">等　级：</div> \
					<div class="value" id="rank">1</div> \
				</div>\
				<div class="table-row"> \
					<div class="property">昵　称：</div> \
					<div class="value" id="userName">winsky</div> \
				</div>\
				<div class="table-row"> \
					<div class="property">性　别：</div> \
					<div class="value" id="sex">男</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">电　话：</div> \
					<div class="value" id="telephone">18013878510</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">余　额：</div> \
					<div class="value" id="validMoney">1000元</div> \
				</div> \
			</div>\
			<!-- 会员左侧信息 结束 -->\
			<!-- 会员右侧信息 -->\
			<div class="span4">\
				<div class="table-row" style="padding-top: 10px;"> \
					<div class="property">积　分：</div> \
					<div class="value" id="point">0</div> \
				</div>\
				<div class="table-row"> \
					<div class="property">年　龄：</div> \
					<div class="value" id="age">20</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">生　日：</div> \
					<div class="value" id="birthday">1994-10-23</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">银行卡号：</div> \
					<div class="value" id="cardId">2222</div> \
				</div> \
				<div class="table-row"> \
					<div class="property">有效期：</div> \
					<div class="value" id="validDate">2022-03-02</div> \
				</div> \
			</div>\
			<!-- 会员右侧信息 结束 -->\
	';
	$("#edit_info").html('');
	$("#info").html(txt);
}