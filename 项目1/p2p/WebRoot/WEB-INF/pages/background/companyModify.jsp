<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>companyModify.html</title>

	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="this is my page">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css"
		type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/FlatUI/dist/css/flat-ui.css"
		type="text/css"></link>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/plugins/FlatUI/dist/img/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/FlatUI/dist/js/flat-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/fwb/bootstrap-wysiwyg.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/fwb/external/jquery.hotkeys.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/productAdd.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/FlatUI/dist/js/application.js"></script>
	
	<script type="text/javascript">
		function checkData() {
			$("input[name='companyDetail']").val($('#content').html());
			
			//企业名称
			let nameMsg = $('#nameMsg').text();
			
			//融资佣金
			let financingMsg = $('#financingMsg').text();
			
			//公司详情
			let contentMsg = $('#contentMsg').text();
			
			//获取所有的异常返回，如果有，不能提交表单
			if (nameMsg || financingMsg || contentMsg) {
				//不提交表单
				return false;
			}
		
			//提交表单
			return true;
		}
		
		$(function() {
			//校验企业名称是否为空
			$('#companyName').blur(function() {
				$('#nameMsg').text('');
				let name = $(this).val();
				let msg = checkNull(name);
				$('#nameMsg').text(msg);
				//校验修改后的企业名称是否已经存在
				checkNameExist();
			});
			
			//校验融资佣金
			$('#financingInReturn').blur(function() {
				$('#financingMsg').text('');
				let name = $(this).val();
				let msg1 = checkNull(name);
				$('#financingMsg').text(msg1);
				let msg2 = checkNumber(name);
				if (msg2)
					$('#financingMsg').text('请输入数字');
			})
			
			//检查公司详情是否为空
			$('#content').blur(function() {
				$('#contentMsg').text('');
				let name = $(this).text();
				let msg = checkNull(name);
				$('#contentMsg').text(msg);
			});
		});
		
		//校验修改后的企业名称是否已经存在
		function checkNameExist() {
			let companyId = $('#companyId').val();
			let companyName = $('#companyName').val();
			if (companyName) {
				$.post(
					'${pageContext.request.contextPath}/company/modifyCheck.do',
					{"companyName":companyName,"companyId":companyId},
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
	    <h3 class="panel-title">企业修改</h3>
	  </div>
	  <div class="panel-body">
	  		
	  		<form class="form-horizontal" role="form"
	  			action="${pageContext.request.contextPath}/company/companyModifyPost.do"
	  			method="post" onsubmit="return checkData()">
	  			<input type="hidden" name="companyDetail" id="hidden">
	  			<input type="hidden" name="pageNo" value="${pageNo}">
	  			
	  		<div class="form-group">
              <label for="companyId" class="col-lg-2 control-label">企业编号</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" id="companyId" value="${company.companyId}" name="companyId" readonly="readonly">
              </div>
            </div>
	  		
            <div class="form-group">
              <label for="companyName" class="col-lg-2 control-label">企业名称</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" id="companyName" placeholder="企业名称"
                	value="${company.companyName}" name="companyName">
              </div>
              <span id="nameMsg" style="color:red;"></span>
            </div>
            
            <div class="form-group">
            	<label for="productRate" class="col-lg-2 control-label">融资佣金</label>
            	<div class="col-lg-10">
			        <div class="input-group col-lg-3" style="float: left;">
			            <input type="text" class="form-control" id="financingInReturn" placeholder="佣金数"
			            	value="${company.financingInReturn}" name="financingInReturn">
			            <span class="input-group-addon">%</span>
			        </div>
			    </div>
			    <span id="financingMsg" style="color:red;"></span>
          	</div>
			            
            <div class="form-group">
              <label for="content" class="col-lg-2 control-label">内容</label>
              <div class="col-lg-10">
			<div class="btn-toolbar" data-role="editor-toolbar"	data-target="#content">
				<div class="btn-group">
					<a class="btn" data-edit="indent" title="Indent (Tab)">
						Tab缩进
					</a>
				</div>
				<div class="btn-group">
					<a class="btn" data-edit="justifyleft"
						title="Align Left (Ctrl/Cmd+L)">
						左对齐
					</a> 
					<a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)">
						居中
					</a> 
					<a class="btn" data-edit="justifyright"
						title="Align Right (Ctrl/Cmd+R)">
						右对齐
					</a> 
					<a class="btn" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)">
						自动调整
					</a>
				</div>
		
		 		<div class="btn-group">
					<a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)">
						撤销
					</a> 
					<a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)">
						恢复
					</a>
				</div>
				
			</div>
            <div id="content" style="overflow:scroll; max-height:300px;border: 1px solid;height: 200px;">${company.companyDetail}</div>
         </div>
         <span id="contentMsg" style="color:red;"></span>
     </div>
            
            
            <div class="form-group">
              <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-lg btn-info" style="padding: 15px 60px;border-radius: 0px;">修改</button>
              </div>
            </div>
          </form>
	  		
	  		
	  		
	  </div>
	</div>
	
</body>
</html>
    