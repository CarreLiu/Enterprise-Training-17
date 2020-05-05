<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>学生管理系统</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" type="text/css"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrapValidator.min.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrapValidator.min.js"></script>
	
  </head>
  <body>
    <% request.setAttribute("index", 0); %>
	<jsp:include page="top.jsp"/>
    <div class="container margin-top-10">
    	<div class="col-sm-8">
    		<div class="panel panel-default">
			  <div class="panel-body">
			    <table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>编号</th>
							<th>用户名</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
							<th>班级</th>
							<th>学科</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${students}" var="student">
						<tr>
							<td>${student.sid}</td>
							<td>${student.username}</td>
							<td>${student.name}</td>
							<td>${student.gender}</td>
							<td>${student.age}</td>
							<td>${student.grade.gname}</td>
							<td>${student.course.cname}</td>
						</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8" style="text-align: center;">
							  <ul class="pagination" style="margin: 0px;">
							    <li>
							      <a href="#" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							      </a>
							    </li>
							    <li class="active"><a href="#">1</a></li>
							    <li><a href="#">2</a></li>
							    <li><a href="#">3</a></li>
							    <li><a href="#">4</a></li>
							    <li><a href="#">5</a></li>
							    <li>
							      <a href="#" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							    </li>
							  </ul>
							</td>
						</tr>
					</tfoot>
				</table>
			  </div>
			</div>
   		</div>
    	<jsp:include page="right.jsp"/>
   	</div>
    <jsp:include page="bottom.jsp"/>
    <!-- 密码修改model窗口 -->
    <div class="modal fade" id="modfiyPWD" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">用户密码修改</h4>
	      </div>
	      <form action="" class="form-horizontal" method="post">
		      <div class="modal-body">
			     <div class="form-group">
			       <label class="col-sm-3 control-label">登录密码：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="password">
			       </div>
			       <label class="col-sm-3 control-label error-info" style="text-align:left;">*不可为空</label>
			    </div>
			     <div class="form-group">
			       <label class="col-sm-3 control-label">新的密码：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="password">
			       </div>
			       <label class="col-sm-3 control-label error-info" style="text-align:left;">*不可为空</label>
			    </div>
			     <div class="form-group">
			       <label class="col-sm-3 control-label">重复密码：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="password">
			       </div>
			       <label class="col-sm-3 control-label error-info" style="text-align:left;">*不可为空</label>
			    </div>
		      </div>
		      <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
		          <button type="reset" class="btn btn-default">重&nbsp;&nbsp;置</button>
		          <button type="submit" class="btn btn-default">修&nbsp;&nbsp;改</button>
			  </div>
		  </form>
	    </div>
	  </div>
	</div>
	<!-- MODEL结束 -->
  </body>
</html>
