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
		        url:"${pageContext.request.contextPath}/blog/findAllBlog.do",//要请求数据的文件路径
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
		          	field:'bid',
		          	align:'center'
		          },
		          {
		          	title:'日志标题',
		          	field:'title',
		          	align:'center',
		          	formatter:function(value,row,index){
		          		return '<a data-toggle="modal" data-target="#blogDetail" style="cursor:pointer;" onclick="showModalDetail(' + row.bid + ')">' + value + '</a>';
		          	}
		          },
		          {
		          	title:'更新时间',
		          	field:'createDate',
		          	align:'center',
					formatter:function(value,row,index){
						return dateFormat(value, 'yyyy/MM/dd');
					}
		          },
		          {
		          	title:'作者',
		          	field:'student',
		          	align:'center',
		          	formatter:function(value,row,index){
		          		return value.username;
		          	}
		          },
		          {
		          	title:'操作',
		          	field:'bid',
		          	align:'center',
		          	formatter:actionFormatter
		          }
				]

        	});
        	
        }
        
		//操作栏的格式化
        function actionFormatter(value,row,index){
			let id = value;
            let result='';
            result += '<a class="btn btn-danger btn-xs" onclick="showModalDelete(' + id + ')">删除</a>';
        	return result;
        }
        
        function queryParams(params){
        	return {

        		//页号
        		pageNo:(params.offset / params.limit)+1,
        		
        		//页的大小
        		pageSize:params.limit

        	}

        }
        
        //显示日志详情模态框
        function showModalDetail(bid) {
        	$.post('${pageContext.request.contextPath}/blog/blogDetail.do', {'bid':bid}, function(blog) {
      			$('#blogTitle').html(blog.title);
      			$('#blogContent').html(blog.content);
      		}, 'json');
        }
      	
        //显示删除确认框
   		function showModalDelete(bid) {
   			//将bid赋值给隐藏表单域
   			$('#del_id').val(bid);
   			//显示删除确认框
   			$('#deleteBlog').modal('show');
   		}
   		
   		//删除日志
   		function deleteBlog() {
   			//从隐藏表单域取出bid值
   			let bid = $('#del_id').val();
   			window.location.href = "${pageContext.request.contextPath}/blog/deleteBlog.do?bid=" + bid;
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
   		});
   		
	</script>
  </head>
  <body>
    <% request.setAttribute("index", 4); %>
    <jsp:include page="top.jsp"/>
    <div class="container margin-top-10">
    	<div class="col-sm-8">
    		<div class="panel panel-default">
			  <div class="panel-body">
			    <table id="cusTable" data-toggle="tooltip">
					
				</table>
			  </div>
			</div>
   		</div>
    	<jsp:include page="right.jsp"/>
   	</div>
    <jsp:include page="bottom.jsp"></jsp:include>
    
    <!-- 日志详情模态框start -->
	<div class="modal fade" tabindex="-1" role="dialog" id="blogDetail">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
			<label>日志标题:</label>
    		<p id="blogTitle" class="lead"></p>
    		<label>日志正文:</label>
			<p id="blogContent"></p>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    <!-- 日志详情模态框end -->
    
    <!-- 删除提示模态框start -->
	<div class="modal fade" tabindex="-1" role="dialog" id="deleteBlog">
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
	        <button type="button" class="btn btn-primary" onclick="deleteBlog()">确认</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    <!-- 删除提示模态框end -->
    
  </body>
</html>