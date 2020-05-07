<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>companyReportDetail.html</title>

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
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/FlatUI/dist/js/flat-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap/js/messager.js"></script>
</head>

<body style="padding: 5px 10px">
	
	<div class="panel panel-primary">
	  <div class="panel-heading">
	    <h3 class="panel-title">企业报表详情(${reportDetailList[0].product.company.companyName}:
	    	<c:choose>
	    		<c:when test="${reportYear!=null}">${reportYear}</c:when>
	    		<c:otherwise>全部</c:otherwise>
	    	</c:choose>
	    	年)
	    </h3>
	  </div>
	  <div class="panel-body">
	  		<table class="table table-hover">
	  			<thead>
	  				<tr>
	  					<th>融资产品</th>
	  					<th>融资目标</th>
	  					<th>实际融资数</th>
	  					<th>佣金</th>
	  				</tr>
	  			</thead>
	  			<tbody>
	  				<c:forEach items="${reportDetailList}" var="report">
		  			<tr> 	 	 	 	 	 
		  				<td>${report.product.productName}</td>
		  				<td>${report.targetFinance}万</td>
		  				<td>${report.actualFinance}万</td>
		  				<td>${report.moneyReturn}万</td>
		  			</tr>
		  			</c:forEach>
	  			</tbody>
	  		</table>
	  </div>
	</div>
	
</body>
</html>
    