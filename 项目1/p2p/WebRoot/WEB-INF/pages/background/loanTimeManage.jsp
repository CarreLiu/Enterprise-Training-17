<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>loanTimeManage.html</title>

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
	 
    <!-- art-template -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/template-web.js"></script>
       
    <!-- jqueryPage -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jqueryPage.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jqueryPage.css">
	
	<script type="text/javascript">
		function checkData1() {
			//贷款周期
			let periodMsg1 = $('#periodMsg1').text();
			//获取所有的异常返回，如果有，不能提交表单
			if (periodMsg1) {
				//不提交表单
				return false;
			}
		
			//提交表单
			return true;
		}
		
		function checkData2() {
			//贷款周期
			let periodMsg2 = $('#periodMsg2').text();
			//获取所有的异常返回，如果有，不能提交表单
			if (periodMsg2) {
				//不提交表单
				return false;
			}
		
			//提交表单
			return true;
		}
		
		$(function() {
			//校验添加时贷款周期是否为空
			$('#loanTimeName4Add').blur(function() {
				$('#periodMsg1').text('');
				let name = $(this).val();
				let msg = checkNull(name);
				$('#periodMsg1').text(msg);
				//校验添加时该贷款周期是否已经存在
				checkPeriodExist1();
			});
			//校验修改时贷款周期是否为空
			$('#loanTimeName4Modify').blur(function() {
				$('#periodMsg2').text('');
				let name = $(this).val();
				let msg = checkNull(name);
				$('#periodMsg2').text(msg);
				//校验修改后该贷款周期是否已经存在
				checkPeriodExist2();
			});
		});
		
		//校验贷款周期是否已经存在
		function checkPeriodExist1() {
			let period = $('#loanTimeName4Add').val();
			if (period) {
				$.post(
					'${pageContext.request.contextPath}/lendingPeriod/addCheck.do',
					{"period":period},
					function(msg) {
						$('#periodMsg1').text(msg);
					}
				);
			}
		}
		
		//校验修改后该贷款周期是否已经存在
		function checkPeriodExist2() {
			let lendingPeriodId = $('#loanTimeId4Modify').val();
			let period = $('#loanTimeName4Modify').val();
			if (period) {
				$.post(
					'${pageContext.request.contextPath}/lendingPeriod/modifyCheck.do',
					{"period":period,'lendingPeriodId':lendingPeriodId},
					function(msg) {
						$('#periodMsg2').text(msg);
					}
				);
			}
		}
		
		//判断是否为null
		function checkNull(data) {
			if (!data) {
				return '不能为空';
			}
			return '';
		}
		
		function modify(name,id)
		{
			$("#loanTimeId4Modify").val(id);
			$("#loanTimeName4Modify").val(name);
			$('#proModify').modal();
		}
		
		function changeStatus(id,status)
		{
			let flag;
			if(status == '1')
				flag = 0;
			else
				flag = 1;
			window.location.href = "${pageContext.request.contextPath}/lendingPeriod/periodStatusModify.do?lendingPeriodId="+id+"&periodStatus="+flag;
		}
		
		
		
		
		function isEmpty(val) {
	        if (val == null || val == '' || val.typeOf == 'undefined') {
	            return true;
	        }
	        return false;
	    }
	
	    /*发送ajax请求获取数据*/
	    function loadData(pageNo, pageSize) {
	        pageNo = !isEmpty(pageNo) && !isNaN(pageNo) && pageNo > 0 ? pageNo : 1;
	        pageSize = !isEmpty(pageSize) && !isNaN(pageSize) && pageSize > 0 ? pageSize : 2;
	
	        $.ajax({
	            url: '${pageContext.request.contextPath}/lendingPeriod/ajaxLendingPeriodList.do',
	            type: 'post',
	            data : {"pageNo":pageNo,"pageSize":pageSize},
	            dataType:'json',
	            success: function(data){
	                var pageNo = data.pageNum;
	                var totalPage = data.pages;
	                var list = {
	                    lendingPeriods: data.list
	                };
	               	//console.log(list);
	                var html = template('data-template', list);
	                $('#data-box').html(html);
	
	                // 数据加载完成后进行分页器的初始化
	                initPaginator(pageNo, totalPage);
	            }
	        });
	
	        
	    }
	
	    /*初始化分页器*/
	    function initPaginator(pageNo, totalPage) {
	
	
	        // jqueryPage插件
	        $('.tcdPageCode').createPage({
	            current: pageNo,
	            pageCount: totalPage,
	            backFn: function(p) {
	                loadData(p, '');
	            }
	        });
	
	    }
	
	    $(function() {
	        loadData('${pageNo}');
	    });
		
	</script>
