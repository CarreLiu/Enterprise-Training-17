<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//修改密码
	function modifyPwd() {
		let id = '${user.id}';
		let oldPass = $('#oldPass').val();
		let newPass = $('#newPass').val();
		let params = {
			"id" : id,
			"oldPass" : oldPass,
			'newPass' : newPass
		};
		$.post('${pageContext.request.contextPath}/sysuser/modifyPwd.do', params, function(data) {
			if (data == 'success') {
				alert('修改密码成功');
				//返回登录页面继续登录
				window.location = '${pageContext.request.contextPath}/login.jsp';
			} else {
				alert("修改密码失败");
				//清空表单数据
				$('#modFrm')[0].reset();
			}
		});
	}
	
	//退出系统
	function logOut() {
		$.ajax({
			method:'post',
			url:'${pageContext.request.contextPath}/sysuser/logOut.do',
			success:function() {
				alert('你已退出系统');
				//重新定位到login.jsp,继续登录
				window.location = '${pageContext.request.contextPath}/login.jsp';
			}
		});
		
	}
	
	function modFrmValidator() {
		$('#modFrm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields:{
                oldPass: {
                    validators: {
                        notEmpty: {
                            message: '登录密码不能为空'
                        }
                    }
                },
                newPass: {
                    validators: {
                        notEmpty: {
                            message: '新的密码不能为空'
                        }          
                    }
                },
                rePass: {
                	validators: {
                		notEmpty: {
                			message: '重复密码不能为空'
                		},
						identical: {
							field: 'newPass',
							message: '两次输入的密码不一致'
						}
                	}
                }
            }
        });
	  }
	
	$(document).ready(function() {
		modFrmValidator();
		$('#modifyBtn').on('click', function() {
			let validator = $('#modFrm').data("bootstrapValidator");	//获取validator对象
	    	validator.validate();	//手动触发验证
	    	if (validator.isValid()) {
	    		modifyPwd();
	    	}
		});
	});
</script>
</head>
<body>
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<img alt="" src="${pageContext.request.contextPath}/images/user.png">
				<span class="font-style">欢迎您：${user.username}管理员！</span>
			</div>
			<div class="panel-body">
				<div class="col-sm-12">
					<ul class="nav nav-pills nav-stacked">
						<li><a class="btn btn-link" data-toggle="modal"
							data-target="#modifyPWD" style="text-align: left;">修改登录密码</a></li>
						<li><a onclick="logOut()" style="cursor:pointer;">退出校园系统</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<img alt="" src="${pageContext.request.contextPath}/images/message.png">
				<span class="font-style">&nbsp;联系我们：</span>
			</div>
			<div class="panel-body">
				<address class="padding-left-10 font-info">
					<strong>联系地址：</strong><br> 南京市紫荆花路68号中兴通讯南京研发中心<br> <strong>联系电话：</strong><br>
					025-12345678
				</address>
			</div>
		</div>
	</div>
	
	<!-- 密码修改model窗口 -->
	<div class="modal fade" id="modifyPWD" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">用户密码修改</h4>
				</div>
				<form action="" class="form-horizontal" method="post" id="modFrm">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-3 control-label">登录密码：</label>
							<div class="col-sm-6">
								<input class="form-control" type="password" id="oldPass"
									value="${user.password}" name="oldPass">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">新的密码：</label>
							<div class="col-sm-6">
								<input class="form-control" type="password" id="newPass" name="newPass">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">重复密码：</label>
							<div class="col-sm-6">
								<input class="form-control" type="password" name="rePass">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							aria-label="Close">关&nbsp;&nbsp;闭</button>
						<button type="reset" class="btn btn-default">重&nbsp;&nbsp;置</button>
						<button id="modifyBtn" type="button" class="btn btn-default">修&nbsp;&nbsp;改</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- MODEL结束 -->
</body>
</html>