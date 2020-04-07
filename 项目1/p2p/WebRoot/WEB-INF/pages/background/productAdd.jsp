<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>productAdd.html</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/FlatUI/dist/css/flat-ui.css"
	type="text/css"></link>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/plugins/FlatUI/dist/img/favicon.ico">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/FlatUI/dist/js/flat-ui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/fwb/bootstrap-wysiwyg.js"></script>
<script
	src="${pageContext.request.contextPath}/plugins/fwb/external/jquery.hotkeys.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/productAdd.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/FlatUI/dist/js/application.js"></script>

<script type="text/javascript">
	//提交表单之前执行的函数
	function checkData() {
		//将编辑框中的内容赋值给隐藏表单域
		$("input[type='hidden']").val($('#content').html());
		
		//产品名称
		let nameMsg = $('#nameMsg').text();
		
		//贷款利率
		let rateMsg = $('#rateMsg').text();
		
		//贷款规模
		let amountMsg = $('#amountMsg').text();
		
		//联系人
		let linkmanMsg = $('#linkmanMsg').text();
		
		//联系银行
		let bankMsg = $('#bankMsg').text();
		
		//获取所有的异常返回，如果有，不能提交表单
		if (nameMsg || rateMsg || amountMsg || linkmanMsg || bankMsg) {
			//不提交表单
			return false;
		}
		
		//提交表单
		return true;
	}
	
	$(function() {
		//校验产品名称是否为空
		$('#productName').blur(function() {
			$('#nameMsg').text('');
			let name = $(this).val();
			let msg = checkNull(name);
			$('#nameMsg').text(msg);
			//检验该产品名称是否已经存在
			checkNameExist();
		});
		
		//校验贷款利率
		$("input[name='primeLendingRateFrom'], input[name='primeLendingRateTo']").blur(function() {
			$('#rateMsg').text('');
			let rateFrom = $("input[name='primeLendingRateFrom']").val();
			let msg1 = checkNumber(rateFrom);
			let rateTo = $("input[name='primeLendingRateTo']").val();
			let msg2 = checkNumber(rateTo);
			//其中任何一个有值,返回error
			if (msg1 || msg2) {
				$('#rateMsg').text('请输入数字');
			}
			else {
				//获取整型值
				rateFrom = Number(rateFrom);
				rateTo = Number(rateTo);
				//如果前值大于后值,输出提示消息
				if (rateFrom >= rateTo) {
					$('#rateMsg').text('最大利率需大于最小利率');
				}
			}
			
		});
		
		//校验贷款规模
		$("input[name='financingAmountFrom'], input[name='financingAmountTo']").blur(function() {
			$('#amountMsg').text('');
			let amountFrom = $("input[name='financingAmountFrom']").val();
			let msg1 = checkNumber(amountFrom);
			let amountTo = $("input[name='financingAmountTo']").val();
			let msg2 = checkNumber(amountTo);
			//其中任何一个有值,返回error
			if (msg1 || msg2) {
				$('#amountMsg').text('请输入数字');
			}
			else {
				//获取整型值
				amountFrom = Number(amountFrom);
				amountTo = Number(amountTo);
				//如果前值大于后值,输出提示消息
				if (amountFrom >= amountTo) {
					$('#amountMsg').text('最大贷款金额需大于最小贷款金额');
				}
			}
			
		});
		
		//联系人不能为空
		$('#linkman').blur(function() {
			$('#linkmanMsg').text('');
			let name = $(this).val();
			let msg = checkNull(name);
			$('#linkmanMsg').text(msg);
		});
		
		//联系银行不能为空
		$('#bank').blur(function() {
			$('#bankMsg').text('');
			let name = $(this).val();
			let msg = checkNull(name);
			$('#bankMsg').text(msg);
		});
		
		//发行单位下拉列表值发生改变时触发
		$("select[name='companyId']").change(function() {
			let productName = $('#productName').val();
			if (productName) {
				//检验该产品名称是否已经存在
				checkNameExist();
			}
			//如果发行单位就是**银行,那么联系银行就是该单位
			let companyName = $(this).find('option:selected').text();
			if (companyName.endsWith('银行')) {
				$('#bankMsg').text('');
				$('#bank').val(companyName);
			}
		});
		
	});
	
	//校验该产品是否已经存在
	function checkNameExist() {
		let productName = $('#productName').val();
		//获取单位
		let companyId = $("select[name='companyId']").val();
		if (productName && companyId) {
			$.post(
				'${pageContext.request.contextPath}/product/addCheck.do',
				{"productName":productName, "companyId":companyId},
				function(msg) {
					$('#nameMsg').text(msg);
				}
			);
		}
	}
	
	
	//判断是否是数字
	function checkNumber(data) {
		let num = Number(data) + '';
		if (num == 'NaN') {
			return 'error';
		}
		return '';
	}
	
	//判断是否为null
	function checkNull(data) {
		if (!data) {
			return '不能为空';
		}
		return '';
	}
</script>
</head>

