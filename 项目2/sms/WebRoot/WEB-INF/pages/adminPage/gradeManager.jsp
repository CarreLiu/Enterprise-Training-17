<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		function initTable(){
        
        	//先销毁表格
        	$('#cusTable').bootstrapTable('destroy');
        	//初始化表格，动态从数据库中加载数据
        	$('#cusTable').bootstrapTable({
        		method: 'post',
		        contentType: "application/x-www-form-urlencoded",//post请求必须要有！
		        url:"${pageContext.request.contextPath}/grade/findAllGrade.do",//要请求数据的文件路径
		        striped: true, //是否显示行间隔色
		        pageNumber: 1, //初始化加载第一页，默认第一页
		        pagination:true,//是否分页
		        queryParamsType:'limit',//查询参数组织方式
		        queryParams:queryParams,//请求服务器时所传的参数
		        sidePagination:'server',//指定服务器端分页
		        pageSize:5,//单页记录数
		        pageList:[5,10,20,30],//分页步进值
		        showRefresh:false,//刷新按钮
		        showColumns:false,
		        clickToSelect: true,//是否启用点击选中行
		        toolbarAlign:'right',//工具栏对齐方式
		        buttonsAlign:'right',//按钮对齐方式
		        undefinedText: "空",//当数据为 undefined 时显示的字符  
		        columns:[
		          {
		            title:'全选',
		            field:'select',
		            //复选框
		            checkbox:true,
		            width:25,
		            align:'center',
		            valign:'middle'
		          },
		          {
		          	title:'编号',
		          	field:'gid',
		          	align:'center'


		          },
		          {
		          	title:'班级名称',
		          	field:'gname',
		          	align:'center'
		          },
		          {
		          	title:'班级详情',
		          	field:'gdesc',
		          	align:'center',
		          },
		          {
		          	title:'状态',
		          	field:'state',
		          	align:'center',
		          	formatter:function(value,row,index){
		          		return value == 0 ? '禁用' : '启用';
		          	}
		          },
		          {
		          	title:'操作',
		          	field:'gid',
		          	align:'center',
		          	formatter:actionFormatter
		          }
				]
        	});
        	
        }
        
		//操作栏的格式化
        function actionFormatter(value,row,index){
             let result='';
             if(row.state == 1){
             	result += '<button type="button" class="btn btn-warning btn-xs" onclick="changeState('+row.gid+','+row.state+')">禁用</button>&nbsp;&nbsp;&nbsp;';
             }else{
             	result += '<button type="button" class="btn btn-success btn-xs" onclick="changeState('+row.gid+','+row.state+')">启用</button>&nbsp;&nbsp;&nbsp;';
             }
             result += '<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modfiyGrade" onclick="showModalModify('+row.gid+')">修改</button>&nbsp;&nbsp;&nbsp;';
             result += '<button type="button" class="btn btn-danger btn-xs" onclick="showModalDelete('+row.gid+')">删除</button>';
        	 return result;
        }
        
      	//请求服务数据时所传参数
        function queryParams(params){
        	return {

        		//页号
        		pageNo:(params.offset / params.limit)+1,
        		
        		//页的大小
        		pageSize:params.limit
        		
        	}

        }
      	
      	//显示修改确认框
      	function showModalModify(gid) {
      		$.post('${pageContext.request.contextPath}/grade/findById.do', {'gid':gid}, function(grade) {
      			$('#primeGname').val(grade.gname);
      			$('#gid').val(grade.gid);
      			$('#gname').val(grade.gname);
      			$('#gdesc').val(grade.gdesc);
      		}, 'json');
      	}
      	
        //显示删除确认框
   		function showModalDelete(gid) {
   			//将gid赋值给隐藏表单域
   			$('#del_id').val(gid);
   			//显示删除确认框
   			$('#deleteGrade').modal('show');
   		}
   		
   		//删除班级
   		function deleteGrade() {
   			//从隐藏表单域取出gid值
   			let gid = $('#del_id').val();
   			window.location.href = "${pageContext.request.contextPath}/grade/deleteGrade.do?gid=" + gid;
   		}
   		
   		//修改班级状态
   		function changeState(gid, state) {
   			let flag;
			if(state == '1')
				flag = 0;
			else
				flag = 1;
			$.post('${pageContext.request.contextPath}/grade/modifyGradeState.do', {'gid':gid, 'state':flag}, function() {
				$('#cusTable').bootstrapTable('refresh');
			});
   		}
   		
   		//按条件查询班级信息
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
				url : "${pageContext.request.contextPath}/grade/findByCondition.do", //要请求数据的文件路径
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
				columns:[
		          {
		            title:'全选',
		            field:'select',
		            //复选框
		            checkbox:true,
		            width:25,
		            align:'center',
		            valign:'middle'
		          },
		          {
		          	title:'编号',
		          	field:'gid',
		          	align:'center'


		          },
		          {
		          	title:'班级名称',
		          	field:'gname',
		          	align:'center'
		          },
		          {
		          	title:'班级详情',
		          	field:'gdesc',
		          	align:'center',
		          },
		          {
		          	title:'状态',
		          	field:'state',
		          	align:'center',
		          	formatter:function(value,row,index){
		          		return value == 0 ? '禁用' : '启用';
		          	}
		          },
		          {
		          	title:'操作',
		          	field:'gid',
		          	align:'center',
		          	formatter:actionFormatter
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
        		gname:$('#queryGname').val()
        	}
        }
      	
      	$(function() {
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
      		
      		$('#frmAddGrade').bootstrapValidator({
				feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		       	fields: {
					gname: {
						validators: {
							notEmpty: {
								message: '班级名称不能为空'
							},
							remote: {
								type: 'post',
		                    	url: '${pageContext.request.contextPath}/grade/findByGradename.do'
							}
						}
					},
					gdesc: {
						validators: {
							notEmpty: {
								message: '班级详情不能为空'
							}
						}
					}
		       	}
			});
      		
      		$('#frmModifyGrade').bootstrapValidator({
				feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		       	fields: {
					gname: {
						validators: {
							notEmpty: {
								message: '班级名称不能为空'
							},
							remote: {
								type: 'post',
		                    	url: '${pageContext.request.contextPath}/grade/findByGradename.do',
		                    	data: {
		                    		primeGname: function() {
		                    			return $('#primeGname').val();
		                    		}
		                    	}
							}
						}
					},
					gdesc: {
						validators: {
							notEmpty: {
								message: '班级详情不能为空'
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
			  	<form class="form-inline">
				  <div class="form-group">
				    <label>班级:</label>
				    <input type="text" class="form-control input-sm" placeholder="不填查询所有" id="queryGname">
				  </div>
			       <button type="reset" class="btn btn-success btn-sm">重&nbsp;&nbsp;置</button>
			       <button type="button" class="btn btn-success btn-sm" onclick="queryByCondition()">查&nbsp;&nbsp;询</button>
				</form>
				<hr/>
			   <div class="padding-bottom-3" style="text-align: right;">
			  		<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#addGrade" >添加新班级</button>
			  	</div>
			    <table id="cusTable" data-toggle="tooltip">
					
				</table>
			  </div>
			</div>
   		</div>
    	<jsp:include page="right.jsp"/>
   	</div>
   	<jsp:include page="bottom.jsp"></jsp:include>
    
    <!-- 班级修改model窗口 -->
    <div class="modal fade" id="modfiyGrade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">班级信息修改</h4>
	      </div>
	      <form action="${pageContext.request.contextPath}/grade/modifyGrade.do" id="frmModifyGrade" class="form-horizontal" method="post">
		      <input type="hidden" id="primeGname">
		      <div class="modal-body">
				<div class="form-group">
			       <label class="col-sm-3 control-label">班级编号：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" value="1" readonly="readonly" name="gid" id="gid">
			       </div>
			    </div>
			     <div class="form-group">
			       <label class="col-sm-3 control-label">班级名称：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" value="软件开发一班" name="gname" id="gname">
			       </div>
			    </div>
			    <div class="form-group">
			       <label class="col-sm-3 control-label">班级详情：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" value="中兴软件开发一班" name="gdesc" id="gdesc">
			       </div>
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
	<!-- 班级添加model窗口 -->
    <div class="modal fade" id="addGrade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">新班级添加</h4>
	      </div>
	      <form action="${pageContext.request.contextPath}/grade/addGrade.do" id="frmAddGrade" class="form-horizontal" method="post">
		      <div class="modal-body">
			     <div class="form-group">
			       <label class="col-sm-3 control-label">班级名称：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" name="gname">
			       </div>
			    </div>
			    <div class="form-group">
			       <label class="col-sm-3 control-label">班级详情：</label>
			       <div class="col-sm-6">
			         <input class="form-control" type="text" name="gdesc">
			       </div>
			    </div>
		      </div>
		      <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
		          <button type="reset" class="btn btn-default">重&nbsp;&nbsp;置</button>
		          <button type="submit" class="btn btn-default">添&nbsp;&nbsp;加</button>
			  </div>
		  </form>
	    </div>
	  </div>
	</div>
	<!-- MODEL结束 -->
	
	<!-- 删除提示模态框start -->
	<div class="modal fade" tabindex="-1" role="dialog" id="deleteGrade">
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
	        <button type="button" class="btn btn-primary" onclick="deleteGrade()">确认</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    <!-- 删除提示模态框end -->
  </body>
</html>
