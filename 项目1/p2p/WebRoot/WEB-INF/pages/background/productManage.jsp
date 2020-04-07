<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>productManage.html</title>

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
		function deleteProduct(id, pageNo, curSize) {
			//删除页只剩1个数据
			if (curSize == 1)
				pageNo = pageNo - 1;
			$.messager.model = {
				ok:{ text: "确认", classed: 'btn-default' },
				cancel: { text: "取消", classed: 'btn-error' }
			};
			$.messager.confirm("确认删除", "确认要删除吗?删除后不能恢复", function() { 
				window.location.href = "${pageContext.request.contextPath}/product/productDelete.do?productId="+id+"&pageNo="+pageNo;
			});
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
	            url: '${pageContext.request.contextPath}/product/ajaxProductList.do',
	            type: 'post',
	            data : {"pageNo":pageNo,"pageSize":pageSize},
	            dataType:'json',
	            success: function(data){
	                var pageNo = data.pageNum;
	                var totalPage = data.pages;
	                var curSize = data.size;
	                var list = {
	                    products: data.list,
	                    pageNo: pageNo,
	                    curSize : curSize
	                };
	               	//console.log(list);
	               	//console.log(list.products);
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

<body style="padding: 5px 10px">
	<span style="color:red">${errMsg}</span>
	<div class="panel panel-primary">
	  <div class="panel-heading">
	    <h3 class="panel-title">融资产品管理</h3>
	  </div>
	  <div class="panel-body">
	  		<a  href="${pageContext.request.contextPath}/product/productAdd.do" class="btn btn-primary btn-default">
	  			添加
	  			<span class="glyphicon glyphicon-plus"></span>
	  		</a>
	  		<table class="table table-hover">
	  			<thead>
	  				<tr>
	  					<th>产品名称</th>
	  					<th>发行单位</th>
	  					<th>产品类型</th>
	  					<th>贷款周期</th>
	  					<th>贷款利率</th>
	  					<th>贷款规模</th>
	  					<th>状态</th>
	  					<th>操作</th>
	  				</tr>
	  			</thead>
	  			<tbody id="data-box">
	  				<script type="text/html" id="data-template">
						{{each products as product index}}
						
		  				<tr> 	 	 	 	 	 
		  					<td>{{product.productName}}</td>
		  					<td>{{product.company.companyName}}</td>
		  					<td>{{product.productType.productTypeName}}</td>
		  					<td>{{product.lendingPeriod.period}}</td>
		  					<td>{{product.primeLendingRateTo}}%</td>
		  					<td>{{product.financingAmountTo}}万</td>
		  					<td>{{if product.lendingPeriod.periodStatus==1 && product.productType.productTypeStatus==1}}
							有效产品{{else}}无效产品{{/if}}
							</td>
		  					<td>
		  						<a class="btn btn-primary  btn-xs btn-warning" href="${pageContext.request.contextPath}/product/productModify.do?productId={{product.productId}}&pageNo={{pageNo}}">
		  							修改
		  							<span class="glyphicon glyphicon-pencil"></span>
		  						</a>
		  						<a class="btn btn-primary btn-xs btn-success" href="${pageContext.request.contextPath}/product/productDetail.do?productId={{product.productId}}">
		  							详情
		  							<span class="glyphicon glyphicon-list-alt"></span>
		  						</a>
		  						<a class="btn btn-primary btn-xs btn-danger" href="javascript:;" onclick="deleteProduct({{product.productId}}, {{pageNo}}, {{curSize}})">
		  							删除
		  							<span class="glyphicon glyphicon-remove"></span>
		  						</a>
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
</body>
</html>
