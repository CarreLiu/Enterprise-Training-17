<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>学生管理系统</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-table.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-table.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrapValidator.min.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrapValidator.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
    
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
							return row.gender == 1 ? '女' : '男';
	
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
					},
					{
						title : '操作',
						field : 'sid',
						align : 'center',
						formatter : actionFormatter
					}
				]
			});
		}
		
		//操作栏的格式化
		function actionFormatter(value, row, index) {
			let id = value;
			let result = "";
			result += "<a class='btn btn-success btn-xs' onclick='showModalModify(" + id + ")'>修改</a>&nbsp;&nbsp;&nbsp;";
			result += "<a class='btn btn-success btn-xs' onclick='showModalDelete(" + id + ")'>删除</a>";
			return result;
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
			//获取服务器端传递过来的msg
		    let msg='${msg}';
		    if(msg!=''){
		    	//在这个表格上初始化一个弹出栏
		    	$('#cusTable').tooltip({
		    		title:'提示消息',
		    		template:'<div class="tooltip tooltipMsg">'+msg+'</div>',
		    		trigger:'manual'
		    	}).tooltip('show');
		    	
		    }
		    
		    //完成表单提交后表单的tooltip在显示后2秒钟以后自动关闭
			$('[data-toggle="tooltip"]').each(function(){
				//这个this表示的是表单中的元素
				$(this).on('shown.bs.tooltip',function(){//绑定事件，当tooltip显示之后触发
				   //这个this表示当前tooltip,
				   let _this=this;
				   setTimeout(function(){
					 $(_this).tooltip('hide');  
				   },2000);
				});
				
			});
			
			$('#frmAddStudent').bootstrapValidator({
				feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		       	fields: {
		       		username: {
		       			verbose: false,
					    validators: {//验证规则
					        notEmpty: {
					            message: '用户名不能为空'
					        },
		                    stringLength: {
		                        min: 3,
		                        max: 10,
		                        message: '用户名长度必须3-10位'
		                    },
		                    regexp: {
		                        regexp: /^[a-zA-Z0-9_]+$/,
		                        message: '用户名只能包含数字、字母、下划线'
		                    },
		                    remote: {//向远程服务器发送请求进行校验
		                    	type: 'post',
		                    	url: '${pageContext.request.contextPath}/student/findByUsername.do'
		                    }
					    }
					},
					password: {
						validators: {
							notEmpty: {
								message: '密码不能为空'
							},
							different: {
								field: 'username',
								message: '密码不能和用户名相同'
							}
						}
					},
					repassword: {
						validators: {
							notEmpty: {
								message: '确认密码不能为空'
							},
							identical: {
								field: 'password',
								message: '两次输入的密码不一致'
							}
						}
					},
					name: {
						validators: {
							notEmpty: {
								message: '姓名不能为空'
							}
						}
					},
					age: {
						validators: {
							notEmpty: {
								message: '年龄不能为空'
							},
							greaterThan: {
								value: 10,
								inclusive: true,
								message: '年龄必须大于等于10'
							}
						}
					},
					gid: {
						validators: {
							notEmpty: {
								message: '班级不能为空,请选择'
							}
						}
					},
					cid: {
						validators: {
							notEmpty: {
								message: '课程不能为空,请选择'
							}
						}
					}
		       	}
			});
			
			$('#frmModifyStudent').bootstrapValidator({
				feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		       	fields: {
		       		username: {
					    validators: {//验证规则
					        notEmpty: {
					            message: '用户名不能为空'
					        },
		                    stringLength: {
		                        min: 3,
		                        max: 10,
		                        message: '用户名长度必须3-10位'
		                    },
		                    regexp: {
		                        regexp: /^[a-zA-Z0-9_]+$/,
		                        message: '用户名只能包含数字、字母、下划线'
		                    },
		                    remote: {//向远程服务器发送请求进行校验
		                    	type: 'post',
		                    	url: '${pageContext.request.contextPath}/student/findByUsername.do',
		                    	data: {
		                    		primeUsername: function() {
		                    			return $('#primeUsername').val();
		                    		}
		                    	}
		                    }
					    }
					},
					name: {
						validators: {
							notEmpty: {
								message: '姓名不能为空'
							}
						}
					},
					age: {
						validators: {
							notEmpty: {
								message: '年龄不能为空'
							},
							greaterThan: {
								value: 10,
								inclusive: true,
								message: '年龄必须大于等于10'
							}
						}
					},
					gid: {
						validators: {
							notEmpty: {
								message: '班级不能为空,请选择'
							}
						}
					},
					cid: {
						validators: {
							notEmpty: {
								message: '课程不能为空,请选择'
							}
						}
					}
		       	}
			});
		});
		
		//显示修改模态对话框
		function showModalModify(sid) {
			$.post('${pageContext.request.contextPath}/student/findById.do', {'sid':sid}, function(student) {
				//1:将值取出
				$('#primeUsername').val(student.username);
				$('#sid').val(student.sid);
				$('#username').val(student.username);
				$('#name').val(student.name);
				$('#age').val(student.age);
				//设置性别单选按钮
				if (student.gender == '0') {
					$('#male').prop('checked', true);
				}
				else {
					$('#female').prop('checked', true);
				}
				//设置班级和课程下拉列表值
				$('#gid').val(student.grade.gid);
				$('#cid').val(student.course.cid);
				//2:将值写入对应节点
				//3:显示该修改模态框
				$('#modifyStudent').modal('show');
			}, 'json');
		}
		
		//显示删除确认框
		function showModalDelete(sid) {
			//将sid赋值给隐藏表单域
			$('#del_id').val(sid);
			//显示删除确认框
			$('#deleteStudent').modal('show');
		}
		
		//删除学员
		function deleteStudent() {
			//从隐藏表单域取出sid值
			let sid = $('#del_id').val();
			window.location.href = "${pageContext.request.contextPath}/student/deleteStudent.do?sid=" + sid;
		}
		
		//按条件查询学员信息
		function queryByCondition() {
			initTableByCondition();
		}
		
		function initTableByCondition() {
			//alert(1);
			//先销毁表格  
			$('#cusTable').bootstrapTable('destroy');
			//初始化表格,动态从服务器加载数据  
			$("#cusTable").bootstrapTable({
				method : 'post',
				contentType : "application/x-www-form-urlencoded", //post请求必须要有！
				url : "${pageContext.request.contextPath}/student/findByCondition.do", //要请求数据的文件路径
				striped : true, //是否显示行间隔色
				pageNumber : 1, //初始化加载第一页，默认第一页
				pagination : true, //是否分页
				queryParamsType : 'limit', //查询参数组织方式
				queryParams : queryParamsByCondition, //请求服务器时所传的参数
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
							return row.gender == 1 ? '女' : '男';
	
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
					},
					{
						title : '操作',
						field : 'sid',
						align : 'center',
						formatter : actionFormatter
					}
				]
			});
		}
		
        function queryParamsByCondition(params){
        	return {

        		//页号
        		pageNo:(params.offset / params.limit)+1,
        		
        		//页的大小
        		pageSize:params.limit,
        		
        		//查询表单元素的值
        		name:$('#queryName').val(),
        		minAge:$('#queryMinAge').val(),
        		maxAge:$('#queryMaxAge').val(),
        		gender:$('#queryGender :radio:checked').val(),
        		gid:$('#queryGid').val(),
        		cid:$('#queryCid').val()
        	}
        }
	</script>
    
  </head>
  <body>
	<% request.setAttribute("index", 1); %>
    <jsp:include page="top.jsp"/>
    <div class="container margin-top-10">
    	<div class="col-sm-8">
    		<div class="panel panel-default">
			  <div class="panel-body">
				<form class="form-inline" method="post">
				  <div class="form-group">
				    <label>姓名:</label>
				    <input type="text" class="form-control input-sm" placeholder="不填查询所有" id="queryName">
				  </div>
				  &nbsp;
				  <div class="form-group">
				    <label>年龄:</label>
				    <input type="text" class="form-control input-sm" style="width:80px" placeholder="最小年龄" id="queryMinAge">&nbsp;&nbsp;---&nbsp;&nbsp;
				    <input type="text" class="form-control input-sm" style="width:80px" placeholder="最大年龄" id="queryMaxAge">
				  </div>
				   &nbsp;
				  <div class="form-group">
				    <label>性别:</label>
				    <div class="radio" id="queryGender">
					  <label>
					    <input type="radio" value="all" name="gender" checked="checked"> 全部 
					  </label>
					  &nbsp;
					  <label>
					    <input type="radio" value="0" name="gender"> 男
					  </label>
					  &nbsp;
					  <label>
					    <input type="radio" value="1" name="gender"> 女
					  </label>
					</div>
				  </div>
				   <br>
				   <br>
				  <div class="form-group">
			       <label class="control-label">班级：</label>
			         <select class="form-control input-sm" id="queryGid">
			       	 	<option value="all" selected="selected">--查询全部--</option>
			       	 	<c:forEach items="${gradeList}" var="grade" >
			       	 		<option value="${grade.gid}">${grade.gname}</option>
			       	    </c:forEach>
			       	 </select>
			       	 <label class="control-label">课程：</label>
			         <select class="form-control input-sm" id="queryCid">
			       	 	<option value="all" selected="selected">--查询全部--</option>
			       	 	<c:forEach items="${courseList}" var="course" >
			       	 		<option value="${course.cid}">${course.cname}</option>
			       	 	</c:forEach>
			       	 </select>
			      </div>
			       &nbsp;
			       <button type="reset" class="btn btn-success btn-sm">重&nbsp;&nbsp;置</button>
			       <button type="button" class="btn btn-success btn-sm" onclick="queryByCondition()">查&nbsp;&nbsp;询</button>
				</form>
			  	<hr/>
			  	<div class="padding-bottom-3">
			  		<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/student/exportExcel.do">导出学员信息</a>
			  		<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/student/importExcel.do">导入学员信息</a>
			  		<a class="btn btn-success btn-sm" data-toggle="modal" data-target="#addStudent" style="display:inline-block;float:right;">添加新学员</a>
			  	</div>
			    <table id="cusTable" data-toggle="tooltip">
					
				</table>
			  </div>
			</div>
   		</div>
    	<jsp:include page="right.jsp"/>
   	</div>
    <jsp:include page="bottom.jsp"></jsp:include>
    
    <!-- 添加模态框start -->
	<div class="modal fade" id="addStudent" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加新学员</h4>
	        <form action="${pageContext.request.contextPath}/student/addStudent.do" id="frmAddStudent" class="form-horizontal" method="post">
			     <div class="form-group">
			       <label class="col-sm-3 control-label">登录账户：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" name="username">
			       </div>
			    </div>
			    <div class="form-group">
			       <label class="col-sm-3 control-label">登录密码：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="password" name="password">
			       </div>
			    </div>
			    <div class="form-group">
			       <label class="col-sm-3 control-label">确认密码：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="password" name="repassword">
			       </div>
			    </div>
			     <div class="form-group">
			       <label class="col-sm-3 control-label">学生姓名：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" name="name">
			       </div>
			    </div>
			     <div class="form-group">
			       <label class="col-sm-3 control-label">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" name="age">
			       </div>
			    </div>
			     <div class="form-group">
			       <label class="col-sm-3 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
			       <div class="col-sm-6">
				       <div class="radio">
						  <label>
						    <input type="radio" value="0" name="gender" checked="checked"> 男
						  </label>
						  &nbsp;&nbsp;&nbsp;
						  <label>
						    <input type="radio" value="1" name="gender"> 女
						  </label>
						</div>
					</div>
			    </div>
			    <div class="form-group">
			       <label class="col-sm-3 control-label">班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</label>
			       <div class="col-sm-6">
			         <select class="form-control" name="gid">
			       	 	<option value="">-请选择-</option>
			       	 	<c:forEach items="${gradeList}" var="grade" >
			       	 		<option value="${grade.gid}">${grade.gname}</option>
			       	    </c:forEach>
			       	 </select>
			       </div>
			    </div>
			    <div class="form-group">
			       <label class="col-sm-3 control-label">课&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</label>
			       <div class="col-sm-6">
			         <select class="form-control" name="cid">
			       	 	<option value="">-请选择-</option>
			       	 	<c:forEach items="${courseList}" var="course" >
			       	 		<option value="${course.cid}">${course.cname}</option>
			       	 	</c:forEach>
			       	 </select>
			       </div>
			    </div>
			    <div class="form-group">
			       <div class="col-sm-6  col-sm-offset-3">
			       	 <div class="col-sm-6">
				         <button type="reset" class="btn btn-primary btn-block">重&nbsp;&nbsp;置</button>
			       	 </div>
			       	 <div class="col-sm-6">
				         <button type="submit" class="btn btn-primary btn-block">添&nbsp;&nbsp;加</button>
			       	 </div>
			       </div>
			    </div>
		    </form>
	      </div>
	      
	    </div>
	  </div>
	</div>
    <!-- 添加模态框end -->
    
    <!-- 修改模态框start -->
	<div class="modal fade" id="modifyStudent" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">修改学员</h4>
        	  <form action="${pageContext.request.contextPath}/student/modifyStudent.do" id="frmModifyStudent" class="form-horizontal" method="post">
			     <input type="hidden" id="primeUsername">
			     <input type="hidden" id="sid" name="sid" />
			     <div class="form-group">
			       <label class="col-sm-3 control-label">登录账户：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" name="username" id="username">
			       </div>
			    </div>
			     <div class="form-group">
			       <label class="col-sm-3 control-label">学生姓名：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" name="name" id="name">
			       </div>
			    </div>
			     <div class="form-group">
			       <label class="col-sm-3 control-label">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" name="age" id="age">
			       </div>
			    </div>
			     <div class="form-group">
			       <label class="col-sm-3 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
			       <div class="col-sm-6">
				       <div class="radio">
						  <label>
						    <input type="radio" value="0" name="gender" id="male" checked="checked"> 男
						  </label>
						  &nbsp;&nbsp;&nbsp;
						  <label>
						    <input type="radio" value="1" name="gender" id="female"> 女
						  </label>
						</div>
					</div>
			    </div>
			    <div class="form-group">
			       <label class="col-sm-3 control-label">班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</label>
			       <div class="col-sm-6">
			         <select class="form-control" name="gid" id="gid">
			       	 	<option value="">-请选择-</option>
			       	 	<c:forEach items="${gradeList}" var="grade" >
			       	 		<option value="${grade.gid}">${grade.gname}</option>
			       	    </c:forEach>
			       	 </select>
			       </div>
			    </div>
			    <div class="form-group">
			       <label class="col-sm-3 control-label">课&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</label>
			       <div class="col-sm-6">
			         <select class="form-control" name="cid" id="cid">
			       	 	<option value="">-请选择-</option>
			       	 	<c:forEach items="${courseList}" var="course" >
			       	 		<option value="${course.cid}">${course.cname}</option>
			       	 	</c:forEach>
			       	 </select>
			       </div>
			    </div>
			    <div class="form-group">
			       <div class="col-sm-6  col-sm-offset-3">
			       	 <div class="col-sm-6">
				         <button type="reset" class="btn btn-primary btn-block">重&nbsp;&nbsp;置</button>
			       	 </div>
			       	 <div class="col-sm-6">
				         <button type="submit" class="btn btn-primary btn-block">修&nbsp;&nbsp;改</button>
			       	 </div>
			       </div>
			    </div>
		      </form>
	      </div>
	      
	    </div>
	  </div>
	</div>
    <!-- 修改模态框end -->
    
    <!-- 删除提示模态框start -->
	<div class="modal fade" tabindex="-1" role="dialog" id="deleteStudent">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">操作提示</h4>
	      </div>
	      <input type="hidden" id="del_id" />
	      <div class="modal-body">
	        <p style="font-size: 25px; font-style: bold;">确认要删除吗?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" onclick="deleteStudent()">确认</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    <!-- 删除提示模态框end -->
    
  </body>
</html>
