<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function showTime() {
		let date = new Date();
		$('#timeId').html(dateFormat(date, 'yyyy 年 MM 月 dd 日 hh:mm:ss'));
	}
	
	//时间戳格式化处理
	function dateFormat(date, format) {
		date = new Date(date);
		var map = {
			"M" : date.getMonth() + 1, //月份
			"d" : date.getDate(), //日
			"h" : date.getHours(), //小时
			"m" : date.getMinutes(), //分
			"s" : date.getSeconds(), //秒
			"q" : Math.floor((date.getMonth() + 3) / 3), //季度
			"S" : date.getMilliseconds() //毫秒
		};
		format = format.replace(/([yMdhmsqS])+/g, function(all, t) {
			var v = map[t];
			if (v !== undefined) {
				if (all.length > 1) {
					v = '0' + v;
					v = v.substr(v.length - 2);
				}
				return v;
			} else if (t === 'y') {
				return (date.getFullYear() + '').substr(4 - all.length);
			}
			return all;
		});
		return format;
	}

	$(function() {
		showTime();
		setInterval(showTime,1000);
		
		//正确显示导航栏
		let navIndex = ${index};
		$('ul.nav li').each(function(i) {
			//将所有导航栏背景清空
			$(this).removeClass('active');
			if (navIndex == i) {
				$(this).addClass('active');
			}
		});
		
	});

</script>
</head>
<body>
	<div class="container nav-height">
		<div class="col-sm-3">
			<img alt="" src="${pageContext.request.contextPath}/images/logn.png">
		</div>
		<div
			class="col-md-3 col-md-offset-6 visible-md-block visible-lg-block">
			<p class="p-css" id="timeId"></p>
		</div>
	</div>
	<div class="nav-style">
		<div class="container">
			<div class="col-sm-12">
				<ul class="nav nav-pills">
					<li class="active"><a href="${pageContext.request.contextPath}/sysuser/toAdminMain.do">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
					<li><a href="${pageContext.request.contextPath}/student/studentManager.do">学生管理</a></li>
					<li><a href="${pageContext.request.contextPath}/grade/gradeManager.do">班级管理</a></li>
					<li><a href="${pageContext.request.contextPath}/course/courseManager.do">课程管理</a></li>
					<li><a href="${pageContext.request.contextPath}/blog/blogManager.do">日志管理</a></li>
					<li class="dropdown"><a href="#" data-toggle="dropdown">预留连接
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a data-toggle="modal" data-target="#modifyPWD" style="cursor:pointer;">修改登录密码</a></li>
							<li role="separator" class="divider"></li>
							<li><a onclick="logOut()" style="cursor:pointer;">退出校园系统</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>