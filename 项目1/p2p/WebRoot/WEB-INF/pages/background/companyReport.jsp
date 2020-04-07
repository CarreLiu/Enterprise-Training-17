<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>companyReport.html</title>

	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="this is my page">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css"
		type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/FlatUI/dist/css/flat-ui.css"
		type="text/css"></link>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/plugins/FlatUI/dist/img/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datetimepicker/bootstrap-datetimepicker.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/FlatUI/dist/js/flat-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap/js/messager.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/datetimepicker/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
	
	<script type="text/javascript">
		function clearAll() {
			window.location.href = "${pageContext.request.contextPath}/report/findAll.do";
		}
	</script>
</head>

<body style="padding: 5px 10px">

	<div class="panel panel-primary">
	  <div class="panel-heading">
	    <h3 class="panel-title">企业报表</h3>
	  </div>
	  
	  
	  <div class="panel-body">
	  
	  		<form class="form-inline" role="form" method="post" style="margin-bottom: 10px;" action="${pageContext.request.contextPath}/report/yearReport.do">
				  <div class="form-group">
				    <label  for="datetimepicker">查询年份</label>
				    <input type="text" name="year" value="${reportYear==null?'':reportYear}" readonly="readonly"  id="datetimepicker" data-date-format="yyyy" class="form-control" placeholder="">
				  </div>
				  <input type="submit" class="btn btn-default" value="查询" />
				  <input type="button" class="btn btn-danger" value="清空条件"  onclick="clearAll()"/>
				  <hr/>
			</form>
	  		
	  
	  
	  		<table class="table table-hover">
	  			<thead>
	  				<tr>
	  					<th>企业名称</th>
	  					<th>融资产品总数</th>
	  					<th>总融资目标</th>
	  					<th>实际融资数</th>
	  					<th>佣金</th>
	  					<th>操作</th>
	  				</tr>
	  			</thead>
	  			<tbody>
	  				<c:forEach items="${reportList}" var="report">
		  			<tr> 	 	 	 	 	 
		  				<td>${report.company.companyName}</td>
		  				<td>${report.totalCountOfProduct}</td>
		  				<td>${report.totalTargetFinance}万</td>
		  				<td>${report.totalActualFinance}万</td>
		  				<td>${report.totalMoneyReturn}万</td>
		  				<td>
		  					<a class="btn btn-primary btn-xs btn-success" href="${pageContext.request.contextPath}/report/detail.do?id=${report.company.companyId}">
		  						详情
		  						<span class="glyphicon glyphicon-list-alt"></span>
		  					</a>
		  				</td>
		  			</tr>
		  			</c:forEach>
	  			</tbody>
	  		</table>
	  </div>
	</div>
				<script type="text/javascript">
					$('#datetimepicker').datetimepicker({
				        language:  'zh-CN',
				        weekStart: 1,
				        todayBtn:  1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 4,
						minView: 4,
						forceParse: 0
				    });
				</script>
</body>
</html>
    