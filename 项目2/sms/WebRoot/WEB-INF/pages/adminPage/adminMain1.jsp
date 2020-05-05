<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理系统</title>
<meta charset="utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-table.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-table.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrapValidator.min.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrapValidator.min.js"></script>

<script type="text/javascript">

	function initTable() {
		//alert(1);
		//先销毁表格  
		$('#cusTable').bootstrapTable('destroy');
		//初始化表格,动态从服务器加载数据  
		$("#cusTable").bootstrapTable({
			method : 'post',
			contentType : "application/x-www-form-urlencoded", //post请求必须要有！
			url : "${pageContext.request.contextPath}/student/findAllByPage.do", //要请求数据的文件路径
			striped : true, //是否显示行间隔色
			pageNumber : 1, //初始化加载第一页，默认第一页
			pagination : true, //是否分页
			queryParamsType : 'limit', //查询参数组织方式
			queryParams : queryParams, //请求服务器时所传的参数
			sidePagination : 'server', //指定服务器端分页
			pageSize : 5, //单页记录数
			pageList : [ 5, 10, 20, 30 ], //分页步进值
			showRefresh : false, //刷新按钮
			showColumns : false,
			clickToSelect : true, //是否启用点击选中行
			toolbarAlign : 'right', //工具栏对齐方式
			buttonsAlign : 'right', //按钮对齐方式
			undefinedText : "空", //当数据为 undefined 时显示的字符  
			columns : [
				{
					title : '全选',
					field : 'select',
					//复选框
					checkbox : true,
					width : 25,
					align : 'center',
					valign : 'middle'
				},
				{
					title : '用户名',
					field : 'username',
					align : 'center'
				},
				{
					title : '姓名',
					field : 'name',
					align : 'center'
				},
				{
					title : '性别',
					field : 'gender',
					align : 'center',
					formatter : function(value, row, index) {
						return row.gender == 0 ? '女' : '男';

					}
				},
				{
					title : '年龄',
					field : 'age',
					align : 'center'
				},
				{
					title : '班级',
					field : 'grade',
					align : 'center',
					formatter : function(value, row, index) {
						//通过formatter属性自定义显示
						//value:该行的属性
						//row:该行记录
						//index:该行下标
						return value.gname;

					}
				},
				{
					title : '学科',
					field : 'course',
					align : 'center',
					formatter : function(value, row, index) {
						return value.cname;
					}
				}
			]
		});
	}
	//请求服务数据时所传参数
	function queryParams(params) {
		return {
			//页码  
			pageNo : (params.offset / params.limit) + 1,
			//页面大小
			pageSize : params.limit
		}
	}


	$(document).ready(function() {
		//调用函数，初始化表格  
		initTable();
	});

</script>
</head>
<body>
	<% request.setAttribute("index", 0); %>
	<jsp:include page="top.jsp"/>
	<div class="container margin-top-10">
		<div class="col-sm-8">
			<div class="panel panel-default">
				<div class="panel-body">
					<table id="cusTable">

					</table>
				</div>
			</div>
		</div>
		<jsp:include page="right.jsp"/>
	</div>
	<jsp:include page="bottom.jsp"/>
</body>
</html>