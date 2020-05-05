<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>学生管理系统</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" type="text/css"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-table.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-table.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrapValidator.min.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrapValidator.min.js"></script>
    <script>
    
    	$(function(){
    		CKEDITOR.replace('content');
    		
    		$('#frmAddBlog').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    title: {
                        validators: {
                            notEmpty: {
                                message: '日志标题不能为空'
                            }
                        }
                    }
                }
            });
    		
    	});

    </script>
  </head>
  <body>
    <% request.setAttribute("index", 2); %>
    <jsp:include page="top.jsp"/>
    <div class="container margin-top-10">
    	<div class="col-sm-8">
    		<div class="panel panel-default">
			  <div class="panel-body">
				<form id="frmAddBlog" action="${pageContext.request.contextPath}/blog/addBlog.do" method="post">
				  <div class="form-group">
				    <label>日志标题：</label>
				    <input type="text" class="form-control" id="title" name="title">
				  </div>
				  <div class="form-group">
				    <label>日志正文：</label>
				    <textarea id="content" cols="20" rows="2" class="ckeditor" name="content"></textarea>
				  </div>
				  <div class="text-right">
					  <button type="submit" class="btn btn-success">发&nbsp;&nbsp;&nbsp;&nbsp;表</button>
				  </div>
				</form>
			  </div>
			</div>
   		</div>
    	<jsp:include page="right.jsp"/>
   	</div>
    <jsp:include page="bottom.jsp"></jsp:include>
    
  </body>
</html>