</head>

<body style="padding: 5px 10px;">

	<div class="panel panel-primary">
	  <div class="panel-heading">
	    <h3 class="panel-title">贷款周期管理</h3>
	  </div>
	  <div class="panel-body">
	  		<a  href="javascript:;" class="btn btn-primary btn-default" data-toggle="modal" data-target="#proAdd">
	  			添加
	  			<span class="glyphicon glyphicon-plus"></span>
	  		</a>
	  		
	  		<table class="table table-hover table-bordered">
	  			<thead>
	  				<tr>
	  					<th>贷款周期ID</th>
	  					<th>周期</th>
	  					<th>状态</th>
	  					<th>操作</th>
	  				</tr>
	  			</thead>
	  			<tbody id="data-box">
		  			<script type="text/html" id="data-template">
						{{each lendingPeriods as lendingPeriod index}}
		  				<tr> 	 	 	 	 	 
		  					<td>{{lendingPeriod.lendingPeriodId}}</td>
		  					<td>
		  						{{lendingPeriod.period}}
		  					</td>
		  					<td>{{if lendingPeriod.periodStatus == 1}}启用{{else}}禁用{{/if}}</td>
		  					<td>
		  						<a class="btn btn-primary  btn-xs btn-warning" href="javascript:;" onclick="modify('{{lendingPeriod.period}}','{{lendingPeriod.lendingPeriodId}}')">
		  							修改
		  							<span class="glyphicon glyphicon-pencil"></span>
		  						</a>
		  						{{if lendingPeriod.periodStatus == 0}}
								<a class="btn btn-primary btn-xs btn-success" href="javascript:;" onclick="changeStatus('{{lendingPeriod.lendingPeriodId}}','{{lendingPeriod.periodStatus}}')">
		  							启用
		  							<span class="glyphicon glyphicon-ok"></span>
		  						</a>
								{{else}}
								<a class="btn btn-primary btn-xs btn-danger" href="javascript:;" onclick="changeStatus('{{lendingPeriod.lendingPeriodId}}','{{lendingPeriod.periodStatus}}')">
		  							禁用
		  							<span class="glyphicon glyphicon-remove"></span>
		  						</a>
								{{/if}}
		  					</td>
		  				</tr>
		  				{{/each}}
					</script>
	  			</tbody>
	  		</table>
	  </div>
	  	<!-- jqueryPage, class值固定 -->
		<div class="tcdPageCode"></div>
	</div>
	
	
	
			<!-- 产品修改Modal -->
			<div class="modal fade" style="margin-top: 200px;" id="proModify" tabindex="-1" role="dialog" aria-labelledby="proModifyLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/lendingPeriod/lendingPeriodModify.do"
			        	method="post" onsubmit="return checkData2()">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="myModalLabel">周期修改</h4>
					      </div>
					      <div class="modal-body">
					      			<div class="form-group">
						              <label for="loanTimeId4Modify" class="col-lg-2 control-label">周期Id</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="loanTimeId4Modify" readonly="readonly" name="lendingPeriodId"/>
						              </div>
						            </div>
						            <div class="form-group">
						              <label for="loanTimeName4Modify" class="col-lg-2 control-label">周期</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="loanTimeName4Modify"  placeholder="单位：月" name="period"/>
						              </div>
						              <span id="periodMsg2" style="color:red;"></span>
						            </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <input type="submit" class="btn btn-primary" value="修改" />
					      </div>
           			</form>
			    </div>
			  </div>
			</div>
			<!-- 产品修改Modal -->
			
			<!-- 产品类型添加Modal -->
			<div class="modal fade" style="margin-top: 200px;" id="proAdd" tabindex="-1" role="dialog" aria-labelledby="proAddLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/lendingPeriod/lendingPeriodAdd.do"
			        	method="post" onsubmit="return checkData1()">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="proAddLabel">周期添加</h4>
					      </div>
					      <div class="modal-body">
						            <div class="form-group">
						              <label for="loanTimeName4Add" class="col-lg-2 control-label">周期</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="loanTimeName4Add" placeholder="单位：月" name="period"/>
						              </div>
						              <span id="periodMsg1" style="color:red;"/>
						            </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <input type="submit" class="btn btn-primary" value="添加" />
					      </div>
           			</form>
			    </div>
			  </div>
			</div>
			<!-- 产品添加Modal -->
			
			
	
	
</body>
</html>
    