<body style="padding: 5px 10px">

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">融资产品添加</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form"
				action="${pageContext.request.contextPath}/product/productAddSuccess.do"
				method="post" onsubmit="return checkData()">
				<div class="form-group">
					<label for="productName" class="col-lg-2 control-label">产品名称</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" id="productName"
							placeholder="产品名称" name="productName">
					</div>
					<span id="nameMsg" style="color:red;"/>
				</div>

				<div class="form-group">
					<label for="productRate" class="col-lg-2 control-label">贷款利率</label>
					<div class="col-lg-10">
						<div class="input-group col-lg-3" style="float: left;">
							<input type="text" class="form-control" placeholder="最低利率,不填默认为0"
								name="primeLendingRateFrom"> <span
								class="input-group-addon">%</span>
						</div>
						<div style="float: left;margin: 0 10px;">-----</div>
						<div class="input-group col-lg-4">
							<input type="text" class="form-control"
								placeholder="最高利率,可不填,不填认为利率为固定值"
								name="primeLendingRateTo"> <span
								class="input-group-addon">%</span>
						</div>
					</div>
					<span id="rateMsg" style="color:red;"/>
				</div>


				<div class="form-group">
					<label for="company" class="col-lg-2 control-label">发行单位</label>
					<div class="col-lg-10">
						<select data-toggle="select"
							class="form-control select select-default"
							name="companyId">
							<c:forEach items="${companies}" var="company">
								<option value="${company.companyId}">${company.companyName}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="loansize" class="col-lg-2 control-label">贷款规模</label>
					<div class="col-lg-10">
						<div class="input-group col-lg-3" style="float: left;">
							<input type="text" class="form-control"
								placeholder="最低贷款规模,不填默认为0" name="financingAmountFrom" value="${product.financingAmountFrom}">
							<span class="input-group-addon">万</span>
						</div>
						<div style="float: left;margin: 0 10px;">-----</div>
						<div class="input-group col-lg-5">
							<input type="text" class="form-control"
								placeholder="最高贷款规模,可不填,不填认为贷款规模为固定值"
								name="financingAmountTo"> <span
								class="input-group-addon">万</span>
						</div>
					</div>
					<span id="amountMsg" style="color:red;"/>
				</div>

				<div class="form-group">
					<label for="productType" class="col-lg-2 control-label">产品类型</label>
					<div class="col-lg-10">
						<select data-toggle="select"
							class="form-control select select-default"
							name="productTypeId">
							<c:forEach items="${productTypes}" var="productType">
								<option value="${productType.productTypeId}">${productType.productTypeName}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="productDuration" class="col-lg-2 control-label">贷款周期</label>
					<div class="col-lg-10">
						<select data-toggle="select"
							class="form-control select select-default"
							name="lendingPeriodId">
							<c:forEach items="${lendingPeriods}" var="lendingPeriod">
								<option value="${lendingPeriod.lendingPeriodId}">${lendingPeriod.period}</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<div class="form-group">
					<label for="linkman" class="col-lg-2 control-label">联系人</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" id="linkman"
							placeholder="联系人" name="linkMan">
					</div>
					<span id="linkmanMsg" style="color:red;"/>
				</div>

				<div class="form-group">
					<label for="bank" class="col-lg-2 control-label">联系银行</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" id="bank"
							placeholder="联系银行" name="ownedBank">
					</div>
					<span id="bankMsg" style="color:red;"/>
				</div>

				<div class="form-group">
					<label for="content" class="col-lg-2 control-label">内容</label>
					<div class="col-lg-10">
						<input type="hidden" name="productDescription" id="hidden">
						<div class="btn-toolbar" data-role="editor-toolbar"
							data-target="#content">
							<div class="btn-group">
								<a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"> <b>加粗</b>
								</a> <a class="btn" data-edit="italic" title="Italic (Ctrl/Cmd+I)">
									<i>倾斜</i>
								</a>
							</div>
							<div class="btn-group">
								<a class="btn" data-edit="insertunorderedlist"
									title="Bullet list"> 列表 </a> <a class="btn"
									data-edit="insertorderedlist" title="Number list"> 有序列表 </a> <a
									class="btn" data-edit="indent" title="Indent (Tab)"> Tab缩进
								</a>
							</div>
							<div class="btn-group">
								<a class="btn" data-edit="justifyleft"
									title="Align Left (Ctrl/Cmd+L)"> 左对齐 </a> <a class="btn"
									data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"> 居中 </a>
								<a class="btn" data-edit="justifyright"
									title="Align Right (Ctrl/Cmd+R)"> 右对齐 </a> <a class="btn"
									data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"> 自动调整
								</a>
							</div>

							<div class="btn-group">
								<a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)">
									撤销 </a> <a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)">
									恢复 </a>
							</div>

						</div>
						<div id="content"
							style="overflow:scroll; max-height:300px;border: 1px solid;height: 200px;"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<button type="submit" class="btn btn-lg btn-success"
							style="padding: 15px 60px;border-radius: 0px;">添加</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>
