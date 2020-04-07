<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>productTypeManage.html</title>

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
			//产品类型名称
			let typeMsg1 = $('#typeMsg1').text();
			//获取所有的异常返回，如果有，不能提交表单
			if (typeMsg1) {
				//不提交表单
				return false;
			}
		
			//提交表单
			return true;
		}
		
		function checkData2() {
			//产品类型名称
			let typeMsg2 = $('#typeMsg2').text();
			//获取所有的异常返回，如果有，不能提交表单
			if (typeMsg2) {
				//不提交表单
				return false;
			}
		
			//提交表单
			return true;
		}
		
		$(function() {
			//校验添加时产品类型名称是否为空
			$('#productName4Add').blur(function() {
				$('#typeMsg1').text('');
				let name = $(this).val();
				let msg = checkNull(name);
				$('#typeMsg1').text(msg);
				//校验添加时该产品类型名称是否已经存在
				checkTypeNameExist1();
			});
			//校验修改时产品类型名称是否为空
			$('#productName4Modify').blur(function() {
				$('#typeMsg2').text('');
				let name = $(this).val();
				let msg = checkNull(name);
				$('#typeMsg2').text(msg);
				//校验修改后该产品类型名称是否已经存在
				checkTypeNameExist2();
			});
		});
		
		//校验产品类型名称是否已经存在
		function checkTypeNameExist1() {
			let productTypeName = $('#productName4Add').val();
			if (productTypeName) {
				$.post(
					'${pageContext.request.contextPath}/productType/addCheck.do',
					{"productTypeName":productTypeName},
					function(msg) {
						$('#typeMsg1').text(msg);
					}
				);
			}
		}
		
		//校验修改后该产品类型名称是否已经存在
		function checkTypeNameExist2() {
			let productTypeId = $('#productId4Modify').val();
			let productTypeName = $('#productName4Modify').val();
			if (productTypeName) {
				$.post(
					'${pageContext.request.contextPath}/productType/modifyCheck.do',
					{"productTypeName":productTypeName,'productTypeId':productTypeId},
					function(msg) {
						$('#typeMsg2').text(msg);
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
			$("#productId4Modify").val(id);
			$("#productName4Modify").val(name);
			$('#proModify').modal();
		}
		
		function changeStatus(id,status)
		{
			let flag;
			if(status == '1')
				flag = 0;
			else
				flag = 1;
			window.location.href = "${pageContext.request.contextPath}/productType/productTypeStatusModify.do?productTypeId="+id+"&productTypeStatus="+flag;
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
	            url: '${pageContext.request.contextPath}/productType/ajaxProductTypeList.do',
	            type: 'post',
	            data : {"pageNo":pageNo,"pageSize":pageSize},
	            dataType:'json',
	            success: function(data){
	                var pageNo = data.pageNum;
	                var totalPage = data.pages;
	                var list = {
	                    productTypes: data.list
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
	    <h3 class="panel-title">产品类型管理</h3>
	  </div>
	  <div class="panel-body">
	  		<a  href="javascript:;" class="btn btn-primary btn-default" data-toggle="modal" data-target="#proAdd">
	  			添加
	  			<span class="glyphicon glyphicon-plus"></span>
	  		</a>
	  		
	  		<table class="table table-hover table-bordered">
	  			<thead>
	  				<tr>
	  					<th>产品类型ID</th>
	  					<th>产品类型名称</th>
	  					<th>状态</th>
	  					<th>操作</th>
	  				</tr>
	  			</thead>
	  			<tbody id="data-box">
	  				<script type="text/html" id="data-template">
						{{each productTypes as productType index}}
		  				<tr> 	 	 	 	 	 
		  					<td>{{productType.productTypeId}}</td>
		  					<td>
		  						{{productType.productTypeName}}
		  					</td>
		  					<td>{{if productType.productTypeStatus == 1}}启用{{else}}禁用{{/if}}</td>
		  					<td>
		  						<a class="btn btn-primary  btn-xs btn-warning" href="javascript:;" onclick="modify('{{productType.productTypeName}}','{{productType.productTypeId}}')">
		  							修改
		  							<span class="glyphicon glyphicon-pencil"></span>
		  						</a>
								{{if productType.productTypeStatus == 0}}
								<a class="btn btn-primary btn-xs btn-success" href="javascript:;" onclick="changeStatus('{{productType.productTypeId}}','{{productType.productTypeStatus}}')">
		  							启用
		  							<span class="glyphicon glyphicon-ok"></span>
		  						</a>
								{{else}}
								<a class="btn btn-primary btn-xs btn-danger" href="javascript:;" onclick="changeStatus('{{productType.productTypeId}}','{{productType.productTypeStatus}}')">
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
			        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/productType/productTypeModify.do"
			        	method="post" onsubmit="return checkData2()">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="myModalLabel">产品类型修改</h4>
					      </div>
					      <div class="modal-body">
					      			<div class="form-group">
						              <label for="productId4Modify" class="col-lg-2 control-label">类型Id</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="productId4Modify" readonly="readonly" name="productTypeId"/>
						              </div>
						            </div>
						            <div class="form-group">
						              <label for="productName4Modify" class="col-lg-2 control-label">类型名称</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="productName4Modify" name="productTypeName"/>
						              </div>
						              <span id="typeMsg2" style="color:red;"></span>
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
			        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/productType/productTypeAdd.do"
			        	method="post" onsubmit="return checkData1()">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="proAddLabel">产品类型添加</h4>
					      </div>
					      <div class="modal-body">
						            <div class="form-group">
						              <label for="productName4Add" class="col-lg-2 control-label">类型名称</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="productName4Add" placeholder="类型名称" name="productTypeName">
						              </div>
						              <span id="typeMsg1" style="color:red;"/>
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
    