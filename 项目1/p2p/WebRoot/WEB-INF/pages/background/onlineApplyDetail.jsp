<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>onlineApplyDetail.html</title>

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
	
	<!-- art-template -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/template-web.js"></script>
       
    <!-- jqueryPage -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jqueryPage.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jqueryPage.css">
	<script type="text/javascript">
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
	            url: '${pageContext.request.contextPath}/apply/ajaxApplyList.do',
	            type: 'post',
	            data : {"pageNo":pageNo,"pageSize":pageSize},
	            dataType:'json',
	            success: function(data){
	                var pageNo = data.pageNum;
	                var totalPage = data.pages;
	                var list = {
	                    applies: data.list
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
	    
	    
	    //时间戳格式化处理
	    function dateFormat(date, format) {
		    date = new Date(date);
		    var map = {
		        "M": date.getMonth() + 1, //月份
		        "d": date.getDate(), //日
		        "h": date.getHours(), //小时
		        "m": date.getMinutes(), //分
		        "s": date.getSeconds(), //秒
		        "q": Math.floor((date.getMonth() + 3) / 3), //季度
		        "S": date.getMilliseconds() //毫秒
		    };
		    format = format.replace(/([yMdhmsqS])+/g, function (all, t) {
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
		};
	</script>
</head>

<body style="padding: 5px 10px">

	<div class="panel panel-primary">
	  <div class="panel-heading">
	    <h3 class="panel-title">在线申请详情</h3>
	  </div>
	  <div class="panel-body">
	  		<table class="table table-hover">
	  			<thead>
	  				<tr>
	  					<th>产品名称</th>
	  					<th>申请人</th>
	  					<th>申请数额</th>
	  					<th>申请日期</th>
	  					<th>身份证号</th>
	  				</tr>
	  			</thead>
	  			<tbody id="data-box">
	  				<script type="text/html" id="data-template">
						{{each applies as apply index}}
		  				<tr> 	 	 	 	 	 
		  					<td>{{apply.applyProductName}}</td>
		  					<td>{{apply.applyPerson}}</td>
		  					<td>{{apply.applyNum}}万</td>
		  					<td>{{apply.applyDate| dateFormat:'yyyy年MM月dd日'}}</td>
		  					<td>{{apply.applyPersonIdCard}}</td>
		  				</tr>
						{{/each}}
					</script>
	  			</tbody>
	  		</table>
	  		
	  		<!-- jqueryPage, class值固定 -->
		    <div class="tcdPageCode"></div>
	  </div>
	</div>
	
</body>
</html